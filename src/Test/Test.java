package Test;

import DAO.XuHuongDAO;
import Model.LoaiGhe;

public class Test {
    public static void main(String[] args) {
        XuHuongDAO xh = new XuHuongDAO();
        String[] nb = xh.laynam();
        for(String n : nb) {
            System.out.println(n);
        }
    }
}