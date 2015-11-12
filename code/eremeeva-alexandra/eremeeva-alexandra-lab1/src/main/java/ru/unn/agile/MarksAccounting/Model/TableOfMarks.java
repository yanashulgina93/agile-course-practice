package ru.unn.agile.MarksAccounting.Model;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class TableOfMarks {

    private final ArrayList<Group> groups;

    private int findGroup(final String requiredGroup) {
        for (int i = 0; i < getGroups().size(); i++) {
            if (getGroups().get(i).getNumber().equals(requiredGroup)) {
                return i;
            }
        }
        throw new GroupDoesNotExistException("Required group doesn't exist");
    }

    public TableOfMarks() {
        groups = new ArrayList<Group>();
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void addGroup(final String newGroup) {
        if (getGroups().isEmpty()) {
            groups.add(new Group(newGroup));
        } else {
            int i;
            for (i = 0; i < getGroups().size(); i++) {
                if (getGroups().get(i).getNumber().compareTo(newGroup) > 0) {
                    break;
                }
            }
            if (!getGroups().get(i - 1).getNumber().equals(newGroup)) {
                groups.add(i, new Group(newGroup));
            }
        }
    }

    public void addStudent(final String requiredGroup, final String newStudent)
    {
        groups.get(findGroup(requiredGroup)).addStudent(newStudent);
    }

    public void addAcademicSubject(final String requiredGroup, final String newAcademicSubject)
    {
        groups.get(findGroup(requiredGroup)).addAcademicSubject(newAcademicSubject);
    }

    public void addNewMark(final Mark newMark, final String requiredStudent,
                           final String requiredGroup) {
        groups.get(findGroup(requiredGroup)).addNewMark(newMark, requiredStudent);
    }

    @Override
    public int hashCode() {
        return groups.hashCode();
    }

    @Override
    public boolean equals(final Object comparedTableOfMarks) {
        TableOfMarks temp = (TableOfMarks) comparedTableOfMarks;
        return groups.equals(temp.getGroups());
    }

    public void deleteGroup(final String requiredGroup) {
        groups.remove(findGroup(requiredGroup));
    }

    public Mark getMark(final String requiredGroup, final String requiredStudent,
                       final String requiredAcademicSubject, final GregorianCalendar requiredDate) {
        return getGroups().get(findGroup(requiredGroup)).getMark(requiredStudent,
                requiredAcademicSubject, requiredDate);
    }

    public void deleteMark(final String requiredGroup, final String requiredStudent,
                            final String requiredAcademicSubject,
                            final GregorianCalendar requiredDate) {
        groups.get(findGroup(requiredGroup)).deleteMark(requiredStudent,
                requiredAcademicSubject, requiredDate);
    }

    public void deleteAcademicSubject(final String requiredGroup,
                                      final String requiredAcademicSubject) {
        groups.get(findGroup(requiredGroup)).deleteAcademicSubject(requiredAcademicSubject);
    }

    public void deleteStudent(final String requiredGroup, final String requiredStudent) {
        groups.get(findGroup(requiredGroup)).deleteStudent(requiredStudent);
    }
}
