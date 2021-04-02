package eu.branislavpalacka.library.domain;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;

public class Order {
    @NonNull
    private Integer id;
    @NonNull
    private Integer userID;
    @NonNull
    private Integer orderStateId;
    @NonNull
    private Timestamp createdAt;
    @Nullable
    private Timestamp readyAt;
    @Nullable
    private Timestamp borrowedAt;
    @Nullable
    private Timestamp endedAt;
    @Nullable
    private Integer borrowedBy;
    @Nullable
    private Integer endedBy;

    public Order() {
    }

    public Order(@NonNull Integer userID, @NonNull Integer orderStateId) {
        this.userID = userID;
        this.orderStateId = orderStateId;
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
    public Integer getUserID() {
        return userID;
    }

    public void setUserID(@NonNull Integer userID) {
        this.userID = userID;
    }

    @NonNull
    public Integer getOrderStateId() {
        return orderStateId;
    }

    public void setOrderStateId(@NonNull Integer orderStateId) {
        this.orderStateId = orderStateId;
    }

    @NonNull
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(@NonNull Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Nullable
    public Timestamp getReadyAt() {
        return readyAt;
    }

    public void setReadyAt(@Nullable Timestamp readyAt) {
        this.readyAt = readyAt;
    }

    @Nullable
    public Timestamp getBorrowedAt() {
        return borrowedAt;
    }

    public void setBorrowedAt(@Nullable Timestamp borrowedAt) {
        this.borrowedAt = borrowedAt;
    }

    @Nullable
    public Timestamp getEndedAt() {
        return endedAt;
    }

    public void setEndedAt(@Nullable Timestamp endedAt) {
        this.endedAt = endedAt;
    }

    @Nullable
    public Integer getBorrowedBy() {
        return borrowedBy;
    }

    public void setBorrowedBy(@Nullable Integer borrowedBy) {
        this.borrowedBy = borrowedBy;
    }

    @Nullable
    public Integer getEndedBy() {
        return endedBy;
    }

    public void setEndedBy(@Nullable Integer endedBy) {
        this.endedBy = endedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id.equals(order.id) && userID.equals(order.userID) && orderStateId.equals(order.orderStateId) &&
                createdAt.getTime() == order.getCreatedAt().getTime()
                && Objects.equals(readyAt, order.readyAt) && Objects.equals(borrowedAt, order.borrowedAt)
                && Objects.equals(endedAt, order.endedAt) && Objects.equals(borrowedBy, order.borrowedBy) && Objects.equals(endedBy, order.endedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userID, orderStateId, createdAt, readyAt, borrowedAt, endedAt, borrowedBy, endedBy);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userID=" + userID +
                ", orderStateId=" + orderStateId +
                ", createdAt=" + createdAt +
                ", readyAt=" + readyAt +
                ", borrowedAt=" + borrowedAt +
                ", endedAt=" + endedAt +
                ", borrowedBy=" + borrowedBy +
                ", endedBy=" + endedBy +
                '}';
    }
}
