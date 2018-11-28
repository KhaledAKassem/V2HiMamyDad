package medic.esy.es.mamyapp.Activites.staff;

public class staffModel
{

    private Long email;
    private String password;
    public  staffModel(){
    }

    public staffModel(Long email, String password) {
        this.email = email;
        this.password = password;
    }

    public Long getEmail() {
        return email;
    }

    public void setEmail(Long email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
