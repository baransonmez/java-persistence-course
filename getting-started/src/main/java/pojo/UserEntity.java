package pojo;

import java.util.Objects;

public class UserEntity {
    Long id;
    String name;

    public UserEntity() {
    }

    public UserEntity(Long id, String name) {
        this();
        setId(id);
        setName(name);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntity)) return false;
        UserEntity message = (UserEntity) o;
        return Objects.equals(getId(), message.getId())
                && Objects.equals(getName(), message.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        return String.format("pojo.UserEntity{id=%d,name='%s'}",
                getId(),
                getName());
    }
}