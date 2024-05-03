package View;

import DAO.XuHuongDAO;
import Model.LoaiGhe;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;

public class BieuDoXuHuong extends JDialog {
    public BieuDoXuHuong(Frame parent, String nam) {
        super(parent, "Biểu đồ", true);

        XuHuongDAO xh = new XuHuongDAO();
        LoaiGhe loaiGhe = xh.xuHuongDatQuaCacNam(nam);

        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("EconomyClass: " + loaiGhe.getEconomyclass(), loaiGhe.getEconomyclass());
        dataset.setValue("BusinessClass: " + loaiGhe.getBusinessclass(), loaiGhe.getBusinessclass());
        dataset.setValue("FirstClass: " + loaiGhe.getFirstclass(), loaiGhe.getFirstclass());
        JFreeChart freeChart = ChartFactory.createPieChart(
                "Biểu đồ xu hướng đặt vé qua các năm.",
                dataset,
                true,
                true,
                false
        );
        ChartPanel chartPanel = new ChartPanel(freeChart);
        getContentPane().add(chartPanel, BorderLayout.CENTER);

        setSize(600, 400);
        setLocationRelativeTo(parent);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Biểu đồ");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JButton btn = new JButton("Show Dialog");
            btn.addActionListener(e -> { //dÙNG LAMBDA
                BieuDoDoanhThu dialog = new BieuDoDoanhThu(frame);
                dialog.setVisible(true);
            });
            frame.getContentPane().add(btn);
            frame.pack();
            frame.setVisible(true);
        });
    }
}
