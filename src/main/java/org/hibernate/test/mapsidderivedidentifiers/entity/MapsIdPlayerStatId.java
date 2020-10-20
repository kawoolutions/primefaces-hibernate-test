package org.hibernate.test.mapsidderivedidentifiers.entity;

import java.io.Serializable;

public class MapsIdPlayerStatId implements Serializable
{
    private static final long serialVersionUID = 1L;

    private MapsIdScoreId score;

    private MapsIdTeamMemberId teamMember;

    public MapsIdPlayerStatId()
    {
    }

    public MapsIdPlayerStatId(MapsIdPlayerStatId m)
    {
        this(m.getGameId(), m.getHome(), m.getPlayerId(), m.getRosterId());
    }

    public MapsIdPlayerStatId(Integer gameId, Boolean home, Integer playerId, Integer rosterId)
    {
        this.score = new MapsIdScoreId(gameId, home);
        this.teamMember = new MapsIdTeamMemberId(playerId, rosterId);
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

    public MapsIdScoreId getMapsIdScoreId()
    {
        return score;
    }

    public void setMapsIdScoreId(MapsIdScoreId mapsIdScoreId)
    {
        this.score = mapsIdScoreId;
    }

    public MapsIdTeamMemberId getMapsIdTeamMemberId()
    {
        return teamMember;
    }

    public void setMapsIdTeamMemberId(MapsIdTeamMemberId mapsIdTeamMemberId)
    {
        this.teamMember = mapsIdTeamMemberId;
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
        MapsIdPlayerStatId other = ( MapsIdPlayerStatId ) obj;
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
        return "[" + score + ", " + teamMember + "]";
    }
}
