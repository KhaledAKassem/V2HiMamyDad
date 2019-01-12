package medic.esy.es.mamyapp.Activites.model;

public class parent {

   private String id;
    private String name;
    private String gender;
    private String address;
    private String age,email;
    private String password;
    private String phone;
    private String Activity;
    private String Mode;
    private String MorningSnack;
    private String LunchSnack;
    private  String AfternoonSnack;


    public parent(){

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public parent( String id,String password, String name, String gender, String address, String age, String email,String phone,String Activity,String Mode,String MorningSnack, String LunchSnack, String AfternoonSnack) {
        this.id=id;
        this.name = name;
        this.gender = gender;
        this.password=password;
        this.address = address;
        this.age = age;
        this.email = email;
        this.phone=phone;
        this.Activity=Activity;
        this.Mode=Mode;
        this.MorningSnack=MorningSnack;
        this.LunchSnack=LunchSnack;
        this.AfternoonSnack=AfternoonSnack;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMode() {
        return Mode;
    }

    public void setMode(String mode) {
        Mode = mode;
    }

    public String getMorningSnack() {
        return MorningSnack;
    }


    public String getLunchSnack() {
        return LunchSnack;
    }

    public void setMorningSnack(String morningSnack) {
        MorningSnack = morningSnack;
    }

    public void setLunchSnack(String lunchSnack) {
        LunchSnack = lunchSnack;
    }


    public String getAfternoonSnack() {
        return AfternoonSnack;
    }

    public void setAfternoonSnack(String afternoonSnack) {
        AfternoonSnack = afternoonSnack;
    }

    public String getActivity() {
        return Activity;
    }

    public void setActivity(String activity) {
        Activity = activity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }


}
