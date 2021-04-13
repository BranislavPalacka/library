package eu.branislavpalacka.library.domain;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;

public class Book {
    @NonNull
    private Integer id;
    @NonNull
    private String name;
    @Nullable
    private String description;
    @Nullable
    private String image;
    @NonNull
    private int statusID;
    @NonNull
    private int addBy;
    @NonNull
    private Timestamp createdAt;
    @Nullable
    private int basketID;
    @Nullable
    private int orderID;

    public Book() {
    }

    public Book(@NonNull String name, @Nullable String description, @Nullable String image, int statusID, int addBy, int basketID, int orderID) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.statusID = statusID;
        this.addBy = addBy;
        this.createdAt = Timestamp.from(Instant.now());
        this.basketID = basketID;
        this.orderID = orderID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
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

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    public int getAddBy() {
        return addBy;
    }

    public void setAddBy(int addBy) {
        this.addBy = addBy;
    }

    @NonNull
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(@NonNull Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public int getBasketID() {
        return basketID;
    }

    public void setBasketID(int basketID) {
        this.basketID = basketID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return statusID == book.statusID && addBy == book.addBy && basketID == book.basketID && orderID == book.orderID
                && id.equals(book.id) && name.equals(book.name) && Objects.equals(description, book.description)
                && Objects.equals(image, book.image) && createdAt.getTime()==book.getCreatedAt().getTime();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, image, statusID, addBy, createdAt, basketID, orderID);
    }
}
