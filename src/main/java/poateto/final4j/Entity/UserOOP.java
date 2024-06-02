package poateto.final4j.Entity;

public class UserOOP {
    private String email;
    private String pwd;

    public UserOOP(String email, String pwd) {
        this.email = email;
        this.pwd = pwd;
    }

    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return pwd;
    }
}
