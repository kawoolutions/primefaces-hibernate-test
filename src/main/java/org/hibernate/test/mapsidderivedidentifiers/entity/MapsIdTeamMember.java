package org.hibernate.test.mapsidderivedidentifiers.entity;

import java.io.Serializable;
import java.util.List;

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
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "roster_id")
    private MapsIdRoster mapsIdRoster;

    @Id
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "player_id")
    private MapsIdPlayer mapsIdPlayer;

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
        this.mapsIdRoster = new MapsIdRoster();
        this.mapsIdRoster.setId(rosterId);

        this.mapsIdPlayer = new MapsIdPlayer(playerId);
    }

    public Integer getPlayerId()
    {
        return mapsIdPlayer.getId();
    }

    public void setPlayerId(Integer playerId)
    {
        mapsIdPlayer.setId(playerId);
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

    public MapsIdPlayer getMapsIdPlayer()
    {
        return mapsIdPlayer;
    }

    public void setMapsIdPlayer(MapsIdPlayer mapsIdPlayer)
    {
        this.mapsIdPlayer = mapsIdPlayer;
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
        result = prime * result + ( (mapsIdPlayer == null) ? 0 : mapsIdPlayer.hashCode() );
        result = prime * result + ( (mapsIdRoster == null) ? 0 : mapsIdRoster.hashCode() );
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
        if ( mapsIdPlayer == null )
        {
            if ( other.mapsIdPlayer != null )
                return false;
        }
        else if ( !mapsIdPlayer.equals( other.mapsIdPlayer ) )
            return false;
        if ( mapsIdRoster == null )
        {
            if ( other.mapsIdRoster != null )
                return false;
        }
        else if ( !mapsIdRoster.equals( other.mapsIdRoster ) )
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "[" + "]";
    }
}
