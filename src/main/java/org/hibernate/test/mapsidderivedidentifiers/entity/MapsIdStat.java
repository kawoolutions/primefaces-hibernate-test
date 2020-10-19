package org.hibernate.test.mapsidderivedidentifiers.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "\"MapsIdStats\"")
@IdClass(MapsIdStatId.class)
public class MapsIdStat implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @Column
    private Integer period;

    @Basic(optional = false)
    @Column
    private Integer pts;

    @Id
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "game_id", referencedColumnName = "game_id")
    @JoinColumn(name = "is_home", referencedColumnName = "is_home")
    @JoinColumn(name = "player_id", referencedColumnName = "player_id")
    @JoinColumn(name = "roster_id", referencedColumnName = "roster_id")
    private MapsIdPlayerStat mapsIdPlayerStat;

    public MapsIdStat()
    {
    }

    public MapsIdStat(MapsIdStat m)
    {
        this(m.getGameId(), m.getHome(), m.getPlayerId(), m.getRosterId(), m.getPeriod(), m.getPts());
    }

    public MapsIdStat(Integer pts)
    {
        this(null, null, null, null, null, pts);
    }

    public MapsIdStat(Integer gameId, Boolean home, Integer playerId, Integer rosterId, Integer period)
    {
        this(gameId, home, playerId, rosterId, period, null);
    }

    public MapsIdStat(Integer gameId, Boolean home, Integer playerId, Integer rosterId, Integer period, Integer pts)
    {
        this.period = Objects.requireNonNull(period);
        this.pts = pts;

        this.mapsIdPlayerStat = new MapsIdPlayerStat(gameId, home, playerId, rosterId);
    }

    public Integer getGameId()
    {
        return mapsIdPlayerStat.getGameId();
    }

    public void setGameId(Integer gameId)
    {
        mapsIdPlayerStat.setGameId(gameId);
    }

    public Boolean getHome()
    {
        return mapsIdPlayerStat.getHome();
    }

    public void setHome(Boolean home)
    {
        mapsIdPlayerStat.setHome(home);
    }

    public Integer getPlayerId()
    {
        return mapsIdPlayerStat.getPlayerId();
    }

    public void setPlayerId(Integer playerId)
    {
        mapsIdPlayerStat.setPlayerId(playerId);
    }

    public Integer getRosterId()
    {
        return mapsIdPlayerStat.getRosterId();
    }

    public void setRosterId(Integer rosterId)
    {
        mapsIdPlayerStat.setRosterId(rosterId);
    }

    public Integer getPeriod()
    {
        return period;
    }

    public void setPeriod(Integer period)
    {
        this.period = period;
    }

    public Integer getPts()
    {
        return pts;
    }

    public void setPts(Integer pts)
    {
        this.pts = pts;
    }

    public MapsIdPlayerStat getMapsIdPlayerStat()
    {
        return mapsIdPlayerStat;
    }

    public void setMapsIdPlayerStat(MapsIdPlayerStat mapsIdPlayerStat)
    {
        this.mapsIdPlayerStat = mapsIdPlayerStat;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( (mapsIdPlayerStat == null) ? 0 : mapsIdPlayerStat.hashCode() );
        result = prime * result + ( (period == null) ? 0 : period.hashCode() );
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
        MapsIdStat other = ( MapsIdStat ) obj;
        if ( mapsIdPlayerStat == null )
        {
            if ( other.mapsIdPlayerStat != null )
                return false;
        }
        else if ( !mapsIdPlayerStat.equals( other.mapsIdPlayerStat ) )
            return false;
        if ( period == null )
        {
            if ( other.period != null )
                return false;
        }
        else if ( !period.equals( other.period ) )
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "[" + period + ", " + pts + "]";
    }
}
