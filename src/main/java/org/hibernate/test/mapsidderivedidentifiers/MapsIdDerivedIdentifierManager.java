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

import org.hibernate.test.mapsidderivedidentifiers.entity.MapsIdClub;
import org.hibernate.test.mapsidderivedidentifiers.entity.MapsIdGame;
import org.hibernate.test.mapsidderivedidentifiers.entity.MapsIdPlayer;
import org.hibernate.test.mapsidderivedidentifiers.entity.MapsIdPlayerStat;
import org.hibernate.test.mapsidderivedidentifiers.entity.MapsIdRoster;
import org.hibernate.test.mapsidderivedidentifiers.entity.MapsIdScore;
import org.hibernate.test.mapsidderivedidentifiers.entity.MapsIdTeam;
import org.hibernate.test.mapsidderivedidentifiers.entity.MapsIdTeamMember;

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
            
            List<MapsIdClub> clubs = new ArrayList<MapsIdClub>();
            clubs.add( new MapsIdClub( 1, "Dallas Mavericks" ) );
            clubs.add( new MapsIdClub( 2, "Harlem Globetrotters" ) );
            clubs.add( new MapsIdClub( 3, "Frankfurt Skyliners" ) );
            clubs.add( new MapsIdClub( 4, "Chicago Bulls" ) );
            clubs.add( new MapsIdClub( 5, "Boston Celtics" ) );
            clubs.add( new MapsIdClub( 6, "New York Knicks" ) );
            clubs.add( new MapsIdClub( 7, "Miami Heat" ) );
            clubs.add( new MapsIdClub( 8, "Unicaya Malaga" ) );
            
            clubs.forEach( c -> em.persist( c ) );
            
            List<MapsIdTeam> teams = new ArrayList<MapsIdTeam>();
            teams.add( new MapsIdTeam( 1, "MO20", 3 ) );
            teams.add( new MapsIdTeam( 2, "MO20", 2 ) );
            teams.add( new MapsIdTeam( 3, "MO20", 1 ) );
            teams.add( new MapsIdTeam( 4, "MO20", 1 ) );
            teams.add( new MapsIdTeam( 5, "MO20", 4 ) );
            teams.add( new MapsIdTeam( 6, "MO20", 3 ) );
            teams.add( new MapsIdTeam( 7, "MO20", 2 ) );
            teams.add( new MapsIdTeam( 8, "MO20", 1 ) );
            
            teams.forEach( t -> em.persist( t ) );
            
            // overwrite club for each team with instances that have the name set
            teams.forEach( t -> t.setClub( clubs.get( clubs.indexOf( t.getClub() ) ) ) );
            
            // roster ID is being generated
            List<MapsIdRoster> rosters = new ArrayList<MapsIdRoster>();
            rosters.add( new MapsIdRoster( 1, "MO20", 3 ) );
            rosters.add( new MapsIdRoster( 2, "MO20", 2 ) );
            rosters.add( new MapsIdRoster( 3, "MO20", 1 ) );
            rosters.add( new MapsIdRoster( 4, "MO20", 1 ) );
            rosters.add( new MapsIdRoster( 5, "MO20", 4 ) );
            rosters.add( new MapsIdRoster( 6, "MO20", 3 ) );
            rosters.add( new MapsIdRoster( 7, "MO20", 2 ) );
            rosters.add( new MapsIdRoster( 8, "MO20", 1 ) );
            
            rosters.forEach( r -> em.persist( r ) );
            rosters.forEach( r -> r.setTeam( teams.get( teams.indexOf( r.getTeam() ) ) ) );
            
            MapsIdGame game01 = newGame( 1, rosters.get( 0 ), rosters.get( 1 ) );
            MapsIdGame game02 = newGame( 2, rosters.get( 2 ), rosters.get( 3 ) );
            MapsIdGame game03 = newGame( 3, rosters.get( 4 ), rosters.get( 5 ) );
            MapsIdGame game04 = newGame( 4, rosters.get( 6 ), rosters.get( 7 ) );
    
            List<MapsIdGame> games = Arrays.asList( game01, game02, game03, game04 );
            
            for ( MapsIdGame game : games )
            {
                MapsIdScore homeScore = game.getScores().get( Boolean.TRUE );
                MapsIdScore awayScore = game.getScores().get( Boolean.FALSE );
                
                System.out.println( "Game before persist: " + NamingUtils.getMapsIdGameLabelFor( game ) + ", home = " + homeScore + ", away = " + awayScore );
    
                game.setScores( null );
    
                em.persist( game );
                
                em.persist( homeScore );
                em.persist( awayScore );
                
                Map<Boolean, MapsIdScore> scores = new HashMap<>();
                scores.put( homeScore.getHome(), homeScore );
                scores.put( awayScore.getHome(), awayScore );
                
                game.setScores( scores );
                
                // add all player stats for home team
                Map<Integer, MapsIdPlayerStat> homePlayerStats = new HashMap<>();
                int index = 0;
                
                for ( MapsIdPlayer player : players )
                {
                    MapsIdRoster roster = rosters.get( index );
                    
                    MapsIdTeamMember tm = new MapsIdTeamMember( player.getId(), roster.getId() );
                    tm.setPlayer( player );
                    tm.setRoster( roster );
                    
                    Integer jerseyNbr = index + 10;
                    
                    MapsIdPlayerStat ps = new MapsIdPlayerStat( game.getId(), homeScore.getHome(), tm.getPlayerId(), roster.getId(), jerseyNbr );
                    ps.setScore( homeScore );
                    ps.setTeamMember( tm );
                    
                    em.persist( ps );
                    
                    homePlayerStats.put( jerseyNbr, ps );
                    
                    index++;
                }
                
                homeScore.setPlayerStats( homePlayerStats );

                // add all player stats for away team
                Map<Integer, MapsIdPlayerStat> awayPlayerStats = new HashMap<>();
                index = 0;
                
                for ( MapsIdPlayer player : players )
                {
                    MapsIdRoster roster = rosters.get( index );
                    
                    MapsIdTeamMember tm = new MapsIdTeamMember( player.getId(), roster.getId() );
                    tm.setPlayer( player );
                    tm.setRoster( rosters.get( index ) );
                    
                    Integer jerseyNbr = index + 10;
                    
                    MapsIdPlayerStat ps = new MapsIdPlayerStat( game.getId(), awayScore.getHome(), tm.getPlayerId(), roster.getId(), jerseyNbr );
                    ps.setScore( awayScore );
                    ps.setTeamMember( tm );
                    
                    em.persist( ps );
                    
                    awayPlayerStats.put( jerseyNbr, ps );
                    
                    index++;
                }
                
                awayScore.setPlayerStats( awayPlayerStats );

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
        homeScore.setRoster( homeRoster );
        homeScore.setGame( game );
        
        MapsIdScore awayScore = new MapsIdScore( game.getId(), Boolean.FALSE, awayRoster.getId(), null );
        awayScore.setRoster( awayRoster );
        awayScore.setGame( game );
        
        Map<Boolean, MapsIdScore> scores = new HashMap<>();
        scores.put( homeScore.getHome(), homeScore );
        scores.put( awayScore.getHome(), awayScore );
        
        game.setScores( scores );

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
        
        TypedQuery<MapsIdGame> query = em.createNamedQuery( MapsIdGame.FIND_ALL, MapsIdGame.class );
        
        EntityGraph<?> graph = em.createEntityGraph( MapsIdGame.FETCH_SCORES );
//        EntityGraph<?> graph = em.getEntityGraph( MapsIdGame.FETCH_SCORES_AND_PLAYER_STATS );
        query.setHint( "javax.persistence.fetchgraph", graph );
        
        entities = query.getResultList();
        
        System.out.println( "Number of games: " + entities.size() );

        for ( MapsIdGame game : entities )
        {
            MapsIdScore homeScore = game.getScores().get( Boolean.TRUE );
            MapsIdScore awayScore = game.getScores().get( Boolean.FALSE );
            
            System.out.println( "Game after loading: " + NamingUtils.getMapsIdGameLabelFor( game ) + ", home = " + homeScore + ", away = " + awayScore );
        }
        
        return entities;
    }
}