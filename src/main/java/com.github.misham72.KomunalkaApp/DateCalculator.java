package com.github.misham72.KomunalkaApp;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateCalculator {

        public static long getDaysPreviousPayment (int paymentDay) {
            //текущая дата
        LocalDate today = LocalDate.now();

                   // Дата последней оплаты (последнее 30-е число)
        LocalDate lastPaymentDate = getLastPaymentDate(paymentDay);

            // Количество дней между датами
            return ChronoUnit.DAYS.between(lastPaymentDate, today);
        }

    private static LocalDate getLastPaymentDate(int paymentDay) {
        LocalDate today = LocalDate.now();
        LocalDate paymentDate = LocalDate.of(today.getYear(), today.getMonth(), paymentDay);

        // Если сегодняшнее число меньше дня оплаты в этом месяце,
        // значит оплата была в прошлом месяце
        if (today.getDayOfMonth() < paymentDay) {
            paymentDate = paymentDate.minusMonths(1);
            // Корректируем день для месяцев с меньшим количеством дней
            int actualDay = Math.min(paymentDay, paymentDate.lengthOfMonth());
            paymentDate = LocalDate.of(paymentDate.getYear(), paymentDate.getMonth(), actualDay);
        }

        return paymentDate;
    }
    public static long getDaysNextPayment(int paymentDay) {
        LocalDate today = LocalDate.now();
        LocalDate nextPaymentDate = getNextPaymentDate(paymentDay);

        return ChronoUnit.DAYS.between(today, nextPaymentDate);
    }

    private static LocalDate getNextPaymentDate(int paymentDay) {
        LocalDate today = LocalDate.now();
        LocalDate paymentDate = LocalDate.of(today.getYear(), today.getMonth(), paymentDay);

        // Если день оплаты в этом месяце уже прошел, берем следующий месяц
        if (today.getDayOfMonth() >= paymentDay) {
            paymentDate = paymentDate.plusMonths(1);
            // Корректируем день для месяцев с меньшим количеством дней
            int actualDay = Math.min(paymentDay, paymentDate.lengthOfMonth());
            paymentDate = LocalDate.of(paymentDate.getYear(), paymentDate.getMonth(), actualDay);
        }

        return paymentDate;
    }
    public static long getDaysWithArrive(LocalDate startDate) {
        LocalDate today = LocalDate.now();

        // Проверяем, что начальная дата не в будущем
        if (startDate.isAfter(today)) {
            throw new IllegalArgumentException("Начальная дата не может быть в будущем");
        }

        return ChronoUnit.DAYS.between(startDate, today);
    }

}


