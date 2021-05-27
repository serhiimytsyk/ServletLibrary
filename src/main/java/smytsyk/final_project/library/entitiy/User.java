package smytsyk.final_project.library.entitiy;

import java.time.LocalDate;

/**
 * User
 * Contains login, password, and personal information
 */
public class User implements Entity {
    private int id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private int roleId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private User user = new User();

        public User build() {
            return user;
        }

        public Builder id(int id) {
            user.id = id;
            return this;
        }

        public Builder login(String login) {
            user.login = login;
            return this;
        }

        public Builder password(String password) {
            user.password = password;
            return this;
        }

        public Builder firstName(String firstName) {
            user.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            user.lastName = lastName;
            return this;
        }

        public Builder email(String email) {
            user.email = email;
            return this;
        }

        public Builder roleId(int roleId) {
            user.roleId = roleId;
            return this;
        }
    }
}
