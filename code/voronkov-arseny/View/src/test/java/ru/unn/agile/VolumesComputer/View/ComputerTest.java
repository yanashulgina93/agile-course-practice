package ru.unn.agile.VolumesComputer.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import ru.unn.agile.VolumesComputer.ViewModel.ComputerViewModel;

import static org.junit.Assert.*;

public class ComputerTest {
    private Computer mComputer;
    @Ignore
    @Before
    public void initialize() {
        mComputer = new Computer(new ComputerViewModel());
    }
    @Ignore
    @After
    public void free() {
        mComputer = null;
    }
    @Ignore
    @Test
    public void fake() {
        mComputer.solve(0, "", "", "");
        assertEquals(mComputer.getOut(),
                ComputerViewModel.EMPTY_VOLUME_STRING);
    }
}
