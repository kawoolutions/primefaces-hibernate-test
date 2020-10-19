package org.hibernate.test.mapsidderivedidentifiers.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "\"MapsIdRosters\"")
public class MapsIdRoster implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Basic(optional = false)
    @Column(name = "club_id")
    private Integer clubId;

    @Basic(optional = false)
    @Column(name = "team_type_code")
    private String teamTypeCode;

    @Basic(optional = false)
    @Column(name = "team_ordinal_nbr")
    private Integer teamOrdinalNbr;

    @OneToMany(mappedBy = "mapsIdRoster")
    private List<MapsIdScore> mapsIdScores;

    @OneToMany(mappedBy = "mapsIdRoster")
    @OrderBy
    private List<MapsIdTeamMember> mapsIdTeamMembers;

    public MapsIdRoster()
    {
    }

    public MapsIdRoster(MapsIdRoster m)
    {
        this(m.getClubId(), m.getTeamTypeCode(), m.getTeamOrdinalNbr());

        this.id = Objects.requireNonNull(m.getId());
    }

    public MapsIdRoster(Integer clubId, String teamTypeCode, Integer teamOrdinalNbr)
    {
        this.clubId = clubId;
        this.teamTypeCode = teamTypeCode;
        this.teamOrdinalNbr = teamOrdinalNbr;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getClubId()
    {
        return clubId;
    }

    public void setClubId(Integer clubId)
    {
        this.clubId = clubId;
    }

    public String getTeamTypeCode()
    {
        return teamTypeCode;
    }

    public void setTeamTypeCode(String teamTypeCode)
    {
        this.teamTypeCode = teamTypeCode;
    }

    public Integer getTeamOrdinalNbr()
    {
        return teamOrdinalNbr;
    }

    public void setTeamOrdinalNbr(Integer teamOrdinalNbr)
    {
        this.teamOrdinalNbr = teamOrdinalNbr;
    }

    public List<MapsIdScore> getMapsIdScores()
    {
        return mapsIdScores;
    }

    public void setMapsIdScores(List<MapsIdScore> mapsIdScores)
    {
        this.mapsIdScores = mapsIdScores;
    }

    public List<MapsIdTeamMember> getMapsIdTeamMembers()
    {
        return mapsIdTeamMembers;
    }

    public void setMapsIdTeamMembers(List<MapsIdTeamMember> mapsIdTeamMembers)
    {
        this.mapsIdTeamMembers = mapsIdTeamMembers;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( (id == null) ? 0 : id.hashCode() );
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
        MapsIdRoster other = ( MapsIdRoster ) obj;
        if ( id == null )
        {
            if ( other.id != null )
                return false;
        }
        else if ( !id.equals( other.id ) )
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "[" + id + ", " + clubId + ", " + teamTypeCode + ", " + teamOrdinalNbr + "]";
    }
}
