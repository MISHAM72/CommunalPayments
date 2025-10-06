package com.github.misham72.KomunalkaApp;

import javax.swing.*;
import java.awt.*;

public class RouterApp extends JPanel {

	public RouterApp() {
		long daysUntilPayment = DateCalculator.calculateDays(1, 30, false);
		long daysFromPayment = DateCalculator.calculateDays(1, 30, true);
		long priceTariff = 950;
		long dayOfPayment= 30;

		setLayout(new GridLayout(7, 2, 10, 10));
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Добавляем отступы

		JLabel daysUntilPaymentLabel = new JLabel(" Оплата через -" + daysUntilPayment + " дней.");
		daysUntilPaymentLabel.setFont(new Font("Arial", Font.BOLD, 16));
		daysUntilPaymentLabel.setForeground(Color.red);
		add(daysUntilPaymentLabel);

		JLabel daysFromPaymentLabel = new JLabel("С момента оплаты прошло " + daysFromPayment + " дней,");
		daysFromPaymentLabel.setFont(new Font("Arial", Font.BOLD, 16));
		daysFromPaymentLabel.setForeground(Color.blue);
		add(daysFromPaymentLabel);
		add(new JLabel());


		JLabel priceTariffLabel = new JLabel("Стоимость тарифа: " + priceTariff + " рублей. ");
		priceTariffLabel.setFont(new Font("Arial", Font.BOLD, 16));
		add(priceTariffLabel);
		add(new JLabel());


		JLabel dayOfPaymentLabel = new JLabel(" Оплата - " + dayOfPayment+ " - го числа, каждого месяца.");
		dayOfPaymentLabel.setFont(new Font("Arial", Font.BOLD, 16));
		add(dayOfPaymentLabel);
		add(new JLabel());

		add(new JLabel());
		add(new JLabel());
		add(new JLabel());
		add(new JLabel());

	}
}
