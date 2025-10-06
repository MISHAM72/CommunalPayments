package com.github.misham72.KomunalkaApp;

import javax.swing.*;
import java.awt.*;

public class GarbageApp extends JPanel {

	public GarbageApp() {

		long daysUntilPayment = DateCalculator.calculateDays(6, 6, false);
		long daysFromPayment = DateCalculator.calculateDays(6, 6, true);
		long priceTariff = 214;
		long dayArrive = 6;
		// Инициализация компонентов
		setLayout(new GridLayout(7, 2, 10, 10));
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		JLabel daysUntilPaymentLabel = new JLabel("Оплата через - " + daysUntilPayment + " дней. ");
		daysUntilPaymentLabel.setFont(new Font("Arial", Font.BOLD, 16));
		daysUntilPaymentLabel.setForeground(Color.red);
		add(daysUntilPaymentLabel);

		JLabel daysFromPaymentLabel = new JLabel("С момент оплаты прошло - " + daysFromPayment + " дней.");
		daysFromPaymentLabel.setFont(new Font("Arial", Font.BOLD, 16));
		daysFromPaymentLabel.setForeground(Color.blue);
		add(daysFromPaymentLabel);
		add(new JLabel());

		JLabel priceTariffLabel = new JLabel("Стоимость тарифа: " + priceTariff + " рублей. ");
		priceTariffLabel.setFont(new Font("Arial", Font.BOLD, 16));
		add(priceTariffLabel);
		add(new JLabel());

		JLabel dayOfPaymentLabel = new JLabel("Оплата " + dayArrive + "-го числа,каждые полгода. ");
		dayOfPaymentLabel.setFont(new Font("Arial", Font.BOLD, 16));
		add(dayOfPaymentLabel);
		add(new JLabel());

		add(new JLabel());
		add(new JLabel());
		add(new JLabel());
		add(new JLabel());
	}
}

