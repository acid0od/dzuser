package net.odtel.dzuser.api.model;

import java.util.List;

public class RGroupReplyPair {

    private String name;
    private List<RGroupReply> list;

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public List<RGroupReply> getList () {
        return list;
    }

    public void setList (List<RGroupReply> list) {
        this.list = list;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RGroupReplyPair that = (RGroupReplyPair) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return !(list != null ? !list.equals(that.list) : that.list != null);

    }

    @Override
    public int hashCode () {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (list != null ? list.hashCode() : 0);
        return result;
    }

    @Override
    public String toString () {
        return "RGroupReplyPair{" +
                "name='" + name + '\'' +
                ", list=" + list +
                '}';
    }
}
