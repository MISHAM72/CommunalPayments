
package com.github.misham72.KomunalkaApp;

import com.github.misham72.KomunalkaCalculator.KomunalkaCalculator;
import com.github.misham72.KomunalkaFileManager.FileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
  //  Класс графического интерфейса
  //  Этот класс отвечает за взаимодействие
  //     с пользователем: создание окна,
  //     обработка событий и отображение данных.
public class KomunalkaApp {
    private JFrame frame;
    private JTextField currentDataField; // Поле для текущих показаний
    private JTextField previousDataField; // Поле для предыдущих показаний
    private JTextField tariffField; // Поле для тарифа
    private JLabel consumptionLabel; // Метка для расхода
    private JLabel paymentLabel; // Метка для оплаты
    private JLabel dateTimeLabel; // Метка для даты и времени операции

    private final KomunalkaCalculator calculator = new KomunalkaCalculator(); // Логика расчётов
    private final FileManager fileManager = new FileManager(); // Логика сохранения результатов

    // Метод для запуска приложения
    public void run() {
        // Создаем окно
        frame = new JFrame("Калькулятор электроэнергии");
        frame.setSize(900, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        // Создаем элементы интерфейса
        currentDataField = new JTextField(5);
        previousDataField = new JTextField(5);
        tariffField = new JTextField(5);

        consumptionLabel = new JLabel("Расход: -");
        paymentLabel = new JLabel("К оплате: -");
        dateTimeLabel = new JLabel("Дата и время операции: -");

        JButton calculateButton = new JButton("Рассчитать");
        calculateButton.setBackground(Color.GREEN);

        // Добавляем обработчик события для кнопки
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleCalculation();
            }
        });

        // Добавляем элементы на окно
        frame.add(new JLabel("Текущие показания (кВт/ч):"));
        frame.add(currentDataField);
        frame.add(new JLabel("Предыдущие показания (кВт/ч):"));
        frame.add(previousDataField);
        frame.add(new JLabel("Тариф (руб.):"));
        frame.add(tariffField);
        frame.add(calculateButton);
        frame.add(consumptionLabel);
        frame.add(paymentLabel);
        frame.add(dateTimeLabel);

        // Показываем окно
        frame.setVisible(true);
    }

    // Обработчик событий кнопки "Рассчитать"
    private void handleCalculation() {
        try {
            // Получаем значения из текстовых полей
            double currentReading = Double.parseDouble(currentDataField.getText());
            double previousReading = Double.parseDouble(previousDataField.getText());
            double tariff = Double.parseDouble(tariffField.getText());

            // Выполняем расчеты
            double consumption = calculator.calculateConsumption(currentReading, previousReading);
            double payment = calculator.calculatePayment(consumption, tariff);

            // Получаем текущую дату и время
            LocalDateTime now = LocalDateTime.now();
            String formattedDateTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            // Отображаем результаты на интерфейсе
            consumptionLabel.setText(String.format("Расход: %.2f кВт/ч", consumption));
            paymentLabel.setText(String.format("К оплате: %.2f руб.", payment));
            dateTimeLabel.setText("Дата и время операции: " + formattedDateTime);

            // Сохраняем данные в файл
            fileManager.saveToFile(currentReading, previousReading, consumption, tariff, payment, formattedDateTime);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame,
                    "Введите корректные числовые значения!",
                    "Ошибка ввода",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame,
                    "Произошла ошибка: " + ex.getMessage(),
                    "Ошибка",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
