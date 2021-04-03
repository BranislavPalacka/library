package eu.branislavpalacka.library.services.api.request;

import org.springframework.lang.NonNull;

import java.util.Objects;

public class UpdateSerieRequest {
    @NonNull
    private String name;

    public UpdateSerieRequest(@NonNull String name) {
        this.name = name;
    }

    public UpdateSerieRequest() {
    }

    @NonNull
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateSerieRequest that = (UpdateSerieRequest) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
