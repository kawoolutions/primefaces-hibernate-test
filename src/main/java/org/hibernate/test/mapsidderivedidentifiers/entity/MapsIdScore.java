package org.hibernate.test.mapsidderivedidentifiers.entity;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "\"MapsIdScores\"")
@IdClass(MapsIdScoreId.class)
public class MapsIdScore implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "is_home", insertable = false, updatable = false)
    private Boolean home;

    @Basic
    @Column(name = "final_score")
    private Integer finalScore;

    @Id
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "game_id")
    private MapsIdGame game;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "roster_id")
    private MapsIdRoster roster;

    @OneToMany(mappedBy = "score")
    @MapKey(name = "jerseyNbr")
    @OrderBy("starter DESC, jerseyNbr")
    private Map<Integer, MapsIdPlayerStat> playerStats;

    public MapsIdScore()
    {
    }

    public MapsIdScore(MapsIdScore m)
    {
        this(m.getGameId(), m.getHome(), m.getRosterId(), m.getFinalScore());
    }

    public MapsIdScore(Integer finalScore)
    {
        this(null, null, null, finalScore);
    }

    public MapsIdScore(Integer gameId, Boolean home)
    {
        this(gameId, home, null);
    }

    public MapsIdScore(Integer gameId, Boolean home, Integer rosterId)
    {
        this(gameId, home, rosterId, null);
    }

    public MapsIdScore(Integer gameId, Boolean home, Integer rosterId, Integer finalScore)
    {
        this.home = Objects.requireNonNull(home);
        this.finalScore = finalScore;

        this.game = new MapsIdGame();
        this.game.setId(gameId);

        this.roster = new MapsIdRoster();
        this.roster.setId(rosterId);
    }

    public Integer getGameId()
    {
        return game.getId();
    }

    public void setGameId(Integer gameId)
    {
        game.setId(gameId);
    }

    public Boolean getHome()
    {
        return home;
    }

    public void setHome(Boolean home)
    {
        this.home = home;
    }

    public Integer getRosterId()
    {
        return roster.getId();
    }

    public void setRosterId(Integer rosterId)
    {
        roster.setId(rosterId);
    }

    public Integer getFinalScore()
    {
        return finalScore;
    }

    public void setFinalScore(Integer finalScore)
    {
        this.finalScore = finalScore;
    }

    public MapsIdGame getGame()
    {
        return game;
    }

    public void setGame(MapsIdGame game)
    {
        this.game = game;
    }

    public MapsIdRoster getRoster()
    {
        return roster;
    }

    public void setRoster(MapsIdRoster roster)
    {
        this.roster = roster;
    }

    public Map<Integer, MapsIdPlayerStat> getPlayerStats()
    {
        return playerStats;
    }

    public void setPlayerStats(Map<Integer, MapsIdPlayerStat> playerStats)
    {
        this.playerStats = playerStats;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( (game == null) ? 0 : game.hashCode() );
        result = prime * result + ( (home == null) ? 0 : home.hashCode() );
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        MapsIdScore other = ( MapsIdScore ) obj;
        if ( game == null )
        {
            if ( other.game != null )
                return false;
        }
        else if ( !game.equals( other.game ) )
            return false;
        if ( home == null )
        {
            if ( other.home != null )
                return false;
        }
        else if ( !home.equals( other.home ) )
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "[" + home + ", " + finalScore + "]";
    }
}
