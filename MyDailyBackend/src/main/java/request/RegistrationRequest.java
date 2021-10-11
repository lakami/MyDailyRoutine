package request;


import javax.validation.constraints.NotBlank;

public class RegistrationRequest {
    @NotBlank
    private String login;
    @NotBlank
    private String username;
    @NotBlank
    private String email;
    @NotBlank
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RegistrationRequest(String login, String username, String email, String password) {
        this.login = login;
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
