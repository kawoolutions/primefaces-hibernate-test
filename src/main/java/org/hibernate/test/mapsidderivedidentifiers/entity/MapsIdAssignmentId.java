package org.hibernate.test.mapsidderivedidentifiers.entity;

import java.io.Serializable;
import java.util.Objects;

public class MapsIdAssignmentId implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Integer mapsIdGame;

    private MapsIdRefpoolMemberId mapsIdRefpoolMember;

    public MapsIdAssignmentId()
    {
    }

    public MapsIdAssignmentId(MapsIdAssignmentId m)
    {
        this(m.getRefereeId(), m.getClubId(), m.getSeasonStartYear(), m.getGameId());
    }

    public MapsIdAssignmentId(Integer refereeId, Integer clubId, Integer seasonStartYear, Integer gameId)
    {
        this.mapsIdGame = Objects.requireNonNull(gameId);

        this.mapsIdRefpoolMember = new MapsIdRefpoolMemberId(refereeId, clubId, seasonStartYear);
    }

    public Integer getRefereeId()
    {
        return mapsIdRefpoolMember.getRefereeId();
    }

    public void setRefereeId(Integer refereeId)
    {
        mapsIdRefpoolMember.setRefereeId(refereeId);
    }

    public Integer getClubId()
    {
        return mapsIdRefpoolMember.getClubId();
    }

    public void setClubId(Integer clubId)
    {
        mapsIdRefpoolMember.setClubId(clubId);
    }

    public Integer getSeasonStartYear()
    {
        return mapsIdRefpoolMember.getSeasonStartYear();
    }

    public void setSeasonStartYear(Integer seasonStartYear)
    {
        mapsIdRefpoolMember.setSeasonStartYear(seasonStartYear);
    }

    public Integer getGameId()
    {
        return mapsIdGame;
    }

    public void setGameId(Integer gameId)
    {
        this.mapsIdGame = gameId;
    }

    public MapsIdRefpoolMemberId getMapsIdRefpoolMemberId()
    {
        return mapsIdRefpoolMember;
    }

    public void setMapsIdRefpoolMemberId(MapsIdRefpoolMemberId mapsIdRefpoolMemberId)
    {
        this.mapsIdRefpoolMember = mapsIdRefpoolMemberId;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( (mapsIdGame == null) ? 0 : mapsIdGame.hashCode() );
        result = prime * result + ( (mapsIdRefpoolMember == null) ? 0 : mapsIdRefpoolMember.hashCode() );
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
        if ( mapsIdGame == null )
        {
            if ( other.mapsIdGame != null )
                return false;
        }
        else if ( !mapsIdGame.equals( other.mapsIdGame ) )
            return false;
        if ( mapsIdRefpoolMember == null )
        {
            if ( other.mapsIdRefpoolMember != null )
                return false;
        }
        else if ( !mapsIdRefpoolMember.equals( other.mapsIdRefpoolMember ) )
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "[" + mapsIdGame + ", " + mapsIdRefpoolMember + "]";
    }
}
