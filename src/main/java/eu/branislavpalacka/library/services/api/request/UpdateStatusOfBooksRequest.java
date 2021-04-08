package eu.branislavpalacka.library.services.api.request;

import org.springframework.lang.NonNull;

import java.util.Objects;

public class UpdateStatusOfBooksRequest {
    @NonNull
    private String name;

    public UpdateStatusOfBooksRequest(@NonNull String name) {
        this.name = name;
    }

    public UpdateStatusOfBooksRequest() {
    }

    @NonNull
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateStatusOfBooksRequest that = (UpdateStatusOfBooksRequest) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
