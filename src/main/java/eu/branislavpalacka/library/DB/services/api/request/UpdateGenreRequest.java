package eu.branislavpalacka.library.DB.services.api.request;

import org.springframework.lang.NonNull;

import java.util.Objects;

public class UpdateGenreRequest {
    @NonNull
    private String name;

    public UpdateGenreRequest() {
    }

    public UpdateGenreRequest(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateGenreRequest that = (UpdateGenreRequest) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
