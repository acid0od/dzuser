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
@Table(name = "cnas")
public class NasType implements Serializable {

    private static final long serialVersionUID = 3899556711369310416L;

    @Id
    @GenericGenerator(name = "cnas_nastype_seq", strategy = "sequence", parameters = {@Parameter(name = "sequence", value = "cnas_nastype_seq")})
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cnas_nastype_seq")
    @Column(name = "nastype", nullable = false)
    private Integer id;

    @Column(name = "nastypename", columnDefinition = "character varying(100)", nullable = false)
    @NotNull
    @Size(min = 3, max = 100, message = "{model.NasType.name.error}")
    private String name;

    public NasType () {
        super();
        this.name = null;
    }

    public NasType (String name) {
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

        NasType nasType = (NasType) o;

        if (id != null ? !id.equals(nasType.id) : nasType.id != null) return false;
        return !(name != null ? !name.equals(nasType.name) : nasType.name != null);

    }

    @Override
    public int hashCode () {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString () {
        return "NasType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
