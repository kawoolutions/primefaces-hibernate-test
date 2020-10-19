package org.hibernate.test.mapsidderivedidentifiers.entity;

import java.io.Serializable;
import java.util.Objects;

public class MapsIdRefpoolMemberId implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Integer mapsIdClub;

    private Integer refereeId;

    private Integer seasonStartYear;

    public MapsIdRefpoolMemberId()
    {
    }

    public MapsIdRefpoolMemberId(MapsIdRefpoolMemberId m)
    {
        this(m.getRefereeId(), m.getClubId(), m.getSeasonStartYear());
    }

    public MapsIdRefpoolMemberId(Integer refereeId, Integer clubId, Integer seasonStartYear)
    {
        this.mapsIdClub = Objects.requireNonNull(clubId);
        this.refereeId = Objects.requireNonNull(refereeId);
        this.seasonStartYear = Objects.requireNonNull(seasonStartYear);
    }

    public Integer getRefereeId()
    {
        return refereeId;
    }

    public void setRefereeId(Integer refereeId)
    {
        this.refereeId = refereeId;
    }

    public Integer getClubId()
    {
        return mapsIdClub;
    }

    public void setClubId(Integer clubId)
    {
        this.mapsIdClub = clubId;
    }

    public Integer getSeasonStartYear()
    {
        return seasonStartYear;
    }

    public void setSeasonStartYear(Integer seasonStartYear)
    {
        this.seasonStartYear = seasonStartYear;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( (mapsIdClub == null) ? 0 : mapsIdClub.hashCode() );
        result = prime * result + ( (refereeId == null) ? 0 : refereeId.hashCode() );
        result = prime * result + ( (seasonStartYear == null) ? 0 : seasonStartYear.hashCode() );
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
        MapsIdRefpoolMemberId other = ( MapsIdRefpoolMemberId ) obj;
        if ( mapsIdClub == null )
        {
            if ( other.mapsIdClub != null )
                return false;
        }
        else if ( !mapsIdClub.equals( other.mapsIdClub ) )
            return false;
        if ( refereeId == null )
        {
            if ( other.refereeId != null )
                return false;
        }
        else if ( !refereeId.equals( other.refereeId ) )
            return false;
        if ( seasonStartYear == null )
        {
            if ( other.seasonStartYear != null )
                return false;
        }
        else if ( !seasonStartYear.equals( other.seasonStartYear ) )
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "[" + mapsIdClub + ", " + refereeId + ", " + seasonStartYear + "]";
    }
}
