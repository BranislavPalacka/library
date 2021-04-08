package eu.branislavpalacka.library.domain;

import org.springframework.lang.NonNull;

import java.util.Objects;

public class StatusOfBooks {
    @NonNull
    private Integer id;
    @NonNull
    private String name;

    public StatusOfBooks() {
    }

    public StatusOfBooks(@NonNull String name) {
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
        StatusOfBooks that = (StatusOfBooks) o;
        return id.equals(that.id) && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

