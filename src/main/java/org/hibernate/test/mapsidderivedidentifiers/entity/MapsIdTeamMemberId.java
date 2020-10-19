package org.hibernate.test.mapsidderivedidentifiers.entity;

import java.io.Serializable;
import java.util.Objects;

public class MapsIdTeamMemberId implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Integer mapsIdPlayer;

    private Integer mapsIdRoster;

    public MapsIdTeamMemberId()
    {
    }

    public MapsIdTeamMemberId(MapsIdTeamMemberId m)
    {
        this(m.getPlayerId(), m.getRosterId());
    }

    public MapsIdTeamMemberId(Integer playerId, Integer rosterId)
    {
        this.mapsIdPlayer = Objects.requireNonNull(playerId);
        this.mapsIdRoster = Objects.requireNonNull(rosterId);
    }

    public Integer getPlayerId()
    {
        return mapsIdPlayer;
    }

    public void setPlayerId(Integer playerId)
    {
        this.mapsIdPlayer = playerId;
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
        result = prime * result + ( (mapsIdPlayer == null) ? 0 : mapsIdPlayer.hashCode() );
        result = prime * result + ( (mapsIdRoster == null) ? 0 : mapsIdRoster.hashCode() );
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
        if ( mapsIdPlayer == null )
        {
            if ( other.mapsIdPlayer != null )
                return false;
        }
        else if ( !mapsIdPlayer.equals( other.mapsIdPlayer ) )
            return false;
        if ( mapsIdRoster == null )
        {
            if ( other.mapsIdRoster != null )
                return false;
        }
        else if ( !mapsIdRoster.equals( other.mapsIdRoster ) )
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "[" + mapsIdPlayer + ", " + mapsIdRoster + "]";
    }
}
