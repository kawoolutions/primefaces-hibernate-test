package org.hibernate.test.mapsidderivedidentifiers.entity;

import java.io.Serializable;
import java.util.Objects;

public class MapsIdStatId implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Integer period;

    private MapsIdPlayerStatId playerStat;

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

        this.playerStat = new MapsIdPlayerStatId(gameId, home, playerId, rosterId);
    }

    public Integer getGameId()
    {
        return playerStat.getGameId();
    }

    public void setGameId(Integer gameId)
    {
        playerStat.setGameId(gameId);
    }

    public Boolean getHome()
    {
        return playerStat.getHome();
    }

    public void setHome(Boolean home)
    {
        playerStat.setHome(home);
    }

    public Integer getPlayerId()
    {
        return playerStat.getPlayerId();
    }

    public void setPlayerId(Integer playerId)
    {
        playerStat.setPlayerId(playerId);
    }

    public Integer getRosterId()
    {
        return playerStat.getRosterId();
    }

    public void setRosterId(Integer rosterId)
    {
        playerStat.setRosterId(rosterId);
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
        return playerStat;
    }

    public void setMapsIdPlayerStatId(MapsIdPlayerStatId mapsIdPlayerStatId)
    {
        this.playerStat = mapsIdPlayerStatId;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( (period == null) ? 0 : period.hashCode() );
        result = prime * result + ( (playerStat == null) ? 0 : playerStat.hashCode() );
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
        if ( period == null )
        {
            if ( other.period != null )
                return false;
        }
        else if ( !period.equals( other.period ) )
            return false;
        if ( playerStat == null )
        {
            if ( other.playerStat != null )
                return false;
        }
        else if ( !playerStat.equals( other.playerStat ) )
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "[" + period + ", " + playerStat + "]";
    }
}
