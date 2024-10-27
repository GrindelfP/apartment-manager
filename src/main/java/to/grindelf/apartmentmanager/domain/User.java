package to.grindelf.apartmentmanager.domain;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class User {

    private String name;
    private String password;
    private Status status;

    public User(@NotNull String name, @NotNull String password) {
        this.name = name;
        this.password = password;
        this.status = Status.JUST_USER;
    }

    public User(@NotNull String name, @NotNull String password, @NotNull Status status) {
        this.name = name;
        this.password = password;
        this.status = status;
    }

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @NotNull
    @Contract(pure = true)
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User)) return false;
        User userAsUser = (User) o;
        return (Objects.equals(this.name, userAsUser.name) && Objects.equals(this.password, userAsUser.password));
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
