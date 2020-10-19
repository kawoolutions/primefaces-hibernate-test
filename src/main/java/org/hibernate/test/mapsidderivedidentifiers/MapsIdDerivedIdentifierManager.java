package org.hibernate.test.mapsidderivedidentifiers;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import org.hibernate.test.mapsidderivedidentifiers.entity.MapsIdGame;
import org.hibernate.test.mapsidderivedidentifiers.entity.MapsIdPlayer;
import org.hibernate.test.mapsidderivedidentifiers.entity.MapsIdPlayerStat;
import org.hibernate.test.mapsidderivedidentifiers.entity.MapsIdRoster;
import org.hibernate.test.mapsidderivedidentifiers.entity.MapsIdScore;
import org.hibernate.test.mapsidderivedidentifiers.entity.MapsIdTeamMember;
import org.hibernate.test.mapsidderivedidentifiers.simpleentity.Game;

@Named
@ApplicationScoped
public class MapsIdDerivedIdentifierManager implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    private List<MapsIdGame> entities;

    @PersistenceUnit( unitName = "TestPU" )
    private EntityManagerFactory emf;
    
//    @PersistenceContext( name = "TestPU" )
    private EntityManager em;
    
    private EntityTransaction et;
    
    @PostConstruct
    public void init()
    {
        System.out.println( "Entity manager factory: " + emf );
        
        // usually not injectable, so create EMF manually
        if ( this.emf == null )
        {
            System.out.println( "Programmatically creating entity manager factory..." );
            
            Map<String, String> properties = new HashMap<>();
            
//            PersistenceProvider provider = new org.eclipse.persistence.jpa.PersistenceProvider();
//
//            // very important: must be a class name, e.g. "org.eclipse.persistence.jpa.PersistenceProvider"
//            properties.put( "javax.persistence.provider", provider.getClass().getName() );
//
//            properties.put( TRANSACTION_TYPE, PersistenceUnitTransactionType.RESOURCE_LOCAL.name() );
//
//            properties.put( JDBC_DRIVER, "org.hsqldb.jdbcDriver" );
//            properties.put( JDBC_URL, "jdbc:hsqldb:mem:test" );
//            properties.put( JDBC_USER, "sa" );
//            properties.put( JDBC_PASSWORD, "" );
//
//            properties.put( DDL_GENERATION, "create-tables" );
//            properties.put( DDL_GENERATION_MODE, "database" );
//
//            properties.put( TARGET_DATABASE, "org.eclipse.persistence.platform.database.HSQLPlatform" );
//            properties.put( TARGET_SERVER, TargetServer.None );
            
            // use dynamic weaving
//            properties.put( WEAVING, "null" );
//            properties.put( WEAVING, "true" );
            
//            properties.put( PersistenceUnitProperties.ECLIPSELINK_PERSISTENCE_XML, "src/main/resources/" + PersistenceUnitProperties.ECLIPSELINK_PERSISTENCE_XML_DEFAULT );
            
            // the EMF creation process REQUIRES a persistence.xml to be on the classpath AT ALL TIMES, see JPAInitializer.findPersistenceUnitInfoInArchives(String puName, Map m)
            // this is why setting the properties programmatically is kinda wasted energy: specify them in the persistence.xml
            this.emf = Persistence.createEntityManagerFactory( "TestPU", properties );
//            this.emf = provider.createEntityManagerFactory( "TestPU", properties );
        }
        
        EntityManager em = emf.createEntityManager();

        System.out.println( "Entity manager: " + this.em );
        
        MapsIdPlayer testPlayer = em.find( MapsIdPlayer.class, 1 );
        
        if ( testPlayer == null )
        {
            // fill DB if player not found: lazy hack :-)
            
            this.et = em.getTransaction();
            this.et.begin();
            
            List<MapsIdPlayer> players = new ArrayList<MapsIdPlayer>();
            players.add( new MapsIdPlayer( 1, "Bird" ) );
            players.add( new MapsIdPlayer( 2, "Jordan" ) );
            players.add( new MapsIdPlayer( 3, "Duncan" ) );
            players.add( new MapsIdPlayer( 4, "Nowitzki" ) );
            players.add( new MapsIdPlayer( 5, "Doncic" ) );
            
            players.forEach( p -> em.persist( p ) );
            
            List<MapsIdRoster> rosters = new ArrayList<MapsIdRoster>();
            rosters.add( new MapsIdRoster( 1, "Dallas Mavericks", 3 ) );
            rosters.add( new MapsIdRoster( 2, "Harlem Globetrotters", 2 ) );
            rosters.add( new MapsIdRoster( 3, "Frankfurt Skyliners", 1 ) );
            rosters.add( new MapsIdRoster( 4, "Chicago Bulls", 1 ) );
            rosters.add( new MapsIdRoster( 5, "Boston Celtics", 4 ) );
            rosters.add( new MapsIdRoster( 6, "New York Knicks", 3 ) );
            rosters.add( new MapsIdRoster( 7, "Miami Heat", 2 ) );
            rosters.add( new MapsIdRoster( 8, "Unicaya Malaga", 1 ) );
            
            rosters.forEach( r -> em.persist( r ) );
            
            MapsIdGame game01 = newGame( 1, rosters.get( 0 ), rosters.get( 1 ) );
            MapsIdGame game02 = newGame( 2, rosters.get( 2 ), rosters.get( 3 ) );
            MapsIdGame game03 = newGame( 3, rosters.get( 4 ), rosters.get( 5 ) );
            MapsIdGame game04 = newGame( 4, rosters.get( 6 ), rosters.get( 7 ) );
    
            List<MapsIdGame> games = Arrays.asList( game01, game02, game03, game04 );
            
            for ( MapsIdGame game : games )
            {
                MapsIdScore homeScore = game.getMapsIdScores().get( Boolean.TRUE );
                MapsIdScore awayScore = game.getMapsIdScores().get( Boolean.FALSE );
                
                System.out.println( "Game before persist: " + NamingUtils.getMapsIdGameLabelFor( game ) + ", home = " + homeScore + ", away = " + awayScore );
    
                game.setMapsIdScores( null );
    
                em.persist( game );
                
                em.persist( homeScore );
                em.persist( awayScore );
                
                Map<Boolean, MapsIdScore> scores = new HashMap<>();
                scores.put( homeScore.getHome(), homeScore );
                scores.put( awayScore.getHome(), awayScore );
                
                game.setMapsIdScores( scores );
                
                // add all player stats for home team
                Map<Integer, MapsIdPlayerStat> homePlayerStats = new HashMap<>();
                int index = 0;
                
                for ( MapsIdPlayer player : players )
                {
                    MapsIdRoster roster = rosters.get( index );
                    
                    MapsIdTeamMember tm = new MapsIdTeamMember( player.getId(), roster.getId() );
                    tm.setMapsIdPlayer( player );
                    tm.setMapsIdRoster( roster );
                    
                    Integer jerseyNbr = index + 10;
                    
                    MapsIdPlayerStat ps = new MapsIdPlayerStat( game.getId(), homeScore.getHome(), tm.getPlayerId(), jerseyNbr );
                    ps.setMapsIdScore( homeScore );
                    ps.setMapsIdTeamMember( tm );
                    
                    em.persist( ps );
                    
                    homePlayerStats.put( jerseyNbr, ps );
                    
                    index++;
                }
                
                homeScore.setMapsIdPlayerStats( homePlayerStats );

                // add all player stats for away team
                Map<Integer, MapsIdPlayerStat> awayPlayerStats = new HashMap<>();
                index = 0;
                
                for ( MapsIdPlayer player : players )
                {
                    MapsIdRoster roster = rosters.get( index );
                    
                    MapsIdTeamMember tm = new MapsIdTeamMember( player.getId(), roster.getId() );
                    tm.setMapsIdPlayer( player );
                    tm.setMapsIdRoster( rosters.get( index ) );
                    
                    Integer jerseyNbr = index + 10;
                    
                    MapsIdPlayerStat ps = new MapsIdPlayerStat( game.getId(), awayScore.getHome(), tm.getPlayerId(), jerseyNbr );
                    ps.setMapsIdScore( awayScore );
                    ps.setMapsIdTeamMember( tm );
                    
                    em.persist( ps );
                    
                    awayPlayerStats.put( jerseyNbr, ps );
                    
                    index++;
                }
                
                awayScore.setMapsIdPlayerStats( awayPlayerStats );

                System.out.println( "Game after persist: " + NamingUtils.getMapsIdGameLabelFor( game ) + ", home = " + homeScore + ", away = " + awayScore );
            }
            
            this.et.commit();
        }
        
        if ( this.em == null || !this.em.isOpen() )
        {
            this.em = em;
        }
    }
    
    private MapsIdGame newGame( Integer gameId, MapsIdRoster homeRoster, MapsIdRoster awayRoster )
    {
        MapsIdGame game = new MapsIdGame( gameId, LocalDateTime.now() );
        wait( 500 );
        
        MapsIdScore homeScore = new MapsIdScore( game.getId(), Boolean.TRUE, homeRoster.getId(), null );
        homeScore.setMapsIdRoster( homeRoster );
        homeScore.setMapsIdGame( game );
        
        MapsIdScore awayScore = new MapsIdScore( game.getId(), Boolean.FALSE, awayRoster.getId(), null );
        awayScore.setMapsIdRoster( awayRoster );
        awayScore.setMapsIdGame( game );
        
        Map<Boolean, MapsIdScore> scores = new HashMap<>();
        scores.put( homeScore.getHome(), homeScore );
        scores.put( awayScore.getHome(), awayScore );
        
        game.setMapsIdScores( scores );

        return game;
    }
    
    private void wait( int msec )
    {
        try
        {
            Thread.sleep( msec );
        }
        catch ( InterruptedException e )
        {
            e.printStackTrace();
        }
    }

    public List<MapsIdGame> getEntities()
    {
        if ( entities == null )
        {
            try
            {
                this.entities = loadEntities();
            }
            catch ( Exception e )
            {
                e.printStackTrace();
            }
        }
        
        return entities;
    }

    public void setEntities( List<MapsIdGame> entities )
    {
        this.entities = entities;
    }
    
    protected List<MapsIdGame> loadEntities() throws Exception
    {
        List<MapsIdGame> entities = null;
        
        TypedQuery<MapsIdGame> query = em.createNamedQuery( Game.FIND_ALL, MapsIdGame.class );
//        TypedQuery<MapsIdGame> query = em.createNamedQuery( Game.FIND_ALL_JOIN_SCORES_GROUP_BY_GAME_ID, Game.class );
        
        EntityGraph<?> graph = em.createEntityGraph( MapsIdGame.FETCH_SCORES );
//        EntityGraph<?> graph = em.getEntityGraph( MapsIdGame.FETCH_SCORES_AND_PLAYER_STATS );
        query.setHint( "javax.persistence.fetchgraph", graph );
        
        entities = query.getResultList();
        
        System.out.println( "Number of games: " + entities.size() );

        for ( MapsIdGame game : entities )
        {
            MapsIdScore homeScore = game.getMapsIdScores().get( Boolean.TRUE );
            MapsIdScore awayScore = game.getMapsIdScores().get( Boolean.FALSE );
            
            System.out.println( "Game after loading: " + NamingUtils.getMapsIdGameLabelFor( game ) + ", home = " + homeScore + ", away = " + awayScore );
        }
        
        return entities;
    }
}