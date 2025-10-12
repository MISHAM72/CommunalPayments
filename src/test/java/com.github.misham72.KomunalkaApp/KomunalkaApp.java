
package com.github.misham72.KomunalkaApp;

import javax.swing.*;
import java.awt.*;


public class KomunalkaApp {


    public void run() {
        System.out.println("Создаем окно приложения...");

        // Инициализируем фрейм
        JFrame frame = new JFrame("КОМУНАЛКА - г. Абинск, ул. Майкопская, 10");
        frame.setSize(1200, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Создаём панели вкладок
        JTabbedPane tabbedPane = new JTabbedPane();
        // Добавляем вкладки (импорты классов для каждой панели)
        System.out.println("Создаем вкладку 'Свет'...");
        tabbedPane.addTab("Свет", new ElectricityApp());
        System.out.println("Создаем вкладку 'Газ'...");
        tabbedPane.addTab("Газ", new GasApp());
        System.out.println("Создаем вкладку 'вода'...");
        tabbedPane.addTab("Вода", new WaterApp());
        System.out.println("Создаем вкладку 'ZONT'...");
        tabbedPane.addTab("ZONT", new ZONTsimApp() );
        System.out.println("Создаем вкладку 'мусор'...");
        tabbedPane.addTab("Мусор", new GarbageApp());
        System.out.println("Создаем вкладку 'роутер'...");
        tabbedPane.addTab("Роутер", new RouterApp());
        System.out.println("Создаем вкладку 'МТС'...");
        tabbedPane.addTab("MTC", new MTSsimApp());
        System.out.println("Создаем вкладку 'TINKOFF'...");
        tabbedPane.addTab("Тинькофф", new TINKOFFsimApp());
        System.out.println("Создаем вкладку 'И -налоги'...");
        tabbedPane.addTab("ИП-налоги", new TaxesIP());
        System.out.println("Создаем вкладку 'ОСАГО'...");
        tabbedPane.addTab("Страхование ОСАГО ", new OsagoAvto());
        // Добавляем TabbedPane в окно
        frame.add(tabbedPane, BorderLayout.CENTER);
        // Отображаем фрейм
        System.out.println("Отображаем окно...");
        frame.setVisible(true);
    }
}
