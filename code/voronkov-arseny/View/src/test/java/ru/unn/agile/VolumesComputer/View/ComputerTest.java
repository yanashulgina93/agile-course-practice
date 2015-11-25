package ru.unn.agile.VolumesComputer.View;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import ru.unn.agile.VolumesComputer.ViewModel.ComputerViewModel;

public class ComputerTest {
    // @Ignore
    @Test
    public void fake() {
        // fail("No test for view!");
        Computer computer = new Computer(new ComputerViewModel());
        computer.solve(0, "2.0", "3.0", "4.0");
    }
}
