package com.github.misham72.KomunalkaMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.FileWriter;
import java.io.IOException;

public class  KomunalkaTest {
    public static void main(String[] args) {
        KomunalkaApp app = new KomunalkaApp(); // Создаем объект KomunalkaApp
        app.run(); //
    }
}


//  Класс графического интерфейса
//  Этот класс отвечает за взаимодействие
//     с пользователем: создание окна,
//     обработка событий и отображение данных.
class KomunalkaApp {
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
        frame.setFont( new Font("Arial", Font.BOLD, 20));
        frame.setSize(1000, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        // Создаем элементы интерфейса
        currentDataField = new JTextField(5);
        previousDataField = new JTextField(5);
        tariffField = new JTextField(5);

        consumptionLabel = new JLabel("Расход: -");
        consumptionLabel.setFont(new Font("Arial", Font.BOLD, 20));
        paymentLabel = new JLabel("К оплате: -");
        paymentLabel.setFont(new Font("Arial", Font.BOLD, 20));
        dateTimeLabel = new JLabel("Дата и время операции: -");
        dateTimeLabel.setFont(new Font("Arial", Font.BOLD, 20));
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


    // Метод для загрузки данных из файла
    private List<String> loadHistoryFromFile() throws IOException {
        List<String> history = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("запись.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                history.add(line);
            }
        }
        return history;
    }

    //----------------------------------------
}
//      Класс, занимающийся исключительно
//      расчетами расхода электроэнергии
class KomunalkaCalculator {
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
class FileManager {
    // Метод для сохранения результатов в файл
    public void saveToFile(double currentReading, double previousReading,
                           double consumption, double tariff,
                           double payment, String dateTime) throws IOException {
        try (FileWriter writer = new FileWriter( "запись.txt", true)) {
            String format = String.format(
                """
                             Дата и время операции: %s
                              Текущие показания: %.2f
                                 Предыдущие показания: %.2f
                                     Расход:   %.2f
                                     Тариф:    %.2f
                                     Сумма оплаты: %.2f
                        """
            , dateTime , currentReading , previousReading , consumption , tariff , payment);

                writer.write(format);
        }
    }
}