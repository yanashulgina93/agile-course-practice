package ru.unn.agile.MarksAccounting.Model;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Group {

    private final String number;
    private final ArrayList<Student> students;
    private final ArrayList<String> academicSubjects;

    private int findAcademicSubject(final String requiredAcademicSubject) {
        for (int i = 0; i < getAcademicSubjects().size(); i++) {
            if (getAcademicSubjects().get(i).equals(requiredAcademicSubject)) {
                return i;
            }
        }
        throw new AcademicSubjectDoesNotExistException(
                "Required academic subject doesn't exist");
    }

    private int findStudent(final String requiredStudent) {
        for (int i = 0; i < getStudents().size(); i++) {
            if (getStudents().get(i).getName().equals(requiredStudent)) {
                return i;
            }
        }
            throw new StudentDoesNotExistException("Required student doesn't exist");
    }

    public Group(final String currentNumber) {
        this.number = currentNumber;
        this.students = new ArrayList<Student>();
        this.academicSubjects = new ArrayList<String>();
    }

    public String getNumber() {
        return number;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<String> getAcademicSubjects() {
        return academicSubjects;
    }

    public void addStudent(final String newStudent) {
        int i = 0;
        while (i < getStudents().size()
                && getStudents().get(i).getName().compareTo(newStudent) <= 0) {
            if (getStudents().get(i).getName().equals(newStudent)) {
                i = -1;
                break;
            }
            i++;
        }
        if (i != -1) {
            students.add(i, new Student(newStudent));
        }
    }

    public void addAcademicSubject(final String newAcademicSubject) {
        int i = 0;
        while (i < getAcademicSubjects().size()
                && getAcademicSubjects().get(i).compareTo(newAcademicSubject) <= 0) {
            if (getAcademicSubjects().get(i).equals(newAcademicSubject)) {
                i = -1;
                break;
            }
            i++;
        }
        if (i != -1) {
            academicSubjects.add(i, newAcademicSubject);
        }
    }

    public int hashCode() {
        final int temp = 10;
        return temp * this.students.hashCode() + temp * temp * this.academicSubjects.hashCode()
                + this.number.hashCode();
    }

    @Override
    public boolean equals(final Object comparedGroup) {
        Group temp = (Group) comparedGroup;
        return temp.getNumber().equals(this.number)
                && temp.getStudents().equals(this.students)
                && temp.getAcademicSubjects().equals(this.academicSubjects);
    }

    public void addNewMark(final Mark newMark, final String requiredStudent) {
        findAcademicSubject(newMark.getAcademicSubject());
        this.students.get(findStudent(requiredStudent)).addMark(newMark);
    }

    public int getMark(final String requiredStudent, final String requiredAcademicSubject,
                       final GregorianCalendar requiredDate) {
        findAcademicSubject(requiredAcademicSubject);
        return this.students.get(findStudent(requiredStudent)).getMark(requiredAcademicSubject,
                requiredDate);
    }

    public void deleteMark(final String requiredStudent, final String requiredAcademicSubject,
                           final GregorianCalendar requiredDate) {
        findAcademicSubject(requiredAcademicSubject);
        this.students.get(findStudent(requiredStudent)).deleteMark(
                requiredAcademicSubject, requiredDate);
    }

    public void deleteStudent(final String requiredStudent) {
        this.students.remove(findStudent(requiredStudent));
    }

    public void deleteAcademicSubject(final String requiredAcademicSubject) {
        getAcademicSubjects().remove(findAcademicSubject(requiredAcademicSubject));
        for (int i = 0; i < getStudents().size(); i++) {
            this.students.get(i).deleteMarks(requiredAcademicSubject);
        }
    }
}
