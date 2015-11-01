package ru.unn.agile.VolumesComputer.Model;

import static org.junit.Assert.*;

import org.junit.Test;

public class VolumesComputerTest {

	@Test
	public void cuboidGoodParams() {
	    final double w = 5.0, h = 4.0, l = 3.0;
	    final double goodVolume = 60.0;  // 5 * 4 * 3
	    final double volume = VolumesComputer.cuboid(w, h, l);
	    assertEquals(volume, goodVolume, m_epsilon);
	}

    @Test
	public void cuboidZeroParams() {
	    final double w = 5.0, h = 4.0, l = 0.0;
        final double goodVolume = 0.0;  // 5 * 4 * 0
        final double volume = VolumesComputer.cuboid(w, h, l);
        assertEquals(volume, goodVolume, m_epsilon);
	}
}
