package com.allanvital.politicaaberta.utils;

import java.time.LocalDate;

public class DateShortcuts {

    public static LocalDate getLocalDateOfLastMonthStart() {
        return LocalDate.now().withDayOfMonth(1).minusMonths(1);
    }

    public static int getLastMonth() {
        return getLocalDateOfLastMonthStart().getMonthValue();
    }

    public static int getLastMonthYear() {
        return getLocalDateOfLastMonthStart().getYear();
    }

}
