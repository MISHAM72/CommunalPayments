package com.github.misham72.KomunalkaApp;

import javax.swing.*;
import java.awt.*;

public class ZONTsimApp  extends JPanel {
    public ZONTsimApp() {
        long days = DateCalculator.getDaysNextPayment(30);
        long price = 120;
        long days1 = DateCalculator.getDaysPreviousPayment(30);

        setLayout(new GridLayout(7, 2, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Добавляем отступы

        JLabel ZontDaylabel = new JLabel(" Оплата через -" + days + " дней.");
        ZontDaylabel.setFont(new Font("Arial", Font.BOLD, 16));
        ZontDaylabel.setForeground(Color.red);
        add(ZontDaylabel);

        JLabel ZontPriceLabel = new JLabel("Стоимость тарифа: " + price + " рублей. ");
        ZontPriceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(ZontPriceLabel);
        add(new JLabel());

        JLabel PreviousDayLabel = new JLabel("С момента оплаты прошло " + days1 + " дней,");
        PreviousDayLabel.setFont(new Font("Arial", Font.BOLD, 16));
        PreviousDayLabel.setForeground(Color.blue);
        add(PreviousDayLabel);
        add(new JLabel());

        JLabel dayOfPaymentLabel = new JLabel(" Оплата - 30-го числа,каждого месяца. ");
        dayOfPaymentLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(dayOfPaymentLabel);
        add(new JLabel());

        add(new JLabel());
        add(new JLabel());
        add(new JLabel());
        add(new JLabel());
    }
}


