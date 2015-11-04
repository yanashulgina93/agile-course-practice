package ru.unn.agile.MarksAccounting.Model;

import java.util.GregorianCalendar;

public class Mark {

    private int value;
    private final String academicSubject;
    private final GregorianCalendar date;

    public Mark(final int currentValue, final String currentAcademicSubject,
                final GregorianCalendar currentDate) {
        this.value = currentValue;
        this.academicSubject = currentAcademicSubject;
        this.date = currentDate;
    }

    public int getValue() {
        return value;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public String getAcademicSubject() {
        return academicSubject;
    }

    @Override
    public int hashCode() {
        final int temp = 100;
        return temp * temp * this.value
                + temp * this.academicSubject.hashCode() + this.date.hashCode();
    }

    @Override
    public boolean equals(final Object comparedMark) {
        Mark temp = (Mark) comparedMark;
        return this.date.equals(temp.getDate())
                && this.academicSubject.equals(temp.getAcademicSubject())
                && this.value == temp.getValue();
    }

    public boolean isMarkCorrection(final Mark comparedMark) {
        return getDate().equals(comparedMark.getDate())
                && getAcademicSubject().equals(comparedMark.getAcademicSubject());
    }

    public void correctMark(final int newValue) {
        this.value = newValue;
    }
}
