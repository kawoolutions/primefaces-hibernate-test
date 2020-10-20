package org.hibernate.test.mapsidderivedidentifiers.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "\"MapsIdPlayer\"")
public class MapsIdPlayer implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @Column
    private Integer id;

    @Basic(optional = false)
    @Column
    private String name;

    @OneToMany(mappedBy = "player")
    private List<MapsIdTeamMember> teamMembers;

    public MapsIdPlayer()
    {
    }

    public MapsIdPlayer(MapsIdPlayer m)
    {
        this(m.getId(), m.getName());
    }

    public MapsIdPlayer(Integer id)
    {
        this(id, null);
    }

    public MapsIdPlayer(String name)
    {
        this(null, name);
    }

    public MapsIdPlayer(Integer id, String name)
    {
        this.id = Objects.requireNonNull(id);
        this.name = name;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<MapsIdTeamMember> getTeamMembers()
    {
        return teamMembers;
    }

    public void setTeamMembers(List<MapsIdTeamMember> teamMembers)
    {
        this.teamMembers = teamMembers;
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
        MapsIdPlayer other = ( MapsIdPlayer ) obj;
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
        return "[" + id + ", " + name + "]";
    }
}
