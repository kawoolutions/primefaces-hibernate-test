package org.hibernate.test.mapkeycolumnwritable;

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
@Table(name = "\"Issue216Scores\"")
@IdClass(Issue216ScoreId.class)
public class Issue216Score implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "game_id")
    private Integer gameId;

    @Id
    @Column(name = "is_home")
    private Boolean home;

    @Basic
    @Column(name = "final_score")
    private Integer finalScore;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "game_id", insertable = false, updatable = false)
    private Issue216Game game;

    public Issue216Score()
    {
    }

    public Issue216Score(Integer finalScore)
    {
        this(null, null, finalScore);
    }

    public Issue216Score(Integer gameId, Boolean home)
    {
        this(gameId, home, null);
    }

    public Issue216Score(Integer gameId, Boolean home, Integer finalScore)
    {
        this.gameId = gameId;
        this.home = home;
        this.finalScore = finalScore;

        this.game = new Issue216Game(gameId);
    }

    public Integer getGameId()
    {
        return gameId;
    }

    public void setGameId(Integer gameId)
    {
        this.gameId = gameId;
    }

    public Boolean getHome()
    {
        return home;
    }

    public void setHome(Boolean home)
    {
        this.home = home;
    }

    public Integer getFinalScore()
    {
        return finalScore;
    }

    public void setFinalScore(Integer finalScore)
    {
        this.finalScore = finalScore;
    }

    public Issue216Game getGame()
    {
        return game;
    }

    public void setGame(Issue216Game game)
    {
        this.game = game;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( (gameId == null) ? 0 : gameId.hashCode() );
        result = prime * result + ( (home == null) ? 0 : home.hashCode() );
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
        Issue216Score other = ( Issue216Score ) obj;
        if ( gameId == null )
        {
            if ( other.gameId != null )
                return false;
        }
        else if ( !gameId.equals( other.gameId ) )
            return false;
        if ( home == null )
        {
            if ( other.home != null )
                return false;
        }
        else if ( !home.equals( other.home ) )
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "[" + gameId + ", " + home + ", " + finalScore + "]";
    }
}
