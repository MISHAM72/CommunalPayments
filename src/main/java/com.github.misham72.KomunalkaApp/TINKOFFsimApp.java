package com.github.misham72.KomunalkaApp;

import javax.swing.*;
import java.awt.*;

public class TINKOFFsimApp extends JPanel {

    public TINKOFFsimApp() {
        long days = DateCalculator.getDaysNextPayment(23);
        long price = 402;
        long days1 = DateCalculator.getDaysPreviousPayment(23);
        setLayout(new GridLayout(7, 2, 10, 10));

        JLabel tinkoffDaylabel = new JLabel(" Оплата через -" + days + " дней.");
        tinkoffDaylabel.setFont(new Font("Arial", Font.BOLD, 16));
        tinkoffDaylabel.setForeground(Color.red);
        add(tinkoffDaylabel);
        add(new JLabel());

        JLabel tinkoffPriceLabel = new JLabel("Стоимость тарифа: " + price + " рублей. ");
        tinkoffPriceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(tinkoffPriceLabel);

        JLabel PreviousDayLabel = new JLabel("С момента оплаты прошло " + days1 + " дней,");
        PreviousDayLabel.setFont(new Font("Arial", Font.BOLD, 16));
        PreviousDayLabel.setForeground(Color.blue);
        add(PreviousDayLabel);
        add(new JLabel());

        JLabel dayOfPaymentLabel = new JLabel(" Оплата - 23-го числа,каждого месяца. ");
        dayOfPaymentLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(dayOfPaymentLabel);
        add(new JLabel());

        add(new JLabel());
        add(new JLabel());
        add(new JLabel());
        add(new JLabel());


    }
}
