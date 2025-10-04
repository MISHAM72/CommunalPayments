package com.github.misham72.KomunalkaApp;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class GarbageApp extends JPanel {

    public GarbageApp() {

        LocalDate startDate = LocalDate.of(2025, 7, 6);
        long days = DateCalculator.getDaysWithArrive(startDate);

        setLayout(new GridLayout(7, 2, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel daysFromArrive = new JLabel("С даты приезда в Москву прошло - " + days + " дней. ");
        daysFromArrive.setFont(new Font("Arial",Font.BOLD,16));
        daysFromArrive.setForeground(Color.getHSBColor(0.8f,0.9f,0.8f));
        add(daysFromArrive);
    }
}

