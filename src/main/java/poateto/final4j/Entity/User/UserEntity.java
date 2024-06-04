package poateto.final4j.Entity.User;

public class UserEntity implements UserDependency {
    private String email;
    private String pwd;

    public UserEntity() { }
    public UserEntity(String email, String pwd) {
        this.email = email;
        this.pwd = pwd;
    }

    @Override
    public String getEmail() {
        return email;
    }
    @Override
    public String getPwd() {
        return pwd;
    }
}
