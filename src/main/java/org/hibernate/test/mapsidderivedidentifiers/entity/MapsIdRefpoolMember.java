package org.hibernate.test.mapsidderivedidentifiers.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "\"MapsIdRefpoolMembers\"")
@IdClass(MapsIdRefpoolMemberId.class)
public class MapsIdRefpoolMember implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "referee_id")
    private Integer refereeId;

    @Id
    @Column(name = "club_id")
    private Integer clubId;

    @Id
    @Column(name = "season_start_year")
    private Integer seasonStartYear;

    @OneToMany(mappedBy = "mapsIdRefpoolMember")
    private List<MapsIdAssignment> mapsIdAssignments;

    public MapsIdRefpoolMember()
    {
    }

    public MapsIdRefpoolMember(MapsIdRefpoolMember m)
    {
        this(m.getRefereeId(), m.getClubId(), m.getSeasonStartYear());
    }

    public MapsIdRefpoolMember(Integer refereeId, Integer clubId, Integer seasonStartYear)
    {
        this.refereeId = Objects.requireNonNull(refereeId);
        this.clubId = Objects.requireNonNull(clubId);
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
        return clubId;
    }

    public void setClubId(Integer clubId)
    {
        this.clubId = clubId;
    }

    public Integer getSeasonStartYear()
    {
        return seasonStartYear;
    }

    public void setSeasonStartYear(Integer seasonStartYear)
    {
        this.seasonStartYear = seasonStartYear;
    }

    public List<MapsIdAssignment> getMapsIdAssignments()
    {
        return mapsIdAssignments;
    }

    public void setMapsIdAssignments(List<MapsIdAssignment> mapsIdAssignments)
    {
        this.mapsIdAssignments = mapsIdAssignments;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( (clubId == null) ? 0 : clubId.hashCode() );
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
        MapsIdRefpoolMember other = ( MapsIdRefpoolMember ) obj;
        if ( clubId == null )
        {
            if ( other.clubId != null )
                return false;
        }
        else if ( !clubId.equals( other.clubId ) )
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
        return "[" + refereeId + ", " + clubId + ", " + seasonStartYear + "]";
    }
}
