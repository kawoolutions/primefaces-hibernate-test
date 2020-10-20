package org.hibernate.test.mapsidderivedidentifiers.entity;

import java.io.Serializable;
import java.util.Objects;

public class MapsIdAssignmentId implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Integer game;

    private MapsIdRefpoolMemberId refpoolMember;

    public MapsIdAssignmentId()
    {
    }

    public MapsIdAssignmentId(MapsIdAssignmentId m)
    {
        this(m.getRefereeId(), m.getClubId(), m.getSeasonStartYear(), m.getGameId());
    }

    public MapsIdAssignmentId(Integer refereeId, Integer clubId, Integer seasonStartYear, Integer gameId)
    {
        this.game = Objects.requireNonNull(gameId);

        this.refpoolMember = new MapsIdRefpoolMemberId(refereeId, clubId, seasonStartYear);
    }

    public Integer getRefereeId()
    {
        return refpoolMember.getRefereeId();
    }

    public void setRefereeId(Integer refereeId)
    {
        refpoolMember.setRefereeId(refereeId);
    }

    public Integer getClubId()
    {
        return refpoolMember.getClubId();
    }

    public void setClubId(Integer clubId)
    {
        refpoolMember.setClubId(clubId);
    }

    public Integer getSeasonStartYear()
    {
        return refpoolMember.getSeasonStartYear();
    }

    public void setSeasonStartYear(Integer seasonStartYear)
    {
        refpoolMember.setSeasonStartYear(seasonStartYear);
    }

    public Integer getGameId()
    {
        return game;
    }

    public void setGameId(Integer gameId)
    {
        this.game = gameId;
    }

    public MapsIdRefpoolMemberId getMapsIdRefpoolMemberId()
    {
        return refpoolMember;
    }

    public void setMapsIdRefpoolMemberId(MapsIdRefpoolMemberId mapsIdRefpoolMemberId)
    {
        this.refpoolMember = mapsIdRefpoolMemberId;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( (game == null) ? 0 : game.hashCode() );
        result = prime * result + ( (refpoolMember == null) ? 0 : refpoolMember.hashCode() );
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
        MapsIdAssignmentId other = ( MapsIdAssignmentId ) obj;
        if ( game == null )
        {
            if ( other.game != null )
                return false;
        }
        else if ( !game.equals( other.game ) )
            return false;
        if ( refpoolMember == null )
        {
            if ( other.refpoolMember != null )
                return false;
        }
        else if ( !refpoolMember.equals( other.refpoolMember ) )
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "[" + game + ", " + refpoolMember + "]";
    }
}
