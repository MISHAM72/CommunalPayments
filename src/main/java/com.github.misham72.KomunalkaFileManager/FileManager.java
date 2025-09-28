package com.github.misham72.KomunalkaFileManager;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

  // Класс для работы с файлами (`FileManager`)**
     public class FileManager {
      // Метод для сохранения результатов в файл
      public void saveToFile(double currentReading, double previousReading,
                             double consumption, double tariff,
                             double payment, String dateTime) throws IOException {
          try (FileWriter writer = new FileWriter("запись.txt", true)) {
              String format = String.format(
                      """
                                   Дата и время операции: %s
                                    Текущие показания: %.2f кВт
                                       Предыдущие показания: %.2f кВт
                                           Расход:   %.2f кВт
                                           Тариф:    %.2f руб.кВт
                                           Сумма оплаты: %.2f руб.
                              """
                      , dateTime, currentReading, previousReading, consumption, tariff, payment);

              writer.write(format);
          }
      }
      // Новый метод для загрузки истории из файла
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






