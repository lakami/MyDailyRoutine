package request;

import javax.persistence.Column;

public class UserRequest {
    private String userName;

    private String userLabel;

    private String password;

    public UserRequest() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLabel() {
        return userLabel;
    }

    public void setUserLabel(String userLabel) {
        this.userLabel = userLabel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRequest(String userName, String userLabel, String password) {
        this.userName = userName;
        this.userLabel = userLabel;
        this.password = password;
    }
}
