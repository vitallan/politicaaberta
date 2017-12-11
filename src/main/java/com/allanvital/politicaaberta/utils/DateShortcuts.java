package com.allanvital.politicaaberta.utils;

import java.time.LocalDate;

public class DateShortcuts {

    private static LocalDate localDateOfCurrentMonth() {
        return LocalDate.now().withDayOfMonth(1);
    }

    private static LocalDate localDateOfLastMonthStart() {
        return localDateOfCurrentMonth().minusMonths(1);
    }
    private static LocalDate localDateOfTwoMonthsAgo() {
        return localDateOfCurrentMonth().minusMonths(2);
    }
    private static LocalDate localDateOfThreeMonthsAgo() {
        return localDateOfCurrentMonth().minusMonths(3);
    }

    public static int lastMonth() {
        return localDateOfLastMonthStart().getMonthValue();
    }

    public static int twoMonthsAgo() {
        return localDateOfTwoMonthsAgo().getMonthValue();
    }

    public static int threeMonthsAgo() {
        return localDateOfThreeMonthsAgo().getMonthValue();
    }

    public static int yearFromLastMonth() {
        return localDateOfTwoMonthsAgo().getYear();
    }

    public static int yearFromTwoMonthsAgo() {
        return localDateOfThreeMonthsAgo().getYear();
    }

    public static int yearFromThreeMonthsAgo() {
        return localDateOfLastMonthStart().getYear();
    }

}
