package org.hibernate.test.mapkeycolumnwritable;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "\"Issue216Games\"")
public class Issue216Game implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @Column
    private Integer id;

    @Basic(optional = false)
    @Column(name = "scheduled_tipoff")
    private LocalDateTime scheduledTipoff;

    @OneToMany(mappedBy = "game")
    @MapKeyColumn(name = "is_home", insertable = false, updatable = false)
    private Map<Boolean, Issue216Score> scores;

    public Issue216Game()
    {
    }

    public Issue216Game(Integer id)
    {
        this(id, null);
    }

    public Issue216Game(LocalDateTime scheduledTipoff)
    {
        this(null, scheduledTipoff);
    }

    public Issue216Game(Integer id, LocalDateTime scheduledTipoff)
    {
        this.id = id;
        this.scheduledTipoff = scheduledTipoff;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public LocalDateTime getScheduledTipoff()
    {
        return scheduledTipoff;
    }

    public void setScheduledTipoff(LocalDateTime scheduledTipoff)
    {
        this.scheduledTipoff = scheduledTipoff;
    }

    public Map<Boolean, Issue216Score> getScores()
    {
        return scores;
    }

    public void setScores(Map<Boolean, Issue216Score> scores)
    {
        this.scores = scores;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( (id == null) ? 0 : id.hashCode() );
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
        Issue216Game other = ( Issue216Game ) obj;
        if ( id == null )
        {
            if ( other.id != null )
                return false;
        }
        else if ( !id.equals( other.id ) )
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "[" + id + ", " + scheduledTipoff + "]";
    }
}
