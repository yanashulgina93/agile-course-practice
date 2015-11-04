package ru.unn.agile.MarksAccounting.Model;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Group {

    private final String number;
    private final ArrayList<Student> students;
    private final ArrayList<String> academicSubjects;

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
        while (i < getStudents().size()) {
            if (getStudents().get(i).getName().compareTo(newStudent) > 0) {
                break;
            }
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
        while (i < getAcademicSubjects().size()) {
            if (getAcademicSubjects().get(i).compareTo(newAcademicSubject) > 0) {
                break;
            }
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
        return (temp * this.students.hashCode() + temp * temp * this.academicSubjects.hashCode()
                + this.number.hashCode());
    }

    @Override
    public boolean equals(final Object comparedGroup) {
        Group temp = (Group) comparedGroup;
        return temp.getNumber().equals(this.number)
                && temp.getStudents().equals(this.students)
                && temp.getAcademicSubjects().equals(this.academicSubjects);
    }

    public void addNewMark(final Mark newMark, final String requiredStudent) {
        int i = 0;
        while (i < getAcademicSubjects().size()) {
            if (getAcademicSubjects().get(i).equals(newMark.getAcademicSubject())) {
                i = -1;
                break;
            }
            if (getAcademicSubjects().get(i).compareTo(newMark.getAcademicSubject()) > 0) {
                break;
            }
            i++;
        }
        if (i != -1) {
            throw new AcademicSubjectDoesNotExistException(
                    "Required academic subject doesn't exist");
        }
        i = 0;
        while (i < getStudents().size()) {
            if (getStudents().get(i).getName().equals(requiredStudent)) {
                this.students.get(i).addMark(newMark);
                i = -1;
                break;
            }
            if (getStudents().get(i).getName().compareTo(requiredStudent) > 0) {
                break;
            }
            i++;
        }
        if (i != -1) {
            throw new StudentDoesNotExistException("Required student doesn't exist");
        }
    }

    public int getMark(final String requiredStudent, final String requiredAcademicSubject,
                       final GregorianCalendar requiredDate) {
        int i = 0;
        while (i < getAcademicSubjects().size()) {
            if (getAcademicSubjects().get(i).equals(requiredAcademicSubject)) {
                i = -1;
                break;
            }
            if (getAcademicSubjects().get(i).compareTo(requiredAcademicSubject) > 0) {
                break;
            }
            i++;
        }
        if (i != -1) {
            throw new AcademicSubjectDoesNotExistException(
                    "Required academic subject doesn't exist");
        }
        i = 0;
        while (i < getStudents().size()) {
            if (getStudents().get(i).getName().equals(requiredStudent)) {
                return this.students.get(i).getMark(requiredAcademicSubject, requiredDate);
            }
            if (getStudents().get(i).getName().compareTo(requiredStudent) > 0) {
                break;
            }
            i++;
        }
        throw new StudentDoesNotExistException("Required student doesn't exist");
    }

    public void deleteMark(final String requiredStudent, final String requiredAcademicSubject,
                           final GregorianCalendar requiredDate) {
        int i = 0;
        while (i < getAcademicSubjects().size()) {
            if (getAcademicSubjects().get(i).equals(requiredAcademicSubject)) {
                i = -1;
                break;
            }
            if (getAcademicSubjects().get(i).compareTo(requiredAcademicSubject) > 0) {
                break;
            }
            i++;
        }
        if (i != -1) {
            throw new AcademicSubjectDoesNotExistException(
                    "Required academic subject doesn't exist");
        }
        i = 0;
        while (i < getStudents().size()) {
            if (getStudents().get(i).getName().equals(requiredStudent)) {
                this.students.get(i).deleteMark(requiredAcademicSubject, requiredDate);
                i = -1;
                break;
            }
            if (getStudents().get(i).getName().compareTo(requiredStudent) > 0) {
                break;
            }
            i++;
        }
        if (i != -1) {
            throw new StudentDoesNotExistException("Required student doesn't exist");
        }
    }

    public void deleteStudent(final String requiredStudent) {
        int i = 0;
        while (i < getStudents().size()) {
            if (getStudents().get(i).getName().equals(requiredStudent)) {
                this.students.remove(i);
                i = -1;
                break;
            }
            if (getStudents().get(i).getName().compareTo(requiredStudent) > 0) {
                break;
            }
            i++;
        }
        if (i != -1) {
            throw new StudentDoesNotExistException("Required student doesn't exist");
        }
    }

    public void deleteAcademicSubject(final String requiredAcademicSubject) {
        int i = 0;
        while (i < getAcademicSubjects().size()) {
            if (getAcademicSubjects().get(i).equals(requiredAcademicSubject)) {
                getAcademicSubjects().remove(i);
                i = -1;
                break;
            }
            if (getAcademicSubjects().get(i).compareTo(requiredAcademicSubject) > 0) {
                break;
            }
            i++;
        }
        if (i != -1) {
            throw new AcademicSubjectDoesNotExistException(
                    "Required academic subject doesn't exist");
        }
        for (i = 0; i < getStudents().size(); i++) {
            this.students.get(i).deleteMarks(requiredAcademicSubject);
        }
    }
}
