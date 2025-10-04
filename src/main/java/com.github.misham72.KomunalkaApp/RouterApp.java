package com.github.misham72.KomunalkaApp;

import javax.swing.*;
import java.awt.*;

public class RouterApp extends JPanel {

    public RouterApp(){
       long days = DateCalculator.getDaysNextPayment(30);
       long price = 950;
       long days1 = DateCalculator.getDaysPreviousPayment(30);

        setLayout(new GridLayout(7, 2, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Добавляем отступы

        JLabel routerDaylabel = new JLabel(" Оплата через -" + days + " дней.");
        routerDaylabel.setFont(new Font("Arial", Font.BOLD, 16));
        routerDaylabel.setForeground(Color.red);
        add(routerDaylabel);

        JLabel routerPriceLabel = new JLabel("Стоимость тарифа: " + price + " рублей. ");
        routerPriceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(routerPriceLabel);
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
