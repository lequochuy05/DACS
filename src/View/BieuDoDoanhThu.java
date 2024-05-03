package View;

import javax.swing.*;
import java.awt.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class BieuDoDoanhThu extends JDialog {
    public BieuDoDoanhThu(Frame parent) {
        super(parent, "Biểu đồ Doanh thu", true); // Tham số true để hiển thị JDialog modal

        // Tạo dataset cho biểu đồ tròn
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Sản phẩm A", 10000);
        dataset.setValue("Sản phẩm B", 20000);
        dataset.setValue("Sản phẩm C", 30000);

        // Tạo biểu đồ tròn từ dataset
        JFreeChart pieChart = ChartFactory.createPieChart(
                "Biểu đồ Doanh thu",
                dataset,
                true, // Hiển thị legend
                true, // Hiển thị tooltips
                false // Không có URL
        );

        // Tạo Panel chứa biểu đồ
        ChartPanel chartPanel = new ChartPanel(pieChart);
        getContentPane().add(chartPanel, BorderLayout.CENTER);

        setSize(600, 400);
        setLocationRelativeTo(parent); // Hiển thị JDialog ở giữa JFrame chính
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Test");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JButton btn = new JButton("Show Dialog");
            btn.addActionListener(e -> {
                BieuDoDoanhThu dialog = new BieuDoDoanhThu(frame);
                dialog.setVisible(true);
            });
            frame.getContentPane().add(btn);
            frame.pack();
            frame.setVisible(true);
        });
    }
}
