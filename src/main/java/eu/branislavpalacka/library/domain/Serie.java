package eu.branislavpalacka.library.domain;

import org.springframework.lang.NonNull;

import java.util.Objects;

public class Serie {
    @NonNull
    private Integer id;
    @NonNull
    private String name;

    public Serie() {
    }

    public Serie(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Serie serie = (Serie) o;
        return id.equals(serie.id) && name.equals(serie.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Serie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
