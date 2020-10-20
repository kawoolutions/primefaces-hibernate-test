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
    private MapsIdGame game;

    @Id
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "referee_id", referencedColumnName = "referee_id")
    @JoinColumn(name = "club_id", referencedColumnName = "club_id")
    @JoinColumn(name = "season_start_year", referencedColumnName = "season_start_year")
    private MapsIdRefpoolMember refpoolMember;

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
        this.game = new MapsIdGame();
        this.game.setId(gameId);

        this.refpoolMember = new MapsIdRefpoolMember(refereeId, clubId, seasonStartYear);
    }

    public Integer getRefereeId()
    {
        return refpoolMember.getRefereeId();
    }

    public void setRefereeId(Integer refereeId)
    {
        refpoolMember.setRefereeId(refereeId);
    }

    public Integer getClubId()
    {
        return refpoolMember.getClubId();
    }

    public void setClubId(Integer clubId)
    {
        refpoolMember.setClubId(clubId);
    }

    public Integer getSeasonStartYear()
    {
        return refpoolMember.getSeasonStartYear();
    }

    public void setSeasonStartYear(Integer seasonStartYear)
    {
        refpoolMember.setSeasonStartYear(seasonStartYear);
    }

    public Integer getGameId()
    {
        return game.getId();
    }

    public void setGameId(Integer gameId)
    {
        game.setId(gameId);
    }

    public Boolean getWasAbsent()
    {
        return wasAbsent;
    }

    public void setWasAbsent(Boolean wasAbsent)
    {
        this.wasAbsent = wasAbsent;
    }

    public MapsIdGame getGame()
    {
        return game;
    }

    public void setGame(MapsIdGame game)
    {
        this.game = game;
    }

    public MapsIdRefpoolMember getRefpoolMember()
    {
        return refpoolMember;
    }

    public void setRefpoolMember(MapsIdRefpoolMember refpoolMember)
    {
        this.refpoolMember = refpoolMember;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( (game == null) ? 0 : game.hashCode() );
        result = prime * result + ( (refpoolMember == null) ? 0 : refpoolMember.hashCode() );
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
        if ( game == null )
        {
            if ( other.game != null )
                return false;
        }
        else if ( !game.equals( other.game ) )
            return false;
        if ( refpoolMember == null )
        {
            if ( other.refpoolMember != null )
                return false;
        }
        else if ( !refpoolMember.equals( other.refpoolMember ) )
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "[" + wasAbsent + "]";
    }
}
