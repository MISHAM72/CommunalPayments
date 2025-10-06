package com.github.misham72.KomunalkaApp;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateCalculator {

	public static long calculateDays(int periodInMonths, int paymentDayOfMonth, boolean isPast) {
		LocalDate today = LocalDate.now();

		// Разделим логику для ежемесячного платежа и более длинных периодов (например, квартал)
		if (periodInMonths == 1) {
			// Месячная оплата
			LocalDate thisMonthPayment = LocalDate.of(today.getYear(), today.getMonthValue(), Math.min(paymentDayOfMonth, today.lengthOfMonth()));

			// Расчёт следующей и последней дат
			LocalDate lastPaymentDate;
			LocalDate nextPaymentDate;

			if (today.isBefore(thisMonthPayment) || today.equals(thisMonthPayment)) {
				lastPaymentDate = thisMonthPayment.minusMonths(1);
				nextPaymentDate = thisMonthPayment;
			} else {
				lastPaymentDate = thisMonthPayment;
				nextPaymentDate = thisMonthPayment.plusMonths(1);
			}

			// Возвращаем результат
			return isPast
					? ChronoUnit.DAYS.between(lastPaymentDate, today)      // Дни с последнего платежа
					: ChronoUnit.DAYS.between(today, nextPaymentDate);     // Дни до следующего платежа
		} else {
			// Любая другая оплата (квартальная, годовая...)
			int month = today.getMonthValue();
			int quarterEndMonth;

			// Определяем конец периода
			if (periodInMonths == 3) {
				if (month <= 3) quarterEndMonth = 3;
				else if (month <= 6) quarterEndMonth = 6;
				else if (month <= 9) quarterEndMonth = 9;
				else quarterEndMonth = 12;
			} else {
				// Для периодов длиннее месяца (например, год) вычисляем как следующий интервал
				quarterEndMonth = (month - 1) / periodInMonths * periodInMonths + periodInMonths;
			}

			// Последний день текущего периода
			LocalDate periodEndDate = LocalDate.of(today.getYear(), quarterEndMonth, Math.min(paymentDayOfMonth, LocalDate.of(today.getYear(), quarterEndMonth, 1).lengthOfMonth()));

			// Рассчитываем даты
			LocalDate lastPaymentDate = periodEndDate.minusMonths(periodInMonths);
			LocalDate nextPaymentDate = periodEndDate;

			if (today.isAfter(periodEndDate)) {
				lastPaymentDate = periodEndDate;
				nextPaymentDate = periodEndDate.plusMonths(periodInMonths);
			}

			// Возвращаем результат
			return isPast
					? ChronoUnit.DAYS.between(lastPaymentDate, today)
					: ChronoUnit.DAYS.between(today, nextPaymentDate);
		}
	}
}
/*
		### Что изменено?
		1. **Добавлена отдельная логика для месячных интервалов (`periodInMonths == 1`):**
		- Теперь месячные платежи рассчитываются отдельно.
    - Для "последнего платежа" используется `thisMonthPayment.minusMonths(1)`, а для "следующего платежа" — `thisMonthPayment.plusMonths(1)`.

		2. **Сохранена логика для кварталов и более длинных периодов:**
		- Логика расчета для кварталов (или других произвольных периодов) осталась прежней.
		- Для других интервалов (`periodInMonths > 1`) используются окончания фиксированных периодов.

		3. **Добавлена гибкость в днях оплаты:**
		- Если день оплаты (`paymentDayOfMonth`) больше, чем количество дней в месяце, он корректно устанавливается на последний день данного месяца.

		### Тестирование
#### Пример 1. Месячная оплата (**сегодня: 6 октября 2023**)
``` java
long daysUntilPayment = DateCalculator.calculateDays(1, 30, false);
long daysFromPayment = DateCalculator.calculateDays(1, 30, true);
*/


