package eu.branislavpalacka.library.domain;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;

public class User {
    @NonNull
    private Integer id;
    @NonNull
    private String name;
    @NonNull
    private String surname;
    @NonNull
    private String address;
    @NonNull
    private String email;
    @NonNull
    private String phoneNumber;
    @NonNull
    private String password;
    @Nullable
    private Integer borrowedBooksNumber;
    @Nullable
    private Integer basketId;
    @Nullable
    private Integer accountApprovedBy;
    @NonNull
    private Timestamp createdAt;

    public User() {
    }

    public User(@NonNull Integer id, @NonNull String name, @NonNull String surname, @NonNull String address,
                @NonNull String email, @NonNull String phoneNumber, @NonNull String password,
                @Nullable Integer borrowedBooksNumber, @Nullable Integer basketId, @Nullable Integer accountApprovedBy) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.borrowedBooksNumber = borrowedBooksNumber;
        this.basketId = basketId;
        this.accountApprovedBy = accountApprovedBy;
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

    @NonNull
    public String getAddress() {
        return address;
    }

    public void setAddress(@NonNull String address) {
        this.address = address;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    @NonNull
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@NonNull String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    @Nullable
    public Integer getBorrowedBooksNumber() {
        return borrowedBooksNumber;
    }

    public void setBorrowedBooksNumber(@Nullable Integer borrowedBooksNumber) {
        this.borrowedBooksNumber = borrowedBooksNumber;
    }

    @Nullable
    public Integer getBasketId() {
        return basketId;
    }

    public void setBasketId(@Nullable Integer basketId) {
        this.basketId = basketId;
    }

    @Nullable
    public Integer getAccountApprovedBy() {
        return accountApprovedBy;
    }

    public void setAccountApprovedBy(@Nullable Integer accountApprovedBy) {
        this.accountApprovedBy = accountApprovedBy;
    }

    @NonNull
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(@NonNull Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) && name.equals(user.name) && surname.equals(user.surname) && address.equals(user.address) &&
                email.equals(user.email) && phoneNumber.equals(user.phoneNumber) && password.equals(user.password) &&
                Objects.equals(borrowedBooksNumber, user.borrowedBooksNumber) && Objects.equals(basketId, user.basketId) &&
                Objects.equals(accountApprovedBy, user.accountApprovedBy) && createdAt.getTime()==user.getCreatedAt().getTime();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, address, email, phoneNumber, password, borrowedBooksNumber, basketId,
                accountApprovedBy, createdAt);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", borrowedBooksNumber=" + borrowedBooksNumber +
                ", basketId=" + basketId +
                ", accountApprovedBy=" + accountApprovedBy +
                ", createdAt=" + createdAt +
                '}';
    }
}
