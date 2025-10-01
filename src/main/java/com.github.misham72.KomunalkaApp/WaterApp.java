package com.github.misham72.KomunalkaApp;

import com.github.misham72.KomunalkaCalculator.KomunalkaCalculator;
import com.github.misham72.KomunalkaFileManager.FileManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WaterApp extends JPanel{

        private final KomunalkaCalculator calculator;
        private final FileManager fileManager;
        private final String fileName = "Вода.txt";

        public WaterApp() {
            this.calculator = new KomunalkaCalculator();
            this.fileManager = new FileManager();

            setLayout(new GridLayout(7, 2, 10, 10));

            // Компоненты интерфейса
            JTextField currentDataField = new JTextField();
            JTextField previousDataField = new JTextField();
            JTextField tariffField = new JTextField();

            JLabel consumptionLabel = new JLabel("Расход: -");
            consumptionLabel.setFont(new Font("Arial", Font.BOLD, 16));
            consumptionLabel.setForeground(Color.red);

            JLabel paymentLabel = new JLabel("К оплате: -");
            paymentLabel.setFont(new Font("Arial", Font.BOLD, 16));
            paymentLabel.setForeground(Color.red);

            JLabel dateTimeLabel = new JLabel("Дата и время последней операции: -");
            dateTimeLabel.setFont(new Font("Arial", Font.BOLD, 16));

            JButton calculateButton = new JButton("Рассчитать");
            calculateButton.setBackground(Color.green);
            calculateButton.setFont(new Font("Arial", Font.BOLD, 16));

            JButton showHistoryButton = new JButton("Показать историю");
            showHistoryButton.setBackground(Color.getHSBColor(0.5f,0.5f, 0.8f));
            showHistoryButton.setFont(new Font("Arial", Font.BOLD, 16));

            // Добавляем компоненты в панель
            add(new JLabel("Текущие показания:"));
            add(currentDataField);
            add(new JLabel("Предыдущие показания:"));
            add(previousDataField);
            add(new JLabel("Тариф (руб.):"));
            add(tariffField);
            add(consumptionLabel);
            add(paymentLabel);
            add(dateTimeLabel);
            add(new JLabel()); // Пустое поле для выравнивания
            add(calculateButton);
            add(showHistoryButton);

            currentDataField.addActionListener(_ -> previousDataField.requestFocus());
            previousDataField.addActionListener(_ -> tariffField.requestFocus());
            tariffField.addActionListener(_-> calculateButton.doClick ());
            // Логика кнопки "Рассчитать"
            calculateButton.addActionListener(_-> {
                try {
                    double currentReading = Double.parseDouble(currentDataField.getText());
                    double previousReading = Double.parseDouble(previousDataField.getText());
                    double tariff = Double.parseDouble(tariffField.getText());

                    // Производим расчёты
                    double consumption = calculator.calculateConsumption(currentReading, previousReading);
                    double payment = calculator.calculatePayment(consumption, tariff);

                    // Обновляем данные на экране
                    String formattedDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
                    consumptionLabel.setText(String.format("Расход: %.2f", consumption));
                    paymentLabel.setText(String.format("К оплате: %.2f руб.", payment));
                    dateTimeLabel.setText("Дата и время последней операции: " + formattedDateTime);

                    // Сохраняем данные
                    fileManager.saveToFile(fileName, formattedDateTime, currentReading, previousReading, tariff, consumption, payment );
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Введите корректные числа!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Ошибка сохранения данных: " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            });

            // Логика кнопки "Показать историю"
            showHistoryButton.addActionListener(_-> {
                try {
                    String history = fileManager.loadFromFile(fileName);
                    if (history.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "История пуста для ресурса: Электричество", "Информация", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JTextArea textArea = new JTextArea(20, 50);
                        textArea.setText(history);
                        textArea.setEditable(false);

                        JScrollPane scrollPane = new JScrollPane(textArea);
                        JOptionPane.showMessageDialog(this, scrollPane, "История (Электричество)", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Ошибка загрузки истории: " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            });
        }
    }
