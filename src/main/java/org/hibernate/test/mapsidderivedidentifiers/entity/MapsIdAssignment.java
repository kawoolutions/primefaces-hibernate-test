package org.hibernate.test.mapsidderivedidentifiers.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "\"MapsIdAssignments\"")
@IdClass(MapsIdAssignmentId.class)
public class MapsIdAssignment implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Basic
    @Column(name = "was_absent")
    private Boolean wasAbsent = Boolean.FALSE;

    @Id
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "game_id")
    private MapsIdGame mapsIdGame;

    @Id
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "referee_id", referencedColumnName = "referee_id")
    @JoinColumn(name = "club_id", referencedColumnName = "club_id")
    @JoinColumn(name = "season_start_year", referencedColumnName = "season_start_year")
    private MapsIdRefpoolMember mapsIdRefpoolMember;

    public MapsIdAssignment()
    {
    }

    public MapsIdAssignment(MapsIdAssignment m)
    {
        this(m.getRefereeId(), m.getClubId(), m.getSeasonStartYear(), m.getGameId());

        this.wasAbsent = m.getWasAbsent();
    }

    public MapsIdAssignment(Integer refereeId, Integer clubId, Integer seasonStartYear, Integer gameId)
    {
        this.mapsIdGame = new MapsIdGame();
        this.mapsIdGame.setId(gameId);

        this.mapsIdRefpoolMember = new MapsIdRefpoolMember(refereeId, clubId, seasonStartYear);
    }

    public Integer getRefereeId()
    {
        return mapsIdRefpoolMember.getRefereeId();
    }

    public void setRefereeId(Integer refereeId)
    {
        mapsIdRefpoolMember.setRefereeId(refereeId);
    }

    public Integer getClubId()
    {
        return mapsIdRefpoolMember.getClubId();
    }

    public void setClubId(Integer clubId)
    {
        mapsIdRefpoolMember.setClubId(clubId);
    }

    public Integer getSeasonStartYear()
    {
        return mapsIdRefpoolMember.getSeasonStartYear();
    }

    public void setSeasonStartYear(Integer seasonStartYear)
    {
        mapsIdRefpoolMember.setSeasonStartYear(seasonStartYear);
    }

    public Integer getGameId()
    {
        return mapsIdGame.getId();
    }

    public void setGameId(Integer gameId)
    {
        mapsIdGame.setId(gameId);
    }

    public Boolean getWasAbsent()
    {
        return wasAbsent;
    }

    public void setWasAbsent(Boolean wasAbsent)
    {
        this.wasAbsent = wasAbsent;
    }

    public MapsIdGame getMapsIdGame()
    {
        return mapsIdGame;
    }

    public void setMapsIdGame(MapsIdGame mapsIdGame)
    {
        this.mapsIdGame = mapsIdGame;
    }

    public MapsIdRefpoolMember getMapsIdRefpoolMember()
    {
        return mapsIdRefpoolMember;
    }

    public void setMapsIdRefpoolMember(MapsIdRefpoolMember mapsIdRefpoolMember)
    {
        this.mapsIdRefpoolMember = mapsIdRefpoolMember;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( (mapsIdGame == null) ? 0 : mapsIdGame.hashCode() );
        result = prime * result + ( (mapsIdRefpoolMember == null) ? 0 : mapsIdRefpoolMember.hashCode() );
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
        MapsIdAssignment other = ( MapsIdAssignment ) obj;
        if ( mapsIdGame == null )
        {
            if ( other.mapsIdGame != null )
                return false;
        }
        else if ( !mapsIdGame.equals( other.mapsIdGame ) )
            return false;
        if ( mapsIdRefpoolMember == null )
        {
            if ( other.mapsIdRefpoolMember != null )
                return false;
        }
        else if ( !mapsIdRefpoolMember.equals( other.mapsIdRefpoolMember ) )
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "[" + wasAbsent + "]";
    }
}
