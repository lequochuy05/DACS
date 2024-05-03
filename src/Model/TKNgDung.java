package Model;

public class TKNgDung {
    private String taiKhoanNG;
    private String matKhauNG;
    public TKNgDung(){

    }
    public TKNgDung(String taiKhoanNG, String matKhauNG){
        this.taiKhoanNG = taiKhoanNG;
        this.matKhauNG = matKhauNG;
    }

    public void setMatKhauNG(String matKhauNG) {
        this.matKhauNG = matKhauNG;
    }

    public void setTaiKhoanNG(String taiKhoanNG) {
        this.taiKhoanNG = taiKhoanNG;
    }

    public String getMatKhauNG() {
        return matKhauNG;
    }

    public String getTaiKhoanNG() {
        return taiKhoanNG;
    }
}
