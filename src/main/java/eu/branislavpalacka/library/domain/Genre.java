package eu.branislavpalacka.library.domain;

import org.springframework.lang.NonNull;

import java.util.Objects;

public class Genre {
    @NonNull
    private Integer id;
    @NonNull
    private String name;

    public Genre() {
    }

    public Genre(@NonNull String name) {
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
        Genre serie = (Genre) o;
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
