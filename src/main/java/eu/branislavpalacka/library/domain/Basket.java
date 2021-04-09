package eu.branislavpalacka.library.domain;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;

public class Basket {
    @NonNull
    private Integer id;
    @Nullable
    private Timestamp lastAdd;

    public Basket() {
    }

    public Basket(@NonNull Integer id) {
        this.id = id;
        this.lastAdd = Timestamp.from(Instant.now());
    }

    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    @Nullable
    public Timestamp getLastAdd() {
        return lastAdd;
    }

    public void setLastAdd(@Nullable Timestamp lastAdd) {
        this.lastAdd = lastAdd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Basket basket = (Basket) o;
        return id.equals(basket.id) && lastAdd.getTime() == basket.getLastAdd().getTime();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastAdd);
    }
}
