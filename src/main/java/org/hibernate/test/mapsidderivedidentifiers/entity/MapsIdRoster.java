package org.hibernate.test.mapsidderivedidentifiers.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "club_id", referencedColumnName = "club_id")
    @JoinColumn(name = "team_type_code", referencedColumnName = "team_type_code")
    @JoinColumn(name = "team_ordinal_nbr", referencedColumnName = "ordinal_nbr")
    private MapsIdTeam mapsIdTeam;

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
        this.mapsIdTeam = new MapsIdTeam(clubId, teamTypeCode, teamOrdinalNbr);
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
        return mapsIdTeam.getClubId();
    }

    public void setClubId(Integer clubId)
    {
        mapsIdTeam.setClubId(clubId);
    }

    public String getTeamTypeCode()
    {
        return mapsIdTeam.getTeamTypeCode();
    }

    public void setTeamTypeCode(String teamTypeCode)
    {
        mapsIdTeam.setTeamTypeCode(teamTypeCode);
    }

    public Integer getTeamOrdinalNbr()
    {
        return mapsIdTeam.getOrdinalNbr();
    }

    public void setTeamOrdinalNbr(Integer teamOrdinalNbr)
    {
        mapsIdTeam.setOrdinalNbr(teamOrdinalNbr);
    }

    public MapsIdTeam getMapsIdTeam()
    {
        return mapsIdTeam;
    }

    public void setMapsIdTeam(MapsIdTeam mapsIdTeam)
    {
        this.mapsIdTeam = mapsIdTeam;
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
        return "[" + id + "]";
    }
}
