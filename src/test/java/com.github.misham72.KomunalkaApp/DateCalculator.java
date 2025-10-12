package com.github.misham72.KomunalkaApp;

import javax.swing.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateCalculator extends JPanel {

	public static LocalDate getNextPaymentDate(int monthsPeriod, int paymentDay) {
		LocalDate today = LocalDate.now();
		return alignToPeriodEnd(today, monthsPeriod, paymentDay);
	}


	public static LocalDate getPreviousPaymentDate(int monthsPeriod, int paymentDay) {
		LocalDate today = LocalDate.now();
		return alignToPeriodStart(today, monthsPeriod, paymentDay);
	}





	/* ....................................................................................................*/
//	private static LocalDate findNextPaymentDate(LocalDate today, int monthsPeriod, int paymentDay) {

	//return alignToPeriodEnd(today, monthsPeriod, paymentDay);
	//}


	/* .....................................................................................................*/
	public static long calculateDaysToNextPayment(int monthsPeriod, int paymentDay) {
		LocalDate today = LocalDate.now();
		LocalDate nextPayment = alignToPeriodEnd(today, monthsPeriod, paymentDay);
		return ChronoUnit.DAYS.between(today, nextPayment);/* находим кол-во дней от сегодня до оплаты */
	}


	private static LocalDate alignToPeriodEnd(LocalDate today, int monthsPeriod, int paymentDay) {
		int currentMonth = today.getMonthValue();/* текущий месяц */
		int currentYear = today.getYear();
		int startMonth = ((currentMonth - 1) / monthsPeriod) * monthsPeriod + 1;/* начало периода */
		int endMonth = startMonth + monthsPeriod - 1;
		LocalDate periodEnd = LocalDate.of(currentYear, endMonth, paymentDay);
		if (monthsPeriod == 12) {
			periodEnd = periodEnd.minusDays(61);
		}
		return periodEnd;
	}


	/* ***************************************************************************************************** */


	public static long calculateDaysFromPreviousPayment(int monthsPeriod, int paymentDay) {
		LocalDate today = LocalDate.now();
		LocalDate previousPayment = alignToPeriodStart(today, monthsPeriod, paymentDay);
		return ChronoUnit.DAYS.between(previousPayment, today);
	}


	/* .................................................................................................*/
	/*private static LocalDate findPreviousPaymentDate(LocalDate today, int monthsPeriod, int paymentDay) {

		LocalDate previousPaymentNul = today.withDayOfMonth(Math.min(paymentDay, today.lengthOfMonth()));

		return alignToPeriodStart(previousPaymentNul, monthsPeriod, paymentDay);
	}*/
	/* .................................................................................................*/


	private static LocalDate alignToPeriodStart(LocalDate today, int monthsPeriod, int paymentDay) {
		int currentMonth = today.getMonthValue();
		int currentYear = today.getYear();
		int startMonth = ((currentMonth - 1) / monthsPeriod) * monthsPeriod + 1; // Начало периода
		LocalDate periodStart = LocalDate.of(currentYear, startMonth, 1);
		//periodStart = periodStart.withDayOfMonth(Math.min(paymentDay, periodStart.lengthOfMonth()));
		if (monthsPeriod == 1 && paymentDay == 30) {
			periodStart = periodStart.minusDays(1);
		} else if (monthsPeriod == 1 && paymentDay == 23) {
			periodStart = periodStart.minusDays(8);
		} else if (monthsPeriod == 3) {
			periodStart = periodStart.minusDays(1);
		} else if (monthsPeriod == 12) {
			periodStart = periodStart.minusDays(66);
		}

		return periodStart;
	}
}





