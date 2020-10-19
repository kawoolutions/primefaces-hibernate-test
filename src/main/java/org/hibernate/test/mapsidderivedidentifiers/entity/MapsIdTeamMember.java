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
@Table(name = "\"MapsIdTeamMembers\"")
@IdClass(MapsIdTeamMemberId.class)
public class MapsIdTeamMember implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "player_id")
    private Integer playerId;

    @Id
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "roster_id")
    private MapsIdRoster mapsIdRoster;

    @OneToMany(mappedBy = "mapsIdTeamMember")
    private List<MapsIdPlayerStat> mapsIdPlayerStats;

    public MapsIdTeamMember()
    {
    }

    public MapsIdTeamMember(MapsIdTeamMember m)
    {
        this(m.getPlayerId(), m.getRosterId());
    }

    public MapsIdTeamMember(Integer playerId, Integer rosterId)
    {
        this.playerId = Objects.requireNonNull(playerId);

        this.mapsIdRoster = new MapsIdRoster();
        this.mapsIdRoster.setId(rosterId);
    }

    public Integer getPlayerId()
    {
        return playerId;
    }

    public void setPlayerId(Integer playerId)
    {
        this.playerId = playerId;
    }

    public Integer getRosterId()
    {
        return mapsIdRoster.getId();
    }

    public void setRosterId(Integer rosterId)
    {
        mapsIdRoster.setId(rosterId);
    }

    public MapsIdRoster getMapsIdRoster()
    {
        return mapsIdRoster;
    }

    public void setMapsIdRoster(MapsIdRoster mapsIdRoster)
    {
        this.mapsIdRoster = mapsIdRoster;
    }

    public List<MapsIdPlayerStat> getMapsIdPlayerStats()
    {
        return mapsIdPlayerStats;
    }

    public void setMapsIdPlayerStats(List<MapsIdPlayerStat> mapsIdPlayerStats)
    {
        this.mapsIdPlayerStats = mapsIdPlayerStats;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( (mapsIdRoster == null) ? 0 : mapsIdRoster.hashCode() );
        result = prime * result + ( (playerId == null) ? 0 : playerId.hashCode() );
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
        MapsIdTeamMember other = ( MapsIdTeamMember ) obj;
        if ( mapsIdRoster == null )
        {
            if ( other.mapsIdRoster != null )
                return false;
        }
        else if ( !mapsIdRoster.equals( other.mapsIdRoster ) )
            return false;
        if ( playerId == null )
        {
            if ( other.playerId != null )
                return false;
        }
        else if ( !playerId.equals( other.playerId ) )
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "[" + playerId + "]";
    }
}
