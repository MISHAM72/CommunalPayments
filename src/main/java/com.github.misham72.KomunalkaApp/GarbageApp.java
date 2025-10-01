package com.github.misham72.KomunalkaApp;

import com.github.misham72.KomunalkaCalculator.KomunalkaCalculator;
import com.github.misham72.KomunalkaFileManager.FileManager;

import javax.swing.*;
import java.awt.*;

public class GarbageApp extends JPanel {
    private final KomunalkaCalculator calculator;
    private final FileManager fileManager;
    private final String fileName = "МТС";



    public GarbageApp() {
        this.calculator = new KomunalkaCalculator();
        this.fileManager = new FileManager();
        setLayout(new GridLayout(7, 2, 10, 10));

        JLabel dayOfPaymentLabel = new JLabel(" Оплата - после приезда домой. ");
        dayOfPaymentLabel.setFont(new Font("Arial", Font.BOLD, 16));
        JLabel costOfPaymentLabel = new JLabel(" Стоимость услуги - не помню. ");
        costOfPaymentLabel.setFont(new Font("Arial", Font.BOLD, 16));

        add(dayOfPaymentLabel);
        add(costOfPaymentLabel);


    }
}
