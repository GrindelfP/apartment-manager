package to.grindelf.apartmentmanager.domain;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class User {

    private String name;
    private String password;
    private UserStatus userStatus;

    public User(@NotNull String name, @NotNull String password) {
        this.name = name;
        this.password = password;
        this.userStatus = UserStatus.JUST_USER;
    }

    public User(@NotNull String name, @NotNull String password, @NotNull UserStatus userStatus) {
        this.name = name;
        this.password = password;
        this.userStatus = userStatus;
    }

    public User(String name) {
        this.name = name;
        this.password = null;
        this.userStatus = UserStatus.JUST_USER;
    }

    public User() {
        this.name = null;
        this.password = null;
        this.userStatus = UserStatus.JUST_USER;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public UserStatus getStatus() {
        return userStatus;
    }

    public void setStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    @NotNull
    @Contract(pure = true)
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' + ", " +
                "password='" + password + '\'' + ", " +
                "userStatus='" + userStatus + '\'' +
                '}'
                ;
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
