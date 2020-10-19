package org.hibernate.test.mapsidderivedidentifiers.entity;

import java.io.Serializable;
import java.util.Objects;

public class MapsIdTeamId implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Integer mapsIdClub;

    private String teamTypeCode;

    private Integer ordinalNbr;

    public MapsIdTeamId()
    {
    }

    public MapsIdTeamId(MapsIdTeamId m)
    {
        this(m.getClubId(), m.getTeamTypeCode(), m.getOrdinalNbr());
    }

    public MapsIdTeamId(Integer clubId, String teamTypeCode, Integer ordinalNbr)
    {
        this.mapsIdClub = Objects.requireNonNull(clubId);
        this.teamTypeCode = Objects.requireNonNull(teamTypeCode);
        this.ordinalNbr = Objects.requireNonNull(ordinalNbr);
    }

    public Integer getClubId()
    {
        return mapsIdClub;
    }

    public void setClubId(Integer clubId)
    {
        this.mapsIdClub = clubId;
    }

    public String getTeamTypeCode()
    {
        return teamTypeCode;
    }

    public void setTeamTypeCode(String teamTypeCode)
    {
        this.teamTypeCode = teamTypeCode;
    }

    public Integer getOrdinalNbr()
    {
        return ordinalNbr;
    }

    public void setOrdinalNbr(Integer ordinalNbr)
    {
        this.ordinalNbr = ordinalNbr;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( (mapsIdClub == null) ? 0 : mapsIdClub.hashCode() );
        result = prime * result + ( (ordinalNbr == null) ? 0 : ordinalNbr.hashCode() );
        result = prime * result + ( (teamTypeCode == null) ? 0 : teamTypeCode.hashCode() );
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
        MapsIdTeamId other = ( MapsIdTeamId ) obj;
        if ( mapsIdClub == null )
        {
            if ( other.mapsIdClub != null )
                return false;
        }
        else if ( !mapsIdClub.equals( other.mapsIdClub ) )
            return false;
        if ( ordinalNbr == null )
        {
            if ( other.ordinalNbr != null )
                return false;
        }
        else if ( !ordinalNbr.equals( other.ordinalNbr ) )
            return false;
        if ( teamTypeCode == null )
        {
            if ( other.teamTypeCode != null )
                return false;
        }
        else if ( !teamTypeCode.equals( other.teamTypeCode ) )
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "[" + mapsIdClub + ", " + teamTypeCode + ", " + ordinalNbr + "]";
    }
}
