package net.odtel.dzuser.api.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ruser")
public class DialupUser {

    @Id
    @GenericGenerator(name = "ruser_userid_seq", strategy = "sequence", parameters = {@Parameter(name = "sequence", value = "ruser_userid_seq")})
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ruser_userid_seq")
    @Column(name = "userid", nullable = false)
    private Integer id;

    @Column(name = "username", columnDefinition = "character varying(30) DEFAULT ''::character varying", nullable = false)
    @Size(min = 3, max = 20, message = "The User name must be at least 3 characters long.")
    private String name;

    @Column(name = "userattr", columnDefinition = "character varying(64) DEFAULT ''::character varying", nullable = false)
    @Size(min = 3, max = 64, message = "The Attribute name must be at least 3 characters long.")
    private String attr;

    @Column(name = "userop", columnDefinition = "character varying(2) DEFAULT '=='::bpchar", nullable = false)
    @Size(min = 1, max = 2, message = "The Attribute name must be at least 2 characters long.")
    private String op;

    @Column(name = "userval", columnDefinition = "character varying(250) DEFAULT ''::character varying", nullable = false)
    @Size(min = 3, max = 250, message = "The Value must be at least 3 characters long.")
    private String val;

    /**
     *
     */
    public DialupUser () {
        super();
        this.name = null;
        this.attr = null;
        this.op = "==";
        this.val = null;
    }

    /**
     * @param name
     * @param attr
     * @param op
     * @param val
     */

    public DialupUser (String name, String attr, String op, String val) {
        super();
        this.name = name;
        this.attr = attr;
        this.op = op;
        this.val = val;
    }

    /**
     * @return the id
     */
    public Integer getId () {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId (Integer id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName () {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName (String name) {
        this.name = name;
    }

    /**
     * @return the attr
     */
    public String getAttr () {
        return attr;
    }

    /**
     * @param attr the attr to set
     */
    public void setAttr (String attr) {
        this.attr = attr;
    }

    /**
     * @return the op
     */
    public String getOp () {
        return op;
    }

    /**
     * @param op the op to set
     */
    public void setOp (String op) {
        this.op = op;
    }

    /**
     * @return the val
     */
    public String getVal () {
        return val;
    }

    /**
     * @param val the val to set
     */
    public void setVal (String val) {
        this.val = val;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DialupUser that = (DialupUser) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (attr != null ? !attr.equals(that.attr) : that.attr != null) return false;
        if (op != null ? !op.equals(that.op) : that.op != null) return false;
        return !(val != null ? !val.equals(that.val) : that.val != null);

    }

    @Override
    public int hashCode () {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (attr != null ? attr.hashCode() : 0);
        result = 31 * result + (op != null ? op.hashCode() : 0);
        result = 31 * result + (val != null ? val.hashCode() : 0);
        return result;
    }

    @Override
    public String toString () {
        return "DialupUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", attr='" + attr + '\'' +
                ", op='" + op + '\'' +
                ", val='" + val + '\'' +
                '}';
    }
}
