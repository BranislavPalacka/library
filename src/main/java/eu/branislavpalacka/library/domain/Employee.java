package eu.branislavpalacka.library.domain;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Objects;

public class Employee {
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
    private Integer active;

    public Employee() {
    }

    public Employee(@NonNull String name, @NonNull String surname, @NonNull String address,
                    @NonNull String email, @NonNull String phoneNumber, @NonNull String password, @Nullable Integer active) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.active = active;
    }

    public int getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id.equals(employee.id) && name.equals(employee.name) && surname.equals(employee.surname)
                && address.equals(employee.address) && email.equals(employee.email) && phoneNumber.equals(employee.phoneNumber)
                && password.equals(employee.password) && active.equals(employee.active);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, address, email, phoneNumber, password,active);
    }


}
