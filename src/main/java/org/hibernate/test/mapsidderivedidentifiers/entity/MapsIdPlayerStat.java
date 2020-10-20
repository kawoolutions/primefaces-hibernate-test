package org.hibernate.test.mapsidderivedidentifiers.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "\"MapsIdPlayerStats\"")
@IdClass(MapsIdPlayerStatId.class)
public class MapsIdPlayerStat implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @Column(name = "jersey_nbr")
    private Integer jerseyNbr;

    @Id
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "game_id", referencedColumnName = "game_id")
    @JoinColumn(name = "is_home", referencedColumnName = "is_home")
    private MapsIdScore score;

    @Id
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "player_id", referencedColumnName = "player_id")
    @JoinColumn(name = "roster_id", referencedColumnName = "roster_id")
    private MapsIdTeamMember teamMember;

    @OneToMany(mappedBy = "playerStat")
    @OrderBy("period")
    private List<MapsIdStat> stats;

    public MapsIdPlayerStat()
    {
    }

    public MapsIdPlayerStat(MapsIdPlayerStat m)
    {
        this(m.getGameId(), m.getHome(), m.getPlayerId(), m.getRosterId(), m.getJerseyNbr());
    }

    public MapsIdPlayerStat(Integer jerseyNbr)
    {
        this(null, null, null, null, jerseyNbr);
    }

    public MapsIdPlayerStat(Integer gameId, Boolean home, Integer playerId, Integer rosterId)
    {
        this(gameId, home, playerId, rosterId, null);
    }

    public MapsIdPlayerStat(Integer gameId, Boolean home, Integer playerId, Integer rosterId, Integer jerseyNbr)
    {
        this.jerseyNbr = jerseyNbr;

        this.score = new MapsIdScore(gameId, home);
        this.teamMember = new MapsIdTeamMember(playerId, rosterId);
    }

    public Integer getGameId()
    {
        return score.getGameId();
    }

    public void setGameId(Integer gameId)
    {
        score.setGameId(gameId);
    }

    public Boolean getHome()
    {
        return score.getHome();
    }

    public void setHome(Boolean home)
    {
        score.setHome(home);
    }

    public Integer getPlayerId()
    {
        return teamMember.getPlayerId();
    }

    public void setPlayerId(Integer playerId)
    {
        teamMember.setPlayerId(playerId);
    }

    public Integer getRosterId()
    {
        return teamMember.getRosterId();
    }

    public void setRosterId(Integer rosterId)
    {
        teamMember.setRosterId(rosterId);
    }

    public Integer getJerseyNbr()
    {
        return jerseyNbr;
    }

    public void setJerseyNbr(Integer jerseyNbr)
    {
        this.jerseyNbr = jerseyNbr;
    }

    public MapsIdScore getScore()
    {
        return score;
    }

    public void setScore(MapsIdScore score)
    {
        this.score = score;
    }

    public MapsIdTeamMember getTeamMember()
    {
        return teamMember;
    }

    public void setTeamMember(MapsIdTeamMember teamMember)
    {
        this.teamMember = teamMember;
    }

    public List<MapsIdStat> getStats()
    {
        return stats;
    }

    public void setStats(List<MapsIdStat> stats)
    {
        this.stats = stats;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( (score == null) ? 0 : score.hashCode() );
        result = prime * result + ( (teamMember == null) ? 0 : teamMember.hashCode() );
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
        MapsIdPlayerStat other = ( MapsIdPlayerStat ) obj;
        if ( score == null )
        {
            if ( other.score != null )
                return false;
        }
        else if ( !score.equals( other.score ) )
            return false;
        if ( teamMember == null )
        {
            if ( other.teamMember != null )
                return false;
        }
        else if ( !teamMember.equals( other.teamMember ) )
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "[" + jerseyNbr + "]";
    }
}
