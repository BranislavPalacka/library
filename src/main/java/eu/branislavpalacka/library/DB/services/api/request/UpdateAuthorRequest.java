package eu.branislavpalacka.library.DB.services.api.request;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Objects;

public class UpdateAuthorRequest {
    @NonNull
    private String name;
    @NonNull
    private String surname;
    @Nullable
    private String description;
    @Nullable
    private String image;

    public UpdateAuthorRequest(@NonNull String name, @NonNull String surname, @Nullable String description, @Nullable String image) {
        this.name = name;
        this.surname = surname;
        this.description = description;
        this.image = image;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public String getSurname() {
        return surname;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    @Nullable
    public String getImage() {
        return image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateAuthorRequest that = (UpdateAuthorRequest) o;
        return name.equals(that.name) && surname.equals(that.surname) && Objects.equals(description, that.description) && Objects.equals(image, that.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, description, image);
    }
}
