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
    private MapsIdRoster roster;

    @Id
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "player_id")
    private MapsIdPlayer player;

    @OneToMany(mappedBy = "teamMember")
    private List<MapsIdPlayerStat> playerStats;

    public MapsIdTeamMember()
    {
    }

    public MapsIdTeamMember(MapsIdTeamMember m)
    {
        this(m.getPlayerId(), m.getRosterId());
    }

    public MapsIdTeamMember(Integer playerId, Integer rosterId)
    {
        this.roster = new MapsIdRoster();
        this.roster.setId(rosterId);

        this.player = new MapsIdPlayer(playerId);
    }

    public Integer getPlayerId()
    {
        return player.getId();
    }

    public void setPlayerId(Integer playerId)
    {
        player.setId(playerId);
    }

    public Integer getRosterId()
    {
        return roster.getId();
    }

    public void setRosterId(Integer rosterId)
    {
        roster.setId(rosterId);
    }

    public MapsIdRoster getRoster()
    {
        return roster;
    }

    public void setRoster(MapsIdRoster roster)
    {
        this.roster = roster;
    }

    public MapsIdPlayer getPlayer()
    {
        return player;
    }

    public void setPlayer(MapsIdPlayer player)
    {
        this.player = player;
    }

    public List<MapsIdPlayerStat> getPlayerStats()
    {
        return playerStats;
    }

    public void setPlayerStats(List<MapsIdPlayerStat> playerStats)
    {
        this.playerStats = playerStats;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( (player == null) ? 0 : player.hashCode() );
        result = prime * result + ( (roster == null) ? 0 : roster.hashCode() );
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
        if ( player == null )
        {
            if ( other.player != null )
                return false;
        }
        else if ( !player.equals( other.player ) )
            return false;
        if ( roster == null )
        {
            if ( other.roster != null )
                return false;
        }
        else if ( !roster.equals( other.roster ) )
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "[" + "]";
    }
}
