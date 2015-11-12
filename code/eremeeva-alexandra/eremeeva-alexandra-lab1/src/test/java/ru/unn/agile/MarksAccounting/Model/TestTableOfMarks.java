package ru.unn.agile.MarksAccounting.Model;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestTableOfMarks {

    private TableOfMarks tableOfMarks;
    private TableOfMarks comparedTableOfMarks;
    private ArrayList<Group> tempGroups;

    private void initTemp() {
        tempGroups = new ArrayList<Group>();
        tempGroups.add(new Group("1"));
        tempGroups.get(0).addStudent("Ivanov");
        tempGroups.get(0).addStudent("Sidorov");
        tempGroups.get(0).addAcademicSubject("History");
        tempGroups.get(0).addAcademicSubject("Maths");
    }

    private void initGroups() {
        tableOfMarks = new TableOfMarks();
        comparedTableOfMarks = new TableOfMarks();
        tableOfMarks.addGroup("1");
        comparedTableOfMarks.addGroup("1");
        comparedTableOfMarks.addGroup("2");
        tableOfMarks.addAcademicSubject("1", "History");
        tableOfMarks.addAcademicSubject("1", "Maths");
        comparedTableOfMarks.addAcademicSubject("1", "History");
        comparedTableOfMarks.addAcademicSubject("1", "Maths");
        comparedTableOfMarks.addAcademicSubject("2", "Science");
        tableOfMarks.addStudent("1", "Ivanov");
        tableOfMarks.addStudent("1", "Sidorov");
        comparedTableOfMarks.addStudent("1", "Ivanov");
        comparedTableOfMarks.addStudent("1", "Sidorov");
        comparedTableOfMarks.addStudent("2", "Petrov");
        comparedTableOfMarks.addNewMark(
                new Mark(4, "Science", new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 1)),
                "Petrov", "2");
    }

    @Before
    public void setUp() {
        initTemp();
        initGroups();
    }

    @Test
    public void canGetGroups() {
        assertEquals(tempGroups, tableOfMarks.getGroups());
    }

    @Test
    public void canAddGroup() {
        tempGroups.add(new Group("2"));
        tableOfMarks.addGroup("2");
        assertEquals(tempGroups, tableOfMarks.getGroups());
    }

    @Test
    public void whenGroupAlreadyExists() {
        tableOfMarks.addGroup("1");
        assertEquals(tempGroups, tableOfMarks.getGroups());
    }

    @Test
    public void canAddStudent() {
        tempGroups.get(0).addStudent("Kornyakov");
        tableOfMarks.addStudent("1", "Kornyakov");
        assertEquals(tempGroups, tableOfMarks.getGroups());
    }

    @Test(expected = GroupDoesNotExistException.class)
    public void whenRequiredGroupDoesNotExistWhileAddingStudent() {
        tableOfMarks.addStudent("2", "Kornyakov");
    }

    @Test
    public void canAddSubject() {
        tempGroups.get(0).addAcademicSubject("Science");
        tableOfMarks.addAcademicSubject("1", "Science");
        assertEquals(tempGroups, tableOfMarks.getGroups());
    }

    @Test(expected = GroupDoesNotExistException.class)
    public void  whenRequiredGroupDoesNotExistWhileAddingAcademicSubject() {
        tableOfMarks.addAcademicSubject("2", "Maths");
    }

    @Test
    public void canAddNewMark() {
        tempGroups.get(0).addNewMark(
                new Mark(4, "History", new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 1)),
                "Sidorov");
        tableOfMarks.addNewMark(
                new Mark(4, "History", new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 1)),
                "Sidorov", "1");
        assertEquals(tempGroups, tableOfMarks.getGroups());
    }

    @Test(expected = GroupDoesNotExistException.class)
    public void whenRequiredGroupDoesNotExistWhileAddingNewMark() {
        tableOfMarks.addNewMark(
                new Mark(4, "History", new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 1)),
                "Sidorov", "2");
    }

    @Test
    public void whenEquals() {
        TableOfMarks equivalentTableOfMarks = tableOfMarks;
        equivalentTableOfMarks.addGroup("2");
        equivalentTableOfMarks.addAcademicSubject("2", "Science");
        equivalentTableOfMarks.addStudent("2", "Petrov");
        equivalentTableOfMarks.addNewMark(
                new Mark(4, "Science", new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 1)),
                "Petrov", "2");
        assertTrue(equivalentTableOfMarks.equals(comparedTableOfMarks));
    }

    @Test
    public void whenDoesNotEqual() {
        assertFalse(tableOfMarks.equals(comparedTableOfMarks));
    }

    @Test
    public void canGetMark() {
        Mark four = new Mark(4, "Science",
                new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 1));
        assertEquals(four, comparedTableOfMarks.getMark("2", "Petrov", "Science",
                new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 1)));
    }

    @Test(expected = GroupDoesNotExistException.class)
    public void whenRequiredGroupDoesNotExistWhileGettingMark() {
        tableOfMarks.getMark("2", "Sidorov",  "History",
                new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 1));
    }

    @Test
    public void canDeleteMark() {
        tableOfMarks.addGroup("2");
        tableOfMarks.addStudent("2", "Petrov");
        tableOfMarks.addAcademicSubject("2", "Science");
        comparedTableOfMarks.deleteMark("2", "Petrov",  "Science",
                new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 1));
        assertEquals(tableOfMarks, comparedTableOfMarks);
    }

    @Test(expected = GroupDoesNotExistException.class)
    public void whenRequiredGroupDoesNotExistWhileDeletingMark() {
        tableOfMarks.deleteMark("2", "Petrov",  "Science",
                new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 1));
    }

    @Test
    public void canDeleteGroup() {
        comparedTableOfMarks.deleteGroup("2");
        assertEquals(tableOfMarks, comparedTableOfMarks);
    }

    @Test(expected = GroupDoesNotExistException.class)
    public void whenRequiredGroupDoesNotExistWhileDeletingGroup() {
        tableOfMarks.deleteGroup("2");
    }

    @Test
    public void canDeleteStudent() {
        tempGroups.get(0).deleteStudent("Sidorov");
        tableOfMarks.deleteStudent("1", "Sidorov");
        assertEquals(tempGroups, tableOfMarks.getGroups());
    }

    @Test(expected = GroupDoesNotExistException.class)
    public void whenRequiredGroupDoesNotExistWhileDeletingStudent() {
        tableOfMarks.deleteStudent("2", "Sidorov");
    }

    @Test
    public void canDeleteAcademicSubject() {
        tempGroups.get(0).deleteAcademicSubject("History");
        tableOfMarks.deleteAcademicSubject("1", "History");
        assertEquals(tempGroups, tableOfMarks.getGroups());
    }

    @Test(expected = GroupDoesNotExistException.class)
    public void whenRequiredGroupDoesNotExistWhileDeletingAcademicSubject() {
        tableOfMarks.deleteAcademicSubject("2", "History");
    }

}
