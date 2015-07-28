package net.odtel.dzuser.api.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "clocation")
public class Location implements Serializable {

    private static final long serialVersionUID = 7575540669505767901L;

    @Id
    @GenericGenerator(name = "clocation_locationid_seq", strategy = "sequence", parameters = {@Parameter(name = "sequence", value = "clocation_locationid_seq")})
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clocation_locationid_seq")
    @Column(name = "locationId", nullable = false)
    private Integer id;

    @Column(name = "locationname", columnDefinition = "character varying(200)", nullable = false)
    @NotNull
    @Size(min = 3, max = 200, message = "{model.Location.name.error}")
    private String name;

    public Location () {
        super();
    }

    public Location (String name) {
        super();
        this.name = name;
    }

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (id != null ? !id.equals(location.id) : location.id != null) return false;
        return !(name != null ? !name.equals(location.name) : location.name != null);

    }

    @Override
    public int hashCode () {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString () {
        return "Location{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
