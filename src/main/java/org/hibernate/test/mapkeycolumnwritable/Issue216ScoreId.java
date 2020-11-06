package org.hibernate.test.mapkeycolumnwritable;

import java.io.Serializable;

public class Issue216ScoreId implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Integer gameId;

    private Boolean home;

    public Issue216ScoreId()
    {
    }

    public Issue216ScoreId(Integer gameId, Boolean home)
    {
        this.gameId = gameId;
        this.home = home;
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
        Issue216ScoreId other = ( Issue216ScoreId ) obj;
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
        return "[" + gameId + ", " + home + "]";
    }
}
