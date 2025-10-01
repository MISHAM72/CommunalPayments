package com.github.misham72.KomunalkaFileManager;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.io.IOException;
import java.nio.file.Files;


  // Класс для работы с файлами (`FileManager`)**
     public class FileManager {
      // Метод для сохранения результатов в файл
      public void saveToFile(String fileName, String dateTime,
                             double currentReading, double previousReading,
                         double consumption, double tariff,
                             double payment) throws IOException {

              String data = String.format(
                      """
                                   Услуга: %s
                                    Текущие показания: %.2f кВт
                                       Предыдущие показания: %.2f кВт
                                           Расход:   %.2f кВт
                                           Тариф:    %.2f руб.кВт
                                           Сумма оплаты: %.2f руб.
                                            (%s)
                              
                              """
                      , fileName, currentReading, previousReading, consumption, tariff, payment, dateTime);
          Files.write(Paths.get(fileName), data.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);

      }
         public String loadFromFile(String fileName) throws IOException {
             return Files.readString(Paths.get(fileName));
         }
     }
