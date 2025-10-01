
package com.github.misham72.KomunalkaApp;

import javax.swing.*;
import java.awt.*;

public class KomunalkaApp {

    public void run() {
        // Инициализируем фрейм
        JFrame frame = new JFrame("КОМУНАЛКА - г. Абинск, ул. Майкопская, 10");
        frame.setSize(1000, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создаём панели вкладок
        JTabbedPane tabbedPane = new JTabbedPane();


        // Добавляем вкладки (импорты классов для каждой панели)
        tabbedPane.addTab("Свет", new ElectricityApp());
        tabbedPane.addTab("Газ", new GasApp());
        tabbedPane.addTab("Вода", new WaterApp());


        // Добавляем TabbedPane в окно
        frame.add(tabbedPane, BorderLayout.CENTER);



        // Отображаем фрейм
        frame.setVisible(true);
    }
}