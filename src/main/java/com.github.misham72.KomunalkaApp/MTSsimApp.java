package com.github.misham72.KomunalkaApp;

import javax.swing.*;
import java.awt.*;

public class MTSsimApp extends JPanel {

    public MTSsimApp() {
    long days = DateCalculator.getDaysNextPayment(23);
    long price = 905;
    long days1 = DateCalculator.getDaysPreviousPayment(23);

        setLayout(new GridLayout(7, 2, 10, 10));

        JLabel mtsDaylabel = new JLabel(" Оплата через -" + days + " дней.");
        mtsDaylabel.setFont(new Font("Arial", Font.BOLD, 16));
        mtsDaylabel.setForeground(Color.red);
        add(mtsDaylabel);

        JLabel mtsPriceLabel = new JLabel("Стоимость тарифа: " + price + " рублей. ");
        mtsPriceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(mtsPriceLabel);
        add(new JLabel());

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
