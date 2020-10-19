package org.hibernate.test.mapsidderivedidentifiers.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "\"MapsIdTeam\"")
@IdClass(MapsIdTeamId.class)
public class MapsIdTeam implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "team_type_code")
    private String teamTypeCode;

    @Id
    @Column(name = "ordinal_nbr")
    private Integer ordinalNbr;

    @Id
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "club_id")
    private MapsIdClub mapsIdClub;

    @OneToMany(mappedBy = "mapsIdTeam")
    private List<MapsIdRoster> mapsIdRosters;

    public MapsIdTeam()
    {
    }

    public MapsIdTeam(MapsIdTeam m)
    {
        this(m.getClubId(), m.getTeamTypeCode(), m.getOrdinalNbr());
    }

    public MapsIdTeam(Integer clubId, String teamTypeCode, Integer ordinalNbr)
    {
        this.teamTypeCode = Objects.requireNonNull(teamTypeCode);
        this.ordinalNbr = Objects.requireNonNull(ordinalNbr);

        this.mapsIdClub = new MapsIdClub(clubId);
    }

    public Integer getClubId()
    {
        return mapsIdClub.getId();
    }

    public void setClubId(Integer clubId)
    {
        mapsIdClub.setId(clubId);
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

    public MapsIdClub getMapsIdClub()
    {
        return mapsIdClub;
    }

    public void setMapsIdClub(MapsIdClub mapsIdClub)
    {
        this.mapsIdClub = mapsIdClub;
    }

    public List<MapsIdRoster> getMapsIdRosters()
    {
        return mapsIdRosters;
    }

    public void setMapsIdRosters(List<MapsIdRoster> mapsIdRosters)
    {
        this.mapsIdRosters = mapsIdRosters;
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
        MapsIdTeam other = ( MapsIdTeam ) obj;
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
        return "[" + teamTypeCode + ", " + ordinalNbr + "]";
    }
}
