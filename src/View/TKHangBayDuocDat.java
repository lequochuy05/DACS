package View;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;

import DAO.XuHuongDAO;

public class TKHangBayDuocDat extends JFrame {
    private ADMIN ad;
    private XuHuongDAO xuHuongDAO; 

    public TKHangBayDuocDat(ADMIN ad) throws HeadlessException {
        this.ad = ad;
        this.xuHuongDAO = new XuHuongDAO(); 
    }

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TKHangBayDuocDat frame = new TKHangBayDuocDat();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public TKHangBayDuocDat() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 850, 522);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setLocationRelativeTo(null);
        setContentPane(contentPane);
        contentPane.setLayout(null);
        this.xuHuongDAO = new XuHuongDAO(); 

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
        panel.setBackground(new Color(192, 192, 192));
        panel.setBounds(10, 22, 816, 374);
        contentPane.add(panel);
        panel.setLayout(new BorderLayout());
 
        
      
        // Tạo biểu đồ cột
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        // Thêm dữ liệu từ danh sách hãng bay vào biểu đồ
        String[] hangBayArr = xuHuongDAO.layHangBay();
        for (String hangBay : hangBayArr) {
        	int soLuongHangBay = xuHuongDAO.demSoLuongHangBay(hangBay);
            dataset.addValue(soLuongHangBay, hangBay, ""); 
        }
    
        JFreeChart barChart = ChartFactory.createBarChart(
                "Biểu đồ thể hiện các hãng bay được đặt ",
                "Hãng bay",
                "Số Lượng",
                dataset
        );
        


        ChartPanel chartPanel = new ChartPanel(barChart);
        panel.add(chartPanel, BorderLayout.CENTER);
        

        // Thay đổi kích thước của các cột
        CategoryPlot plot = barChart.getCategoryPlot();
        plot.getDomainAxis().setCategoryMargin(0.1); // Điều chỉnh khoảng cách giữa các cột
    }
}

