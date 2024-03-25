package org.gonzalez;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class GUI extends JFrame {
    private static final CurrencyService c = new CurrencyService(new RemoteCurrentServicePort("b845daf4ed306e660d257548134a6e3c"));
    //up
    private static final JLabel from = new JLabel("From");
    private static final JTextField fromTextField = new JTextField("chf");
    private static final JLabel to = new JLabel("to");
    private static final JTextField toTextField = new JTextField("usd");
    //middle
    private static final JTextField fromInput = new JTextField();
    private static final JLabel fromString = new JLabel(fromTextField.getText());
    private static final JLabel text = new JLabel("<====>");
    private static final JLabel toLabel = new JLabel("xxxx");
    private static final JLabel toString = new JLabel(toTextField.getText());
    private static final JButton convert = new JButton("convert");
    private static final JLabel change = new JLabel("(-.- CHF -> -.- USD)");
    //down
    private static final JLabel exchanges = new JLabel("1CHF = -.- EUR | -.- USD | -.- COP | -.- CAD");

    public GUI() {
        setTitle("Currency converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(400,175));

        JPanel panelUp = new JPanel();

        panelUp.add(from);
        panelUp.add(fromTextField);
        panelUp.add(to);
        panelUp.add(toTextField);

        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new BorderLayout());
        JPanel panelCenterTop = new JPanel();
        JPanel panelCenterDown = new JPanel();

        panelCenterDown.add(convert);
        panelCenterDown.add(change);

        panelCenterTop.add(fromInput);
        panelCenterTop.add(fromString);
        panelCenterTop.add(text);
        panelCenterTop.add(toLabel);
        panelCenterTop.add(toString);

        panelCenter.add(panelCenterTop, BorderLayout.NORTH);
        panelCenter.add(panelCenterDown, BorderLayout.SOUTH);

        JPanel panelDown = new JPanel();

        panelDown.add(exchanges);

        getContentPane().add(BorderLayout.NORTH, panelUp);
        getContentPane().add(BorderLayout.CENTER, panelCenter);
        getContentPane().add(BorderLayout.SOUTH, panelDown);

        fromTextField.setPreferredSize(new Dimension(35, 24));
        toTextField.setPreferredSize(new Dimension(35, 24));
        fromInput.setPreferredSize(new Dimension(75, 24));
        setVisible(true);

    }

    public static void main(String[] args) {
        new GUI();
        load();

        convert.addActionListener(e -> {
            try {
                String fromText = fromTextField.getText();
                String toText = toTextField.getText();
                String input = fromInput.getText();
                c.checkIfExists(fromText, toText);
                toLabel.setText(String.valueOf(round(c.convertAmount(fromText, toText, fromStringToDouble(input)))));
                loadSingularChange(fromText, toText);
            } catch (InvalidCurrencyException ex) {
                JOptionPane.showConfirmDialog(null,
                        ex.getMessage() + "\n Try again with a different value",
                        "Invalid Currency",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.WARNING_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showConfirmDialog(null,
                    ex.getMessage() + "\n Try again with a different value",
                    "Invalid Value",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            }
        });


    }

    public static void load() {
        loadExchanges();
        loadSingularChange("CHF", "USD");
    }

    public static void loadExchanges() {
        Map<String, Double> m = c.convertMoreAmounts("chf", "eur", "usd", "cop", "cad");
        String formattedString = "1CHF ≈ %s EUR | %s USD | %s COP | %s CAD";
        String res = String.format(formattedString, String.format("%.2f", m.get("eur")), String.format("%.2f", m.get("usd")),String.format("%.2f", m.get("cop")),String.format("%.2f", m.get("cad")));

        exchanges.setText(res);
    }

    public static void loadSingularChange(String from, String to) {
        double amount = c.convertAmount(from, to, 1);
        change.setText(String.format("( 1 %s -> %s %s )", from, round(amount), to));
    }

    public static double round(double n) {
        return (double) ((int) (n * 1000)) / 1000;
    }

    public static double fromStringToDouble(String d) {
        try {
            double n = Double.parseDouble(d);
            if (n < 0) throw new IllegalArgumentException("The number should be positive");
            return n;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("You need to put a valid number");
        }
    }



}
