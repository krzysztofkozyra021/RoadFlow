package src.main.java.pl.roadflow.ui.screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StartingMenu extends JFrame {
    public StartingMenu() {
        setTitle("RoadFlow");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        add(new LeftMenuPanel(), BorderLayout.WEST);
        add(new RightImagePanel(), BorderLayout.CENTER);

        GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        if (device.isFullScreenSupported()) {
            this.setUndecorated(true);
            device.setFullScreenWindow(this);
        }

        setVisible(true);
    }
}

class LeftMenuPanel extends JPanel {
    public LeftMenuPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setPreferredSize(new Dimension(screenSize.width / 4, 0));
        setBackground(new Color(0, 0, 0, 255));

        // Logo panel
        JPanel logoPanel = new JPanel();
        logoPanel.setOpaque(false);
        logoPanel.setLayout(new BoxLayout(logoPanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("RoadFlow");
        title.setFont(new Font("Arial", Font.BOLD, 48));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        logoPanel.add(Box.createVerticalStrut(50));
        logoPanel.add(title);
        logoPanel.add(Box.createVerticalStrut(20));

        JLabel subtitle = new JLabel("Racing Game");
        subtitle.setFont(new Font("Arial", Font.ITALIC, 18));
        subtitle.setForeground(new Color(200, 200, 200));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoPanel.add(subtitle);

        add(logoPanel);
        add(Box.createVerticalStrut(100));

        // Menu buttons
        add(createStyledButton("Start Game"));
        add(Box.createVerticalStrut(20));
        add(createStyledButton("Settings"));
        add(Box.createVerticalStrut(20));
        add(createStyledButton("About"));
        add(Box.createVerticalStrut(20));
        add(createStyledButton("Exit"));
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                if (getModel().isRollover()) {
                    g2.setColor(new Color(0, 0, 0, 100));
                    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                }

                g2.setColor(getForeground());
                g2.setFont(getFont());
                FontMetrics fm = g2.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(text)) / 2;
                int y = ((getHeight() - fm.getHeight()) / 2) + fm.getAscent();
                g2.drawString(text, x, y);
            }
        };

        button.setPreferredSize(new Dimension(200, 40));
        button.setFont(new Font("Arial", Font.BOLD, 24));
        button.setForeground(new Color(151, 151, 151));
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setForeground(new Color(255, 255, 255));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setForeground(new Color(151, 151, 151));
            }
        });

        switch(text) {
            case "Start Game":
                button.addActionListener(_ -> {
                    new CarSelectScreen();
                    SwingUtilities.getWindowAncestor(button).setVisible(false);
                });
                break;
            case "Settings":
                button.addActionListener(_ -> {
                    // TODO: Implement settings window
                });
                break;
            case "About":
                button.addActionListener(_ -> {
                    // TODO: Implement about window
                });
                break;
            case "Exit":
                button.addActionListener(_ -> System.exit(0));
                break;
        }

        return button;
    }
}

class RightImagePanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        // TODO: Add gameplay image as a right panel
        super.paintComponent(g);
    }
}