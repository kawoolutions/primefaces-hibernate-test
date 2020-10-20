package org.hibernate.test.mapsidderivedidentifiers.entity;

import java.io.Serializable;
import java.util.Objects;

public class MapsIdTeamMemberId implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Integer player;

    private Integer roster;

    public MapsIdTeamMemberId()
    {
    }

    public MapsIdTeamMemberId(MapsIdTeamMemberId m)
    {
        this(m.getPlayerId(), m.getRosterId());
    }

    public MapsIdTeamMemberId(Integer playerId, Integer rosterId)
    {
        this.player = Objects.requireNonNull(playerId);
        this.roster = Objects.requireNonNull(rosterId);
    }

    public Integer getPlayerId()
    {
        return player;
    }

    public void setPlayerId(Integer playerId)
    {
        this.player = playerId;
    }

    public Integer getRosterId()
    {
        return roster;
    }

    public void setRosterId(Integer rosterId)
    {
        this.roster = rosterId;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( (player == null) ? 0 : player.hashCode() );
        result = prime * result + ( (roster == null) ? 0 : roster.hashCode() );
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
        if ( player == null )
        {
            if ( other.player != null )
                return false;
        }
        else if ( !player.equals( other.player ) )
            return false;
        if ( roster == null )
        {
            if ( other.roster != null )
                return false;
        }
        else if ( !roster.equals( other.roster ) )
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "[" + player + ", " + roster + "]";
    }
}
