package medic.esy.es.mamyapp.Activites.model;

public class upload {


    private String mImageUrl;
    private String date;
    public upload(){

    }

    public upload(String mImageUrl, String date) {
        this.mImageUrl = mImageUrl;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }
}
