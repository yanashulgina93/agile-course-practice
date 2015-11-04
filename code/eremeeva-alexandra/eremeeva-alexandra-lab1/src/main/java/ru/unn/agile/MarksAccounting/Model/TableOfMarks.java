package ru.unn.agile.MarksAccounting.Model;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class TableOfMarks {

    private final ArrayList<Group> groups;

    public TableOfMarks() {
        groups = new ArrayList<Group>();
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void addGroup(final String newGroup) {
        int i = 0;
        while (i < getGroups().size()) {
            if (getGroups().get(i).getNumber().compareTo(newGroup) > 0) {
                break;
            }
            if (getGroups().get(i).getNumber().equals(newGroup)) {
                i = -1;
                break;
            }
            i++;
        }
        if (i != -1) {
            this.groups.add(i, new Group(newGroup));
        }
    }

    public void addStudent(final String requiredGroup, final String newStudent)
    {
        int i = 0;
        while (i < getGroups().size()) {
            if (getGroups().get(i).getNumber().compareTo(requiredGroup) > 0) {
                break;
            }
            if (getGroups().get(i).getNumber().equals(requiredGroup)) {
                this.groups.get(i).addStudent(newStudent);
                i = -1;
                break;
            }
            i++;
        }
        if (i != -1) {
            throw new GroupDoesNotExistException("Required group doesn't exist");
        }
    }

    public void addAcademicSubject(final String requiredGroup, final String newAcademicSubject)
    {
        int i = 0;
        while (i < getGroups().size()) {
            if (getGroups().get(i).getNumber().compareTo(requiredGroup) > 0) {
                break;
            }
            if (getGroups().get(i).getNumber().equals(requiredGroup)) {
                this.groups.get(i).addAcademicSubject(newAcademicSubject);
                i = -1;
                break;
            }
            i++;
        }
        if (i != -1) {
            throw new GroupDoesNotExistException("Required group doesn't exist");
        }
    }

    public void addNewMark(final Mark newMark, final String requiredStudent,
                           final String requiredGroup) {
        int i = 0;
        while (i < getGroups().size()) {
            if (getGroups().get(i).getNumber().compareTo(requiredGroup) > 0) {
                break;
            }
            if (getGroups().get(i).getNumber().equals(requiredGroup)) {
                this.groups.get(i).addNewMark(newMark, requiredStudent);
                i = -1;
                break;
            }
            i++;
        }
        if (i != -1) {
            throw new GroupDoesNotExistException("Required group doesn't exist");
        }
    }

    @Override
    public int hashCode() {
        return this.groups.hashCode();
    }

    @Override
    public boolean equals(final Object comparedTableOfMarks) {
        TableOfMarks temp = (TableOfMarks) comparedTableOfMarks;
        return this.groups.equals(temp.getGroups());
    }

    public void deleteGroup(final String requiredGroup) {
        int i = 0;
        while (i < getGroups().size()) {
            if (getGroups().get(i).getNumber().compareTo(requiredGroup) > 0) {
                break;
            }
            if (getGroups().get(i).getNumber().equals(requiredGroup)) {
                this.groups.remove(i);
                i = -1;
                break;
            }
            i++;
        }
        if (i != -1) {
            throw new GroupDoesNotExistException("Required group doesn't exist");
        }
    }

    public int getMark(final String requiredGroup, final String requiredStudent,
                       final String requiredAcademicSubject, final GregorianCalendar requiredDate) {
        int i = 0;
        while (i < getGroups().size()) {
            if (getGroups().get(i).getNumber().compareTo(requiredGroup) > 0) {
                break;
            }
            if (getGroups().get(i).getNumber().equals(requiredGroup)) {
                return getGroups().get(i).getMark(requiredStudent, requiredAcademicSubject,
                        requiredDate);
            }
            i++;
        }
        throw new GroupDoesNotExistException("Required group doesn't exist");
    }

    public void deleteMark(final String requiredGroup, final String requiredStudent,
                            final String requiredAcademicSubject,
                            final GregorianCalendar requiredDate) {
        int i = 0;
        while (i < getGroups().size()) {
            if (getGroups().get(i).getNumber().compareTo(requiredGroup) > 0) {
                break;
            }
            if (getGroups().get(i).getNumber().equals(requiredGroup)) {
                this.groups.get(i).deleteMark(requiredStudent, requiredAcademicSubject,
                        requiredDate);
                i = -1;
                break;
            }
            i++;
        }
        if (i != -1) {
            throw new GroupDoesNotExistException("Required group doesn't exist");
        }
    }

    public void deleteAcademicSubject(final String requiredGroup,
                                      final String requiredAcademicSubject) {
        int i = 0;
        while (i < getGroups().size()) {
            if (getGroups().get(i).getNumber().compareTo(requiredGroup) > 0) {
                break;
            }
            if (getGroups().get(i).getNumber().equals(requiredGroup)) {
                this.groups.get(i).deleteAcademicSubject(requiredAcademicSubject);
                i = -1;
                break;
            }
            i++;
        }
        if (i != -1) {
            throw new GroupDoesNotExistException("Required group doesn't exist");
        }
    }

    public void deleteStudent(final String requiredGroup, final String requiredStudent) {
        int i = 0;
        while (i < getGroups().size()) {
            if (getGroups().get(i).getNumber().compareTo(requiredGroup) > 0) {
                break;
            }
            if (getGroups().get(i).getNumber().equals(requiredGroup)) {
                this.groups.get(i).deleteStudent(requiredStudent);
                i = -1;
                break;
            }
            i++;
        }
        if (i != -1) {
            throw new GroupDoesNotExistException("Required group doesn't exist");
        }
    }

}
