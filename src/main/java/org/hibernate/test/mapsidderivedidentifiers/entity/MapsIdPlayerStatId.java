package org.hibernate.test.mapsidderivedidentifiers.entity;

import java.io.Serializable;

public class MapsIdPlayerStatId implements Serializable
{
    private static final long serialVersionUID = 1L;

    private MapsIdScoreId mapsIdScore;

    private MapsIdTeamMemberId mapsIdTeamMember;

    public MapsIdPlayerStatId()
    {
    }

    public MapsIdPlayerStatId(MapsIdPlayerStatId m)
    {
        this(m.getGameId(), m.getHome(), m.getPlayerId(), m.getRosterId());
    }

    public MapsIdPlayerStatId(Integer gameId, Boolean home, Integer playerId, Integer rosterId)
    {
        this.mapsIdScore = new MapsIdScoreId(gameId, home);
        this.mapsIdTeamMember = new MapsIdTeamMemberId(playerId, rosterId);
    }

    public Integer getGameId()
    {
        return mapsIdScore.getGameId();
    }

    public void setGameId(Integer gameId)
    {
        mapsIdScore.setGameId(gameId);
    }

    public Boolean getHome()
    {
        return mapsIdScore.getHome();
    }

    public void setHome(Boolean home)
    {
        mapsIdScore.setHome(home);
    }

    public Integer getPlayerId()
    {
        return mapsIdTeamMember.getPlayerId();
    }

    public void setPlayerId(Integer playerId)
    {
        mapsIdTeamMember.setPlayerId(playerId);
    }

    public Integer getRosterId()
    {
        return mapsIdTeamMember.getRosterId();
    }

    public void setRosterId(Integer rosterId)
    {
        mapsIdTeamMember.setRosterId(rosterId);
    }

    public MapsIdScoreId getMapsIdScoreId()
    {
        return mapsIdScore;
    }

    public void setMapsIdScoreId(MapsIdScoreId mapsIdScoreId)
    {
        this.mapsIdScore = mapsIdScoreId;
    }

    public MapsIdTeamMemberId getMapsIdTeamMemberId()
    {
        return mapsIdTeamMember;
    }

    public void setMapsIdTeamMemberId(MapsIdTeamMemberId mapsIdTeamMemberId)
    {
        this.mapsIdTeamMember = mapsIdTeamMemberId;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( (mapsIdScore == null) ? 0 : mapsIdScore.hashCode() );
        result = prime * result + ( (mapsIdTeamMember == null) ? 0 : mapsIdTeamMember.hashCode() );
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
        if ( mapsIdScore == null )
        {
            if ( other.mapsIdScore != null )
                return false;
        }
        else if ( !mapsIdScore.equals( other.mapsIdScore ) )
            return false;
        if ( mapsIdTeamMember == null )
        {
            if ( other.mapsIdTeamMember != null )
                return false;
        }
        else if ( !mapsIdTeamMember.equals( other.mapsIdTeamMember ) )
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "[" + mapsIdScore + ", " + mapsIdTeamMember + "]";
    }
}
