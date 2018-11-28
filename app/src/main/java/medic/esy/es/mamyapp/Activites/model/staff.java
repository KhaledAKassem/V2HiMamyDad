package medic.esy.es.mamyapp.Activites.model;

public class staff {


   private String staffName,staffGender,staffHomePhone,staffWorkPhone,staffAddress,staffCellPhone;

   public staff(){

   }


    public staff(String staffName, String staffGender, String staffHomePhone, String staffWorkPhone, String staffAddress,String staffCellPhone) {

        this.staffName = staffName;
        this.staffGender = staffGender;
        this.staffHomePhone = staffHomePhone;
        this.staffWorkPhone = staffWorkPhone;
        this.staffAddress = staffAddress;
        this.staffCellPhone=staffCellPhone;
    }

    public String getStaffCellPhone() {
        return staffCellPhone;
    }

    public void setStaffCellPhone(String staffCellPhone) {
        this.staffCellPhone = staffCellPhone;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffGender() {
        return staffGender;
    }

    public void setStaffGender(String staffGender) {
        this.staffGender = staffGender;
    }

    public String getStaffHomePhone() {
        return staffHomePhone;
    }

    public void setStaffHomePhone(String staffHomePhone) {
        this.staffHomePhone = staffHomePhone;
    }

    public String getStaffWorkPhone() {
        return staffWorkPhone;
    }

    public void setStaffWorkPhone(String staffWorkPhone) {
        this.staffWorkPhone = staffWorkPhone;
    }

    public String getStaffAddress() {
        return staffAddress;
    }

    public void setStaffAddress(String staffAddress) {
        this.staffAddress = staffAddress;
    }
}
