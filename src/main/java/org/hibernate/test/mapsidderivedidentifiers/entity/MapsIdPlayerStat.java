package org.hibernate.test.mapsidderivedidentifiers.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "\"MapsIdPlayerStats\"")
@IdClass(MapsIdPlayerStatId.class)
public class MapsIdPlayerStat implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @Column(name = "jersey_nbr")
    private Integer jerseyNbr;

    @Id
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "game_id", referencedColumnName = "game_id")
    @JoinColumn(name = "is_home", referencedColumnName = "is_home")
    private MapsIdScore mapsIdScore;

    @Id
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "player_id", referencedColumnName = "player_id")
    @JoinColumn(name = "roster_id", referencedColumnName = "roster_id")
    private MapsIdTeamMember mapsIdTeamMember;

    @OneToMany(mappedBy = "mapsIdPlayerStat")
    @OrderBy("period")
    private List<MapsIdStat> mapsIdStats;

    public MapsIdPlayerStat()
    {
    }

    public MapsIdPlayerStat(MapsIdPlayerStat m)
    {
        this(m.getGameId(), m.getHome(), m.getPlayerId(), m.getRosterId(), m.getJerseyNbr());
    }

    public MapsIdPlayerStat(Integer jerseyNbr)
    {
        this(null, null, null, null, jerseyNbr);
    }

    public MapsIdPlayerStat(Integer gameId, Boolean home, Integer playerId, Integer rosterId)
    {
        this(gameId, home, playerId, rosterId, null);
    }

    public MapsIdPlayerStat(Integer gameId, Boolean home, Integer playerId, Integer rosterId, Integer jerseyNbr)
    {
        this.jerseyNbr = jerseyNbr;

        this.mapsIdScore = new MapsIdScore(gameId, home);
        this.mapsIdTeamMember = new MapsIdTeamMember(playerId, rosterId);
    }

    public Integer getGameId()
    {
        return mapsIdScore.getGameId();
    }

    public void setGameId(Integer gameId)
    {
        mapsIdScore.setGameId(gameId);
    }

    public Boolean getHome()
    {
        return mapsIdScore.getHome();
    }

    public void setHome(Boolean home)
    {
        mapsIdScore.setHome(home);
    }

    public Integer getPlayerId()
    {
        return mapsIdTeamMember.getPlayerId();
    }

    public void setPlayerId(Integer playerId)
    {
        mapsIdTeamMember.setPlayerId(playerId);
    }

    public Integer getRosterId()
    {
        return mapsIdTeamMember.getRosterId();
    }

    public void setRosterId(Integer rosterId)
    {
        mapsIdTeamMember.setRosterId(rosterId);
    }

    public Integer getJerseyNbr()
    {
        return jerseyNbr;
    }

    public void setJerseyNbr(Integer jerseyNbr)
    {
        this.jerseyNbr = jerseyNbr;
    }

    public MapsIdScore getMapsIdScore()
    {
        return mapsIdScore;
    }

    public void setMapsIdScore(MapsIdScore mapsIdScore)
    {
        this.mapsIdScore = mapsIdScore;
    }

    public MapsIdTeamMember getMapsIdTeamMember()
    {
        return mapsIdTeamMember;
    }

    public void setMapsIdTeamMember(MapsIdTeamMember mapsIdTeamMember)
    {
        this.mapsIdTeamMember = mapsIdTeamMember;
    }

    public List<MapsIdStat> getMapsIdStats()
    {
        return mapsIdStats;
    }

    public void setMapsIdStats(List<MapsIdStat> mapsIdStats)
    {
        this.mapsIdStats = mapsIdStats;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( (mapsIdScore == null) ? 0 : mapsIdScore.hashCode() );
        result = prime * result + ( (mapsIdTeamMember == null) ? 0 : mapsIdTeamMember.hashCode() );
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
        MapsIdPlayerStat other = ( MapsIdPlayerStat ) obj;
        if ( mapsIdScore == null )
        {
            if ( other.mapsIdScore != null )
                return false;
        }
        else if ( !mapsIdScore.equals( other.mapsIdScore ) )
            return false;
        if ( mapsIdTeamMember == null )
        {
            if ( other.mapsIdTeamMember != null )
                return false;
        }
        else if ( !mapsIdTeamMember.equals( other.mapsIdTeamMember ) )
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "[" + jerseyNbr + "]";
    }
}
