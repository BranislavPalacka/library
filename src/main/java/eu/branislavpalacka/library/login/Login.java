package eu.branislavpalacka.library.login;

import org.springframework.lang.Nullable;

public class Login {
    @Nullable
    private String email;

    @Nullable
    private String password;

    public Login(@Nullable String email, @Nullable String password) {
        this.email = email;
        this.password = password;
    }

    public Login() {
    }

    @Nullable
    public String getEmail() {
        return email;
    }

    public void setEmail(@Nullable String email) {
        this.email = email;
    }

    @Nullable
    public String getPassword() {
        return password;
    }

    public void setPassword(@Nullable String password) {
        this.password = password;
    }
}
