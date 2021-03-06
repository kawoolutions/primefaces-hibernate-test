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
@Table(name = "\"MapsIdRefpoolMembers\"")
@IdClass(MapsIdRefpoolMemberId.class)
public class MapsIdRefpoolMember implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "referee_id")
    private Integer refereeId;

    @Id
    @Column(name = "season_start_year")
    private Integer seasonStartYear;

    @Id
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "club_id")
    private MapsIdClub club;

    @OneToMany(mappedBy = "refpoolMember")
    private List<MapsIdAssignment> assignments;

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
        this.seasonStartYear = Objects.requireNonNull(seasonStartYear);

        this.club = new MapsIdClub(clubId);
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
        return club.getId();
    }

    public void setClubId(Integer clubId)
    {
        club.setId(clubId);
    }

    public Integer getSeasonStartYear()
    {
        return seasonStartYear;
    }

    public void setSeasonStartYear(Integer seasonStartYear)
    {
        this.seasonStartYear = seasonStartYear;
    }

    public MapsIdClub getClub()
    {
        return club;
    }

    public void setClub(MapsIdClub club)
    {
        this.club = club;
    }

    public List<MapsIdAssignment> getAssignments()
    {
        return assignments;
    }

    public void setAssignments(List<MapsIdAssignment> assignments)
    {
        this.assignments = assignments;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( (club == null) ? 0 : club.hashCode() );
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
        if ( club == null )
        {
            if ( other.club != null )
                return false;
        }
        else if ( !club.equals( other.club ) )
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
        return "[" + refereeId + ", " + seasonStartYear + "]";
    }
}
