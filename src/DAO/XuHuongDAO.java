package DAO;

import Database.JDCBCUtil;
import Model.LoaiGhe;
import View.ADMIN;
import View.NguoiDung;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XuHuongDAO {
	private LichBayDAO lichBayDAO;
	private NguoiDung nguoiDung;
	private ADMIN admin;

	public XuHuongDAO() {
		this.lichBayDAO = new LichBayDAO();
	}

	public LoaiGhe xuHuongDatQuaCacNam(String year) {
		String[] macb = laydanhsachcacchuyenbaycungnam(year);
		int eco = 0;
		int busin = 0;
		int first = 0;
		for (String ma : macb) {
			try {
				Connection con = JDCBCUtil.getConnection();
				PreparedStatement pst = con.prepareStatement("SELECT loaiGhe FROM " + ma);
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					if (rs.getString("loaiGhe").equals("EconomyClass")) {
						eco++;
					} else if (rs.getString("loaiGhe").equals("BusinessClass")) {
						busin++;
					} else if (rs.getString("loaiGhe").equals("FirstClass")) {
						first++;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
				;
			}
		}
		return new LoaiGhe(eco, busin, first);
	}

	public String[] laydanhsachcacchuyenbaycungnam(String year) {
		List<String> ketqua = new ArrayList<>();
		try {
			Connection con = JDCBCUtil.getConnection();
			PreparedStatement pst = con.prepareStatement(
					"SELECT maChuyenBay FROM luutru WHERE SUBSTRING_INDEX(ngayBay, '/', -1) = '" + year + " '");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				ketqua.add(rs.getString("maChuyenBay"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua.toArray(new String[0]);
	}

	public String[] laynam() {
		List<String> ketqua = new ArrayList<>();
		try {
			Connection con = JDCBCUtil.getConnection();
			PreparedStatement pst = con.prepareStatement("SELECT SUBSTRING_INDEX(ngayBay, '/', -1) AS nam FROM luutru");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				ketqua.add(rs.getString("nam"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua.toArray(new String[0]);
	}

	// --//
	// Lay hang bay
	public String[] layHangBay() {
		List<String> hangBayList = new ArrayList<>();

		try {
			Connection con = JDCBCUtil.getConnection();
			PreparedStatement pst = con.prepareStatement(
					"SELECT DISTINCT hangBay FROM lichBay INNER JOIN dsKhachHang ON lichBay.maChuyenBay = dsKhachHang.maChuyenBay");
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				String hangBay = rs.getString("hangBay");
				hangBayList.add(hangBay);
			}

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return hangBayList.toArray(new String[0]);
	}

	// ==//
	// Đếm
	public int demSoLuongHangBay(String hangBay) {
		int soLuong = 0;
		try {
			Connection con = JDCBCUtil.getConnection();
			PreparedStatement pst = con.prepareStatement("SELECT COUNT(*) AS total FROM dsKhachHang"
					+ " INNER JOIN lichBay ON dsKhachHang.maChuyenBay = lichBay.maChuyenBay WHERE lichBay.hangBay = ?");
			pst.setString(1, hangBay);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				soLuong = rs.getInt("total");
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return soLuong;
	}
	
	
	//--//
	//TK Doanh thu
	public Map<Integer, Double> thongKeDoanhThuTheoNam() {
        Map<Integer, Double> doanhThuTheoNam = new HashMap<>();
        try {
            Connection con = JDCBCUtil.getConnection();
            PreparedStatement pst = con.prepareStatement(
                "SELECT SUBSTRING_INDEX(lb.ngayBay, '/', -1) AS nam,\n"
                + "       SUM(CASE WHEN kh.loaiGhe = 'EconomyClass' THEN 80\n"
                + "                WHEN kh.loaiGhe = 'BusinessClass' THEN 500\n"
                + "                WHEN kh.loaiGhe = 'FirstClass' THEN 150\n"
                + "                ELSE 0 END) AS DoanhThu\n"
                + "FROM lichBay lb\n"
                + "INNER JOIN dsKhachHang kh ON lb.MaChuyenbay = kh.maChuyenBay \n"
                + "WHERE kh.tinhTrang = 'Đã xác nhận'\n"
                + "GROUP BY Nam;"
            );
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int nam = rs.getInt("Nam");
                double doanhThu = rs.getDouble("DoanhThu");
                doanhThuTheoNam.put(nam, doanhThu);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doanhThuTheoNam;
    }

}
