
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColumnChartUI extends JFrame {

    private JPanel chartPanel;
    private JButton generateChartButton;

    public ColumnChartUI() {
        setTitle("Column Chart");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        chartPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Vẽ biểu đồ theo cột ở đây
                drawColumnChart(g);
            }
        };

        generateChartButton = new JButton("Generate Chart");
        generateChartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Khi nút được nhấn, vẽ lại biểu đồ
                chartPanel.repaint();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(generateChartButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(chartPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    private void drawColumnChart(Graphics g) {
        // Dữ liệu thử
        int[] data = {100, 50, 200, 80, 10};
        String[] labels = {"Label 1", "Label 2", "Label 3", "Label 4", "Label 5"};

        // Chiều rộng và khoảng cách giữa các cột
        int columnWidth = 100;
        int columnGap = 20;

        // Tọa độ x ban đầu của cột đầu tiên
        int startX = 100;

        // Vẽ các cột
        for (int i = 0; i < data.length; i++) {
            // Tính toán tọa độ x và y của cột
            int x = startX + (columnWidth + columnGap) * i;
            int y = 300 - data[i]; // Chiều cao của cột được tính từ đỉnh xuống

            // Chọn màu và vẽ cột
            switch (i % 3) {
                case 0:
                    g.setColor(Color.blue);
                    break;
                case 1:
                    g.setColor(Color.red);
                    break;
                case 2:
                    g.setColor(Color.green);
                    break;
            }
            g.fillRect(x, y, columnWidth, data[i]);
            // Vẽ nhãn dưới cột
            g.setColor(Color.black);
            FontMetrics fm = g.getFontMetrics();
            int labelWidth = fm.stringWidth(labels[i]);
            g.drawString(labels[i], x + (columnWidth - labelWidth) / 2, 320);
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ColumnChartUI chartUI = new ColumnChartUI();
                chartUI.setVisible(true);
            }
        });
    }
}
