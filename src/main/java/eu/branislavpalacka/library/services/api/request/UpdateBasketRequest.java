package eu.branislavpalacka.library.services.api.request;

import org.springframework.lang.NonNull;

import java.sql.Timestamp;
import java.util.Objects;

public class UpdateBasketRequest {
    @NonNull
    private Timestamp lastAdd;

    public UpdateBasketRequest() {
    }

    public UpdateBasketRequest(@NonNull Timestamp lastAdd) {
        this.lastAdd = lastAdd;
    }

    @NonNull
    public Timestamp getLastAdd() {
        return lastAdd;
    }

    public void setLastAdd(@NonNull Timestamp lastAdd) {
        this.lastAdd = lastAdd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateBasketRequest that = (UpdateBasketRequest) o;
        return lastAdd.getTime() == that.getLastAdd().getTime();
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastAdd);
    }
}
