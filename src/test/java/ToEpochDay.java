import java.time.LocalDate;

public class ToEpochDay {
	public static void main(String[] args) {
		LocalDate date1970 = LocalDate.of(1970, 1, 1);  // Эпохальная дата
		LocalDate myDate = LocalDate.of(2025, 10, 30); // Моя дата

		long daysBetween = myDate.toEpochDay();  // Используем toEpochDay
		System.out.println("Порядковый номер дня: " + daysBetween);
	}
}
/** package com.github.misham72.KomunalkaApp;

 import com.github.misham72.KomunalkaFileManager.FileManager;

 import javax.swing.*;
 import java.awt.*;
 import java.io.IOException;
 import java.time.LocalDate;
 import java.time.LocalDateTime;
 import java.time.format.DateTimeFormatter;

 public class Troyka extends JPanel {
 private static final Font FONT_ARIAL_BOLD_18 = new Font("Arial", Font.BOLD, 18);

 private final FileManager fileManager;
 private final String fileName = "Troyka .txt";


 public Troyka() {
 this.fileManager = new FileManager();


 LocalDate date = LocalDate.now();
 long daysUntilPayment = DateCalculator.calculateDaysToNextPayment(12, 24);
 long daysFromPayment = DateCalculator.calculateDaysFromPreviousPayment(12, 24);
 LocalDate nextPayment = DateCalculator.getNextPaymentDate(12, 24);
 LocalDate previousPayment = DateCalculator.getPreviousPaymentDate(12, 24);
 long priceTariff = 22650;


 setLayout(new GridLayout(7, 2, 10, 10));
 setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Добавляем отступы

 JLabel previousPaymentLabel = new JLabel(" Предыдущая оплата:   " + previousPayment);
 previousPaymentLabel.setFont(FONT_ARIAL_BOLD_18);
 previousPaymentLabel.setForeground(Color.blue);
 add(previousPaymentLabel);

 JLabel dateLabel = new JLabel(" Сегодня:          " + date);
 dateLabel.setFont(FONT_ARIAL_BOLD_18);
 dateLabel.setForeground(Color.black);
 add(dateLabel);

 JLabel dayOfPaymentLabel = new JLabel(" Дата оплаты:                 " + nextPayment);
 dayOfPaymentLabel.setFont(FONT_ARIAL_BOLD_18);
 dayOfPaymentLabel.setForeground(Color.blue);
 add(dayOfPaymentLabel);
 add(new JLabel());
 add(new JLabel());

 JLabel daysUntilPaymentLabel = new JLabel(" Оплата через:                        " + daysUntilPayment + " дней.");
 daysUntilPaymentLabel.setFont(FONT_ARIAL_BOLD_18);
 daysUntilPaymentLabel.setForeground(Color.red);
 add(daysUntilPaymentLabel);
 add(new JLabel());

 JLabel daysFromPaymentLabel = new JLabel(" С момента оплаты прошло: " + daysFromPayment + " дней,");
 daysFromPaymentLabel.setFont(FONT_ARIAL_BOLD_18);
 daysFromPaymentLabel.setForeground(Color.blue);
 add(daysFromPaymentLabel);
 add(new JLabel());
 add(new JLabel());


 JLabel priceTariffLabel = new JLabel(" Стоимость тарифа: " + priceTariff + " рублей. ");
 priceTariffLabel.setFont(new Font("Arial", Font.BOLD, 16));
 priceTariffLabel.setForeground(Color.getHSBColor(0.9f, 0.85f, 0.8f));
 add(priceTariffLabel);
 add(new JLabel());

 JButton saveHistoryButton = new JButton("Сохранить в файл");
 saveHistoryButton.setBackground(Color.green);
 saveHistoryButton.setFont(new Font("Arial", Font.BOLD, 16));
 add(saveHistoryButton);

 saveHistoryButton.addActionListener(_ -> {
 try {
 String formattedDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
 fileManager.formatPaymentDate(fileName, daysUntilPayment, daysFromPayment, nextPayment, previousPayment,priceTariff,formattedDateTime);
 JOptionPane.showMessageDialog(this, "Данные успешно сохранены!", "Успех", JOptionPane.INFORMATION_MESSAGE);
 } catch (IOException ex) {
 JOptionPane.showMessageDialog(this, "Ошибка сохранения данных: " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
 }
 });

 // Кнопка для загрузки истории
 JButton showHistoryButton = new JButton("Показать историю");
 showHistoryButton.setBackground(Color.getHSBColor(0.99f, 0.29f, 0.94f));
 showHistoryButton.setFont(new Font("Arial", Font.BOLD, 16));
 add(showHistoryButton);

 showHistoryButton.addActionListener(_ -> {
 try {
 String history = fileManager.loadFromFile(fileName);
 if (history.isEmpty()) {
 JOptionPane.showMessageDialog(this, "История пуста для ресурса: MTS", "Информация", JOptionPane.INFORMATION_MESSAGE);
 } else {
 JTextArea textArea = new JTextArea(20, 50);
 textArea.setText(history);
 textArea.setEditable(true);

 JScrollPane scrollPane = new JScrollPane(textArea);
 JOptionPane.showMessageDialog(this, scrollPane, "История (MTS)", JOptionPane.INFORMATION_MESSAGE);
 }
 } catch (IOException ex) {
 JOptionPane.showMessageDialog(this, "Ошибка загрузки истории: " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
 }
 });
 }
 }

 **/
