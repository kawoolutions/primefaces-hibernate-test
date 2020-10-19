package org.hibernate.test.mapsidderivedidentifiers.entity;

import java.io.Serializable;
import java.util.Objects;

public class MapsIdTeamMemberId implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Integer mapsIdRoster;

    private Integer playerId;

    public MapsIdTeamMemberId()
    {
    }

    public MapsIdTeamMemberId(MapsIdTeamMemberId m)
    {
        this(m.getPlayerId(), m.getRosterId());
    }

    public MapsIdTeamMemberId(Integer playerId, Integer rosterId)
    {
        this.mapsIdRoster = Objects.requireNonNull(rosterId);
        this.playerId = Objects.requireNonNull(playerId);
    }

    public Integer getPlayerId()
    {
        return playerId;
    }

    public void setPlayerId(Integer playerId)
    {
        this.playerId = playerId;
    }

    public Integer getRosterId()
    {
        return mapsIdRoster;
    }

    public void setRosterId(Integer rosterId)
    {
        this.mapsIdRoster = rosterId;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( (mapsIdRoster == null) ? 0 : mapsIdRoster.hashCode() );
        result = prime * result + ( (playerId == null) ? 0 : playerId.hashCode() );
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
        MapsIdTeamMemberId other = ( MapsIdTeamMemberId ) obj;
        if ( mapsIdRoster == null )
        {
            if ( other.mapsIdRoster != null )
                return false;
        }
        else if ( !mapsIdRoster.equals( other.mapsIdRoster ) )
            return false;
        if ( playerId == null )
        {
            if ( other.playerId != null )
                return false;
        }
        else if ( !playerId.equals( other.playerId ) )
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "[" + mapsIdRoster + ", " + playerId + "]";
    }
}
