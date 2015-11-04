package ru.unn.agile.MarksAccounting.Model;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Student {

    private final String name;
    private final ArrayList<Mark> marks;

    public Student(final String currentName) {
        this.name = currentName;
        this.marks = new ArrayList<Mark>();
    }

    public ArrayList<Mark> getMarks() {
        return marks;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        final int temp = 100;
        return temp * this.name.hashCode() + marks.hashCode();
    }

    @Override
    public boolean equals(final Object comparedStudent) {
        Student temp = (Student) comparedStudent;
        return this.name.equals(temp.getName()) && this.marks.equals(temp.getMarks());
    }

    public void addMark(final Mark newMark) {
        int i = 0;
        while (i < getMarks().size()
                && !(getMarks().get(i).getDate().after(newMark.getDate()))) {
            if (newMark.isMarkCorrection(getMarks().get(i))) {
                getMarks().get(i).correctMark(newMark.getValue());
                i = -1;
                break;
            }
            i++;
        }
        if (i != -1) {
            this.marks.add(i, newMark);
        }
    }

    public int getMark(final String requiredAcademicSubject, final GregorianCalendar requiredDate) {
        int i = 0;
        while (i < getMarks().size()) {
            if (getMarks().get(i).getDate().equals(requiredDate)
                    && getMarks().get(i).getAcademicSubject().equals(requiredAcademicSubject)) {
                return getMarks().get(i).getValue();
            }
            i++;
        }
        throw new MarkDoesNotExistException("Required mark doesn't exist");
    }

    public void deleteMark(final String requiredAcademicSubject,
                           final GregorianCalendar requiredDate) {
        int i = 0;
        while (i < getMarks().size()
                && !(getMarks().get(i).getDate().after(requiredDate))) {
            if (getMarks().get(i).getDate().equals(requiredDate)
                    && getMarks().get(i).getAcademicSubject().equals(requiredAcademicSubject)) {
                this.marks.remove(i);
                i = -1;
                break;
            }
            i++;
        }
        if (i != -1) {
            throw new MarkDoesNotExistException("Required mark doesn't exist");
        }
    }

    public void deleteMarks(final String requieredAcademicSubject) {
        int i = 0;
        while (i < getMarks().size()) {
            if (getMarks().get(i).getAcademicSubject().equals(requieredAcademicSubject)) {
                this.marks.remove(i);
            } else {
                i++;
            }
        }
    }
}
