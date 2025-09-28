
package com.github.misham72.KomunalkaApp;

import com.github.misham72.KomunalkaCalculator.KomunalkaCalculator;
import com.github.misham72.KomunalkaFileManager.FileManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class KomunalkaApp {

    private JFrame frame;

    private JTextField currentDataField;
    private JTextField previousDataField;
    private JTextField tariffField;
    private JLabel consumptionLabel;
    private JLabel paymentLabel;
    private JLabel dateTimeLabel;
    //1. — переменная создаётся на уровне класса
    // (глобальная, в памяти общий доступ).
    private JButton calculateButton;
    private JButton showHistoryButton;

    private final KomunalkaCalculator calculator = new KomunalkaCalculator();
    private final FileManager fileManager = new FileManager();


    public void run() {

        initializeComponents();
        setupActionListeners();
        showFrame();
    }

    public void initializeComponents() {
        frame = new JFrame(" КОМУНАЛКА - г. Абинск, ул. Майкопская,10");
        frame.setSize(1000, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());


        // Создаем объекты (элементы интерфейс(5);

        currentDataField = new JTextField(5);
        previousDataField = new JTextField();
        tariffField = new JTextField(5);
        consumptionLabel = new JLabel("Расход: -");
        paymentLabel = new JLabel("К оплате: -");
        dateTimeLabel = new JLabel("Дата и время операции: -");
        calculateButton = new JButton("Рассчитать");
        calculateButton.setBackground(Color.green);


        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Файл");
        JMenuItem menuItem = new JMenuItem("Открыть");
        menuItem.addActionListener(_ -> System.out.println("Элемент меню выбран!"));
        menu.add(menuItem);
        menuBar.add(menu );
        frame.setJMenuBar(menuBar);

        // Кнопка для отображения истории
        showHistoryButton = new JButton("Показать историю");
        showHistoryButton.setBackground(Color.orange);
        frame.add(showHistoryButton);


        frame.add(new JLabel("Текущие показания (кВт/ч):"));
        frame.add(currentDataField);
        frame.add(new JLabel("Предыдущие показания (кВт/ч):"));
        frame.add(previousDataField);
        frame.add(new JLabel("Тариф (руб.):"));
        frame.add(tariffField);
        frame.add(consumptionLabel);
        frame.add(paymentLabel);
        frame.add(dateTimeLabel);
        // — добавляем кнопку в окно (`JFrame`)для отображения.
        frame.add(calculateButton);

    }

    private void setupActionListeners() {

        //1. — вешаем действие, которое выполняется после нажатия на кнопку
        calculateButton.addActionListener(_ -> handleCalculation());
        showHistoryButton.addActionListener(_ -> loadHistory());
        currentDataField.addActionListener(_ -> previousDataField.requestFocus());
        previousDataField.addActionListener(_ -> tariffField.requestFocus());
        tariffField.addActionListener(_ -> handleCalculation());
    }

    private void showFrame() {
        frame.setVisible(true);
    }

    // Обработчик событий кнопки "Рассчитать"
    private void handleCalculation() {
        try {

            double currentReading = Double.parseDouble(currentDataField.getText());
            double previousReading = Double.parseDouble(previousDataField.getText());
            double tariff = Double.parseDouble(tariffField.getText());

            // Выполняем расчеты
            double consumption = calculator.calculateConsumption(currentReading, previousReading);
            double payment = calculator.calculatePayment(consumption, tariff);

            // Получаем текущую дату и время
            LocalDateTime now = LocalDateTime.now();
            String formattedDateTime = now.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

            // Отображаем результаты на интерфейсе
            consumptionLabel.setText(String.format("Расход: %.2f кВт/ч", consumption));
            paymentLabel.setText(String.format("К оплате: %.2f руб.", payment));
            dateTimeLabel.setText("Дата и время операции: " + formattedDateTime);

            // Сохраняем данные в файл
            fileManager.saveToFile(currentReading, previousReading, tariff, consumption, payment, formattedDateTime);

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
        JOptionPane.showMessageDialog(frame,
                "Данные успешно сохранены!",
                "Информация",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void loadHistory() {
        try {
            // Загружаем историю из файла через FileManager
            String history = fileManager.loadFromFile();

            // Проверяем, есть ли данные в файле
            if (history.isEmpty()) {
                JOptionPane.showMessageDialog(frame,
                        "История пуста!",
                        "Информация",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JTextArea textArea = new JTextArea(20, 50);
                textArea.setText(history);
                textArea.setEditable(false);

                JScrollPane scrollPane = new JScrollPane(textArea);

                JOptionPane.showMessageDialog(frame, scrollPane, "История платежей", JOptionPane.INFORMATION_MESSAGE);

            }
        } catch (IOException e) {
            // Обрабатываем ошибки чтения файла
            JOptionPane.showMessageDialog(frame,
                    "Ошибка при чтении файла: " + e.getMessage(),
                    "Ошибка",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
