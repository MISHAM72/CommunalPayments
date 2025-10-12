package com.github.misham72.KomunalkaApp;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class GarbageApp extends JPanel {

	public GarbageApp() {

		LocalDate date = LocalDate.now();
		long daysUntilPayment = DateCalculator.calculateDaysToNextPayment(1, 30);
		long daysFromPayment = DateCalculator.calculateDaysFromPreviousPayment(1, 30);
		LocalDate nextPayment = DateCalculator.getNextPaymentDate(1, 30);
		LocalDate previousPayment = DateCalculator.getPreviousPaymentDate(1, 30);
		long priceTariff = 214;


		setLayout(new GridLayout(7, 2, 10, 10));
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Добавляем отступы

		JLabel previousPaymentLabel = new JLabel(" Предыдущая оплата:   " + previousPayment);
		previousPaymentLabel.setFont(new Font("Arial", Font.BOLD, 18));
		previousPaymentLabel.setForeground(Color.blue);
		add(previousPaymentLabel);
		add(new JLabel());

		JLabel dateLabel = new JLabel(" Сегодня:          " + date);
		dateLabel.setFont(new Font("Arial", Font.BOLD, 18));
		dateLabel.setForeground(Color.black);
		add(dateLabel);
		add(new JLabel());

		JLabel dayOfPaymentLabel = new JLabel(" Дата оплаты:                 " + nextPayment);
		dayOfPaymentLabel.setFont(new Font("Arial", Font.BOLD, 18));
		dayOfPaymentLabel.setForeground(Color.red);
		add(dayOfPaymentLabel);
		add(new JLabel());
		add(new JLabel());


		JLabel daysUntilPaymentLabel = new JLabel(" Оплата через:                        " + daysUntilPayment + " дней.");
		daysUntilPaymentLabel.setFont(new Font("Arial", Font.BOLD, 18));
		daysUntilPaymentLabel.setForeground(Color.red);
		add(daysUntilPaymentLabel);
		add(new JLabel());

		JLabel daysFromPaymentLabel = new JLabel(" С момента оплаты прошло: " + daysFromPayment + " дней,");
		daysFromPaymentLabel.setFont(new Font("Arial", Font.BOLD, 18));
		daysFromPaymentLabel.setForeground(Color.blue);
		add(daysFromPaymentLabel);
		add(new JLabel());
		add(new JLabel());


		JLabel priceTariffLabel = new JLabel(" Стоимость тарифа: " + priceTariff + " рублей. ");
		priceTariffLabel.setFont(new Font("Arial", Font.BOLD, 16));
		priceTariffLabel.setForeground(Color.getHSBColor(0.9f, 0.85f, 0.8f));
		add(priceTariffLabel);
		add(new JLabel());
	}

}
