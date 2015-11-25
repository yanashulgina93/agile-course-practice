package ru.unn.agile.VolumesComputer.View;

import org.junit.Test;
import ru.unn.agile.VolumesComputer.ViewModel.ComputerViewModel;

import static org.junit.Assert.*;

public class ComputerTest {
    @Test
    public void fake() {
        // fail("No test for view!");
        Computer computer = new Computer(new ComputerViewModel());
        computer.solve(0, "", "", "");
        assertEquals(computer.getOut(), ComputerViewModel.EMPTY_VOLUME_STRING);
    }
}
