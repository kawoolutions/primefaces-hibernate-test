package org.hibernate.test.mapsidderivedidentifiers.entity;

import java.io.Serializable;
import java.util.Objects;

public class MapsIdStatId implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Integer period;

    private MapsIdPlayerStatId mapsIdPlayerStat;

    public MapsIdStatId()
    {
    }

    public MapsIdStatId(MapsIdStatId m)
    {
        this(m.getGameId(), m.getHome(), m.getPlayerId(), m.getRosterId(), m.getPeriod());
    }

    public MapsIdStatId(Integer gameId, Boolean home, Integer playerId, Integer rosterId, Integer period)
    {
        this.period = Objects.requireNonNull(period);

        this.mapsIdPlayerStat = new MapsIdPlayerStatId(gameId, home, playerId, rosterId);
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

    public MapsIdPlayerStatId getMapsIdPlayerStatId()
    {
        return mapsIdPlayerStat;
    }

    public void setMapsIdPlayerStatId(MapsIdPlayerStatId mapsIdPlayerStatId)
    {
        this.mapsIdPlayerStat = mapsIdPlayerStatId;
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
        MapsIdStatId other = ( MapsIdStatId ) obj;
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
        return "[" + period + ", " + mapsIdPlayerStat + "]";
    }
}
