package com.github.misham72.KomunalkaMain;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.FileWriter;
import java.io.IOException;

class KomunalkaTest {
    public static void main(String[] args) {
        new KomunalkaApp().run();
    }
}
class KomunalkaApp {

    private JFrame frame;

    private JTextField currentDataField; // Поле текущих показаний
    private JTextField previousDataField; // Поле предыдущих показаний
    private JTextField tariffField; // Поле для тарифа
    private JLabel consumptionLabel;
    private JLabel paymentLabel;
    private JLabel dateTimeLabel;
    private JButton calculateButton;
    private JButton showHistoryButton;

    private final KomunalkaCalculator calculator = new KomunalkaCalculator(); // Логика расчётов
    private final FileManager fileManager = new FileManager(); // Логика сохранения результатов


    // Метод для запуска приложения
    public void run() {

        initializeComponents();  // Логично всю настройку собрать здесь
        setupActionListeners();
        showFrame();

    }

    private void initializeComponents() {

        frame = new JFrame("Комуналка , г. Абинск, ул. Майкопскя, 10.");
        frame.setFont(new Font("Arial", Font.BOLD, 20));
        frame.setSize(1000, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());


        // Кнопка для расчета
        currentDataField = new JTextField(10);
        previousDataField = new JTextField(10);
        tariffField = new JTextField(10);
        consumptionLabel = new JLabel("Расход: -");
        paymentLabel = new JLabel("К оплате: -");
        dateTimeLabel = new JLabel("Дата и время операции: -");
        calculateButton = new JButton("Рассчитать");
        calculateButton.setBackground(Color.green);
        showHistoryButton = new JButton("Показать историю.");
        showHistoryButton.setBackground(Color.orange);


        frame.add(new JLabel("Текущие показания (кВт/ч):"));
        frame.add(currentDataField);
        frame.add(new JLabel("Предыдущие показания (кВт/ч):"));
        frame.add(previousDataField);
        frame.add(new JLabel("Тариф (руб.):"));
        frame.add(tariffField);
        frame.add(paymentLabel);
        frame.add(consumptionLabel);
        frame.add(dateTimeLabel);
        frame.add(calculateButton);
        frame.add(showHistoryButton);
    }
    private void setupActionListeners() {

        // лямбда
        currentDataField.addActionListener(_-> previousDataField.requestFocus());
        previousDataField.addActionListener(_-> tariffField.requestFocus());
        tariffField.addActionListener(_-> handleCalculation());
        calculateButton.addActionListener(_-> handleCalculation());
        showHistoryButton.addActionListener(_->loadHistory());
    }

    private void showFrame() {
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
            paymentLabel.setText(String.format("К оплате: %.2f руб.", payment));
            // Получаем текущую дату и время
            LocalDateTime now = LocalDateTime.now();
            String formattedDateTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            // Отображаем результаты на интерфейсе
            consumptionLabel.setText(String.format("Расход: %.2f кВт/ч", consumption));

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



    //      расчетами расхода электроэнергии
   static class  KomunalkaCalculator {
        // Метод для расчета расхода электроэнергии
        public double calculateConsumption(double currentReading, double previousReading) {
            return currentReading - previousReading;
        }

        // Метод для расчета суммы оплаты
        public double calculatePayment(double consumption, double tariff) {
            return consumption * tariff;
        }
    }


    // Класс для работы с файлами (`FileManager`)**
//Класс отвечает за сохранение данных в файл.
// Подходит для любых сценариев,
// требу
   static class FileManager {
        // Метод для сохранения результатов в файл
        public void saveToFile(double currentReading, double previousReading,
                               double consumption, double tariff,
                               double payment, String dateTime) throws IOException {
            try (FileWriter writer = new FileWriter("запись.txt", true)) {
                String format = String.format(
                        """
                                     Дата и время операции: %s
                                      Текущие показания: %.2f
                                         Предыдущие показания: %.2f
                                             Расход:   %.2f
                                             Тариф:    %.2f
                                             Сумма оплаты: %.2f
                                             =====
                                """
                        , dateTime, currentReading, previousReading, consumption, tariff, payment);

                writer.write(format);
                writer.write("\n");

            }
        }
        public String loadFromFile() throws IOException {
            Path path = Paths.get("запись.txt");

            // Проверяем, существует ли файл
            if (!Files.exists(path)) {
                return ""; // Если файла нет, возвращаем пустую строку
            }

            // Читаем содержимое файла и возвращаем его как строку
            return Files.readString(path);
        }

    }
}

