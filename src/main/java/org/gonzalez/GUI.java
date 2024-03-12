package org.gonzalez;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class GUI extends JFrame {
    private static final CurrencyService c = new CurrencyService(new RemoteCurrentServicePort("13e4351b9d6e76b6f390c211b57315d4"));
    //up
    private static final JLabel from = new JLabel("From");
    private static JTextField fromTextField = new JTextField();
    private static final JLabel to = new JLabel("to");
    private static JTextField toTextField = new JTextField();
    //middle
    private static JTextField fromInput = new JTextField();
    private static JLabel fromString = new JLabel(fromTextField.getText());
    private static final JLabel text = new JLabel("<====>");
    private static JLabel toLabel = new JLabel("xxxx");
    private static JLabel toString = new JLabel(toTextField.getText());
    private static JLabel change = new JLabel("(-.- CHF -> -.- USD)");
    //down
    private static JLabel exchanges = new JLabel("1CHF = -.- EUR | -.- USD | -.- COP | -.- CAD");

    public GUI() {
        setTitle("Currency converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(400,150));

        JPanel panelUp = new JPanel();

        panelUp.add(from);
        panelUp.add(fromTextField);
        panelUp.add(to);
        panelUp.add(toTextField);

        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new BorderLayout());
        JPanel panelCenterTop = new JPanel();
        JPanel panelCenterDown = new JPanel();

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

        fromTextField.setPreferredSize(new Dimension(75, 24));
        toTextField.setPreferredSize(new Dimension(75, 24));
        fromInput.setPreferredSize(new Dimension(75, 24));
        setVisible(true);

    }

    public static void main(String[] args) {
        new GUI();
        load();

        fromInput.addActionListener(e -> {
            String fromText = fromTextField.getText();
            String toText = toTextField.getText();

            toLabel.setText(String.valueOf(c.convertAmount(fromText, toText, Double.parseDouble(fromInput.getText()))));
            loadSingularChange(fromText, toText);

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

        change.setText(String.format("( 1 %s -> %s %s )", from, String.format("%.2f", amount), to));


        for (int i = 0; i < 10; i++) {
            if ((int) amount * Math.pow(10, i) != 0) {
                change.setText(String.format("( 1 %s -> %s %s )", from, String.format("%.4f", amount), to));

            }
        }
    }

    public static void changeResult(double amount) {
        toLabel.setText(String.valueOf(amount));
    }



}
