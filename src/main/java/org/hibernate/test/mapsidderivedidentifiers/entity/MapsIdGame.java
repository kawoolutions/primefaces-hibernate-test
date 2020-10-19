package org.hibernate.test.mapsidderivedidentifiers.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "\"MapsIdGames\"")
public class MapsIdGame implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Basic
    @Column(name = "arena_id")
    private Integer arenaId;

    @Basic(optional = false)
    @Column(name = "scheduled_tipoff")
    private LocalDateTime scheduledTipoff;

    @OneToMany(mappedBy = "mapsIdGame")
    private List<MapsIdAssignment> mapsIdAssignments;

    @OneToMany(mappedBy = "mapsIdGame")
    @MapKeyColumn(name = "is_home")
    private Map<Boolean, MapsIdScore> mapsIdScores;

    public MapsIdGame()
    {
    }

    public MapsIdGame(MapsIdGame m)
    {
        this(m.getArenaId(), m.getScheduledTipoff());

        this.id = Objects.requireNonNull(m.getId());
    }

    public MapsIdGame(LocalDateTime scheduledTipoff)
    {
        this(null, scheduledTipoff);
    }

    public MapsIdGame(Integer arenaId, LocalDateTime scheduledTipoff)
    {
        this.arenaId = arenaId;
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

    public Integer getArenaId()
    {
        return arenaId;
    }

    public void setArenaId(Integer arenaId)
    {
        this.arenaId = arenaId;
    }

    public LocalDateTime getScheduledTipoff()
    {
        return scheduledTipoff;
    }

    public void setScheduledTipoff(LocalDateTime scheduledTipoff)
    {
        this.scheduledTipoff = scheduledTipoff;
    }

    public List<MapsIdAssignment> getMapsIdAssignments()
    {
        return mapsIdAssignments;
    }

    public void setMapsIdAssignments(List<MapsIdAssignment> mapsIdAssignments)
    {
        this.mapsIdAssignments = mapsIdAssignments;
    }

    public Map<Boolean, MapsIdScore> getMapsIdScores()
    {
        return mapsIdScores;
    }

    public void setMapsIdScores(Map<Boolean, MapsIdScore> mapsIdScores)
    {
        this.mapsIdScores = mapsIdScores;
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
        MapsIdGame other = ( MapsIdGame ) obj;
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
        return "[" + id + ", " + arenaId + ", " + scheduledTipoff + "]";
    }
}
