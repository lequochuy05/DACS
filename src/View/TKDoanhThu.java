package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Map;

import org.jfree.chart.*;
import org.jfree.data.category.DefaultCategoryDataset;

import DAO.XuHuongDAO;

public class TKDoanhThu extends JFrame {
	private ADMIN ad;
	private XuHuongDAO xuHuongDAO;

	public TKDoanhThu(ADMIN ad) throws HeadlessException {
		this.ad = ad;
		this.xuHuongDAO = new XuHuongDAO();
	}

	public TKDoanhThu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 522);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		setLocationRelativeTo(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.addMouseListener((MouseListener) new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				try {
					ad = new ADMIN();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				ad.setVisible(true);
			}
		});
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\ASUS\\Documents\\Java\\DoAnCoSo1\\src\\View\\return.png"));
		lblNewLabel.setBounds(0, 447, 68, 38);
		contentPane.add(lblNewLabel);
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		// Thống kê doanh thu theo năm
		this.xuHuongDAO = new XuHuongDAO();
		Map<Integer, Double> doanhThuTheoNam = xuHuongDAO.thongKeDoanhThuTheoNam();
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int nam : doanhThuTheoNam.keySet()) {
			double doanhThu = doanhThuTheoNam.get(nam);
			dataset.addValue(doanhThu, "Doanh thu", String.valueOf(nam));
		}

		// Tạo biểu đồ cột
		JFreeChart barChart = ChartFactory.createBarChart("Thống kê doanh thu theo năm", "Năm", "Doanh thu ($)",
				dataset);

		// Tạo panel chứa biểu đồ
		ChartPanel chartPanel = new ChartPanel(barChart);
		panel.add(chartPanel, BorderLayout.CENTER);

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TKDoanhThu frame = new TKDoanhThu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
