package eu.branislavpalacka.library.services.api.request;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Objects;

public class UpdateBookRequest {
    @NonNull
    private String name;
    @Nullable
    private String description;
    @Nullable
    private String image;
    @NonNull
    private int statusID;
    @Nullable
    private int basketId;
    @Nullable
    private int orderID;

    public UpdateBookRequest(@NonNull String name, @Nullable String description, @Nullable String image, int statusID, int basketId, int orderID) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.statusID = statusID;
        this.basketId = basketId;
        this.orderID = orderID;
    }

    public int getOrderID() {
        return orderID;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    @Nullable
    public String getImage() {
        return image;
    }

    public int getStatusID() {
        return statusID;
    }

    public int getBasketId() {
        return basketId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateBookRequest that = (UpdateBookRequest) o;
        return statusID == that.statusID && basketId == that.basketId && orderID == that.orderID && name.equals(that.name) && Objects.equals(description, that.description) && Objects.equals(image, that.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, image, statusID, basketId, orderID);
    }
}
