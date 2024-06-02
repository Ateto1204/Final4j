package poateto.final4j.Entity;

public class UserCreate extends UserOOP{
    private String name;

    public UserCreate(String email, String name, String pwd) {
        super(email, pwd);
        this.name = name;
    }

    public String getName() { return name; }
}
