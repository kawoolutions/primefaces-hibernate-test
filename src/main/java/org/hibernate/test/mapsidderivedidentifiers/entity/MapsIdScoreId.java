package org.hibernate.test.mapsidderivedidentifiers.entity;

import java.io.Serializable;
import java.util.Objects;

public class MapsIdScoreId implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Integer mapsIdGame;

    private Boolean home;

    public MapsIdScoreId()
    {
    }

    public MapsIdScoreId(MapsIdScoreId m)
    {
        this(m.getGameId(), m.getHome());
    }

    public MapsIdScoreId(Integer gameId, Boolean home)
    {
        this.mapsIdGame = Objects.requireNonNull(gameId);
        this.home = Objects.requireNonNull(home);
    }

    public Integer getGameId()
    {
        return mapsIdGame;
    }

    public void setGameId(Integer gameId)
    {
        this.mapsIdGame = gameId;
    }

    public Boolean getHome()
    {
        return home;
    }

    public void setHome(Boolean home)
    {
        this.home = home;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( (home == null) ? 0 : home.hashCode() );
        result = prime * result + ( (mapsIdGame == null) ? 0 : mapsIdGame.hashCode() );
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
        MapsIdScoreId other = ( MapsIdScoreId ) obj;
        if ( home == null )
        {
            if ( other.home != null )
                return false;
        }
        else if ( !home.equals( other.home ) )
            return false;
        if ( mapsIdGame == null )
        {
            if ( other.mapsIdGame != null )
                return false;
        }
        else if ( !mapsIdGame.equals( other.mapsIdGame ) )
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "[" + mapsIdGame + ", " + home + "]";
    }
}
