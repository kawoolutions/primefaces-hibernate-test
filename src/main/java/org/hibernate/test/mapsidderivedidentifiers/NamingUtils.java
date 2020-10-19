package org.hibernate.test.mapsidderivedidentifiers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;

import org.hibernate.test.mapsidderivedidentifiers.entity.MapsIdGame;
import org.hibernate.test.mapsidderivedidentifiers.entity.MapsIdRoster;
import org.hibernate.test.mapsidderivedidentifiers.entity.MapsIdScore;
import org.hibernate.test.mapsidderivedidentifiers.simpleentity.Game;
import org.hibernate.test.mapsidderivedidentifiers.simpleentity.Roster;
import org.hibernate.test.mapsidderivedidentifiers.simpleentity.Score;

public class NamingUtils
{
    public static String getGameLabelFor( Game game )
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "yyyy-MM-dd HH:mm" );
        LocalDateTime scheduledTipoff = game.getScheduledTipoff();
        String isoDate = scheduledTipoff.format( formatter );
        
        Map<Boolean, Score> scores = game.getScores();
        
        String label = isoDate + ": " + getTeamLabelForRoster( scores.get( Boolean.TRUE ).getRoster() ) + " vs. " + getTeamLabelForRoster( scores.get( Boolean.FALSE ).getRoster() );
        
        return label;
    }

    public static String getTeamLabelForRoster( Roster roster )
    {
        if ( roster == null )
        {
            return "Null EAGER roster!";
        }
        
        return getTeamLabelFor( roster.getClubName(), roster.getOrdinalNbr() );
    }
    
    public static String getTeamLabelFor( String clubName, Integer teamNumber )
    {
        Objects.requireNonNull( clubName, "Club name is null!" );
        
        if ( clubName.isEmpty() )
        {
            throw new IllegalArgumentException( "Club name is empty!" );
        }
        
        if ( teamNumber <= 0 )
        {
            throw new IllegalArgumentException( "Team number is zero or less!" );
        }
        
        // setting only last or first name leaves off comma
        String teamName = clubName + " " + teamNumber;
        
        return teamName;
    }

    public static String getMapsIdGameLabelFor( MapsIdGame game )
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "yyyy-MM-dd HH:mm" );
        LocalDateTime scheduledTipoff = game.getScheduledTipoff();
        String isoDate = scheduledTipoff.format( formatter );
        
        Map<Boolean, MapsIdScore> scores = game.getMapsIdScores();
        
        String label = isoDate + ": " + getMapsIdTeamLabelForRoster( scores.get( Boolean.TRUE ).getMapsIdRoster() ) + " vs. " + getMapsIdTeamLabelForRoster( scores.get( Boolean.FALSE ).getMapsIdRoster() );
        
        return label;
    }

    public static String getMapsIdTeamLabelForRoster( MapsIdRoster roster )
    {
        if ( roster == null )
        {
            return "Null EAGER roster!";
        }
        
        return getTeamLabelFor( roster.getMapsIdTeam().getMapsIdClub().getName(), roster.getTeamOrdinalNbr() );
    }
    
    public static String getMapsIdTeamLabelFor( String clubName, Integer teamNumber )
    {
        Objects.requireNonNull( clubName, "Club name is null!" );
        
        if ( clubName.isEmpty() )
        {
            throw new IllegalArgumentException( "Club name is empty!" );
        }
        
        if ( teamNumber <= 0 )
        {
            throw new IllegalArgumentException( "Team number is zero or less!" );
        }
        
        // setting only last or first name leaves off comma
        String teamName = clubName + " " + teamNumber;
        
        return teamName;
    }
}
