package me.calebclifton;

import me.calebclifton.database.Database;
import me.calebclifton.database.MySQLLogin;

import javax.swing.*;

public class FuturePredictor {
    private static JFrame frame;
    private static JPanel panel;
    private static String name;
    private static JTextField nameField;

    public static Database database;

    public static void main(String[] args) {
        frame = createFrame();
        panel = createPanel();
        frame.add(panel);
        frame.setVisible(true);

        MySQLLogin.init();
        database = new Database();
        database.openConnection();
    }

    private static JFrame createFrame() {
        JFrame frame = new JFrame("Future Predictor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 550);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        return frame;
    }

    private static JPanel createPanel() {
        JPanel panel = new JPanel();
        FormBuilder.init(panel)
                .add(createHeaderPanel(), 1, 1, 1)
                .addBreak(2)
                .add(createBodyPanel(), 1, 3, 1)
                .addBreak(4)
                .add(createFooterPanel(), 1, 5, 1);

        return panel;
    }

    private static JPanel createHeaderPanel() {
        nameField = new JTextField(15);

        JPanel panel = new JPanel();
        FormBuilder.init(panel)
                .add(new JLabel("Name:"), 1, 1, 5)
                .add(nameField, 1, 2, 5);

        return panel;
    }

    private static JPanel createBodyPanel() {
        JPanel panel;

        if (name != null && name.length() > 0)
            panel = FormBuilder.getBodyPanel(name);
        else
            return new JPanel();

        return panel;
    }

    private static JPanel createFooterPanel() {
        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(e -> updateBodyPanelWithName(nameField.getText()));

        JPanel panel = new JPanel();
        FormBuilder.init(panel)
                .add(calculateButton, 1, 1, 5);

        return panel;
    }

    private static void updateBodyPanelWithName(String newName) {
        name = newName.trim();
        frame.remove(panel);
        panel = createPanel();
        frame.add(panel);
        frame.setVisible(true);
    }
}
