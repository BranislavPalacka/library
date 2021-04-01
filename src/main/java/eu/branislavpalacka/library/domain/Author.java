package eu.branislavpalacka.library.domain;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;

public class Author {
    @NonNull
    private Integer id;
    @NonNull
    private String name;
    @NonNull
    private String surname;
    @Nullable
    private String description;
    @Nullable
    private String image;
    @NonNull
    private Timestamp createdAt;
    @NonNull
    private Integer addBy;

    public Author() {
    }

    public Author(@NonNull String name, @NonNull String surname, @Nullable String description, @Nullable String image, @NonNull Integer addBy) {
        this.name = name;
        this.surname = surname;
        this.description = description;
        this.image = image;
        this.addBy = addBy;
        this.createdAt = Timestamp.from(Instant.now());
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

    @NonNull
    public String getSurname() {
        return surname;
    }

    public void setSurname(@NonNull String surname) {
        this.surname = surname;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    public void setDescription(@Nullable String description) {
        this.description = description;
    }

    @Nullable
    public String getImage() {
        return image;
    }

    public void setImage(@Nullable String image) {
        this.image = image;
    }

    @NonNull
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(@NonNull Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @NonNull
    public Integer getAddBy() {
        return addBy;
    }

    public void setAddBy(@NonNull Integer addBy) {
        this.addBy = addBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return id.equals(author.id) && name.equals(author.name) && surname.equals(author.surname)
                && Objects.equals(description, author.description) && Objects.equals(image, author.image)
                && createdAt.getTime()==author.createdAt.getTime() && addBy.equals(author.addBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, description, image, createdAt, addBy);
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", createdAt=" + createdAt +
                ", addBy=" + addBy +
                '}';
    }
}
