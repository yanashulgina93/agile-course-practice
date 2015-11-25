package ru.unn.agile.VolumesComputer.ViewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.VolumesComputer.Model.*;

import static org.junit.Assert.*;

public class ComputerViewModelTest {
    private ComputerViewModel mViewModel;

    @Before
    public void initialize() {
        mViewModel = new ComputerViewModel();
    }
    @After
    public void free() {
        mViewModel = null;
    }
    @Test
    public void setParameter1() {
        final String param = "2.0";
        mViewModel.setParameter1(param);
        assertEquals(mViewModel.getParameter1(), param);
    }
    @Test
    public void setParameter2() {
        final String param = "3.0";
        mViewModel.setParameter2(param);
        assertEquals(mViewModel.getParameter2(), param);
    }
    @Test
    public void setParameter3() {
        final String param = "4.0";
        mViewModel.setParameter3(param);
        assertEquals(mViewModel.getParameter3(), param);
    }
    @Test
    public void setFigure() {
        final FigureName figure = FigureName.RIGHT_CYLINDER;
        mViewModel.setFigure(figure);
        assertEquals(mViewModel.getFigure(), figure);
    }

    @Test
    public void cuboidParametersEnabling() {
        mViewModel.setFigure(FigureName.CUBOID);
        assertTrue(mViewModel.isParameter1enabled()
                && mViewModel.isParameter2enabled()
                && mViewModel.isParameter3enabled());
    }
    @Test
    public void spheroidParametersEnabling() {
        mViewModel.setFigure(FigureName.SPHEROID);
        assertTrue(mViewModel.isParameter1enabled()
                && mViewModel.isParameter2enabled()
                && !mViewModel.isParameter3enabled());
    }
    @Test
    public void rightCylinderParametersEnabling() {
        mViewModel.setFigure(FigureName.RIGHT_CYLINDER);
        assertTrue(mViewModel.isParameter1enabled()
                && mViewModel.isParameter2enabled()
                && mViewModel.isParameter3enabled());
    }
    @Test
    public void rightCircularConeParametersEnabling() {
        mViewModel.setFigure(FigureName.RIGHT_CIRCULAR_CONE);
        assertTrue(mViewModel.isParameter1enabled()
                && mViewModel.isParameter2enabled()
                && !mViewModel.isParameter3enabled());
    }

    @Test
    public void parseTwoGoodParameters() {
        mViewModel.setFigure(FigureName.SPHEROID);
        mViewModel.setParameter1("2.0");
        mViewModel.setParameter2("3.0");
        mViewModel.parse();
        assertTrue(mViewModel.isInputCorrect());
    }
    @Test
    public void parseTwoBadParameters() {
        mViewModel.setFigure(FigureName.SPHEROID);
        mViewModel.setParameter1("2.0");
        mViewModel.setParameter2("oioioi");
        mViewModel.parse();
        assertFalse(mViewModel.isInputCorrect());
    }
    @Test
    public void parseTwoEmptyParameters() {
        mViewModel.setFigure(FigureName.SPHEROID);
        mViewModel.setParameter1("");
        mViewModel.setParameter2("");
        mViewModel.parse();
        assertFalse(mViewModel.isInputCorrect());
    }

    @Test
    public void parseThreeGoodParameters() {
        mViewModel.setFigure(FigureName.CUBOID);
        mViewModel.setParameter1("2.0");
        mViewModel.setParameter2("3.0");
        mViewModel.setParameter3("4.0");
        mViewModel.parse();
        assertTrue(mViewModel.isInputCorrect());
    }
    @Test
    public void parseThreeBadParameters() {
        mViewModel.setFigure(FigureName.CUBOID);
        mViewModel.setParameter1("2.0");
        mViewModel.setParameter2("3.0");
        mViewModel.setParameter3("oioioi");
        mViewModel.parse();
        assertFalse(mViewModel.isInputCorrect());
    }
    @Test
    public void parseThreeEmptyParameters() {
        mViewModel.setFigure(FigureName.CUBOID);
        mViewModel.setParameter1("");
        mViewModel.setParameter2("");
        mViewModel.setParameter3("");
        mViewModel.parse();
        assertFalse(mViewModel.isInputCorrect());
    }

    @Test
    public void solveBadParameters() {
        mViewModel.setParameter1("hehehe");
        mViewModel.solve();
        assertEquals(mViewModel.getVolume(),
                ComputerViewModel.EMPTY_VOLUME_STRING);
    }

    @Test
    public void solveCuboidGoodParameters() {
        mViewModel.setFigure(FigureName.CUBOID);
        mViewModel.setParameter1("2.0");
        mViewModel.setParameter2("3.0");
        mViewModel.setParameter3("4.0");
        mViewModel.solve();
        assertEquals(mViewModel.getVolume(), String.valueOf(
                VolumesComputer.solve(new Cuboid(2.0, 3.0, 4.0))));
    }
    @Test
    public void solveCuboidNegativeParameters() {
        mViewModel.setFigure(FigureName.CUBOID);
        mViewModel.setParameter1("2.0");
        mViewModel.setParameter2("3.0");
        mViewModel.setParameter3("-4.0");
        mViewModel.solve();
        assertEquals(mViewModel.getVolume(),
                ComputerViewModel.BAD_VOLUME_STRING);
    }

    @Test
    public void solveSpheroidGoodParameters() {
        mViewModel.setFigure(FigureName.SPHEROID);
        mViewModel.setParameter1("2.0");
        mViewModel.setParameter2("3.0");
        mViewModel.solve();
        assertEquals(mViewModel.getVolume(), String.valueOf(
                VolumesComputer.solve(new Spheroid(2.0, 3.0))));
    }
    @Test
    public void solveSpheroidNegativeParameters() {
        mViewModel.setFigure(FigureName.SPHEROID);
        mViewModel.setParameter1("2.0");
        mViewModel.setParameter2("-3.0");
        mViewModel.solve();
        assertEquals(mViewModel.getVolume(),
                ComputerViewModel.BAD_VOLUME_STRING);
    }

    @Test
    public void solveRightCylinderGoodParameters() {
        mViewModel.setFigure(FigureName.RIGHT_CYLINDER);
        mViewModel.setParameter1("2.0");
        mViewModel.setParameter2("3.0");
        mViewModel.setParameter3("4.0");
        mViewModel.solve();
        assertEquals(mViewModel.getVolume(), String.valueOf(
                VolumesComputer.solve(new RightCylinder(2.0, 3.0, 4.0))));
    }
    @Test
    public void solveRightCylinderNegativeParameters() {
        mViewModel.setFigure(FigureName.RIGHT_CYLINDER);
        mViewModel.setParameter1("2.0");
        mViewModel.setParameter2("-3.0");
        mViewModel.setParameter3("4.0");
        mViewModel.solve();
        assertEquals(mViewModel.getVolume(),
                ComputerViewModel.BAD_VOLUME_STRING);
    }

    @Test
    public void solveRightCircularConeGoodParameters() {
        mViewModel.setFigure(FigureName.RIGHT_CIRCULAR_CONE);
        mViewModel.setParameter1("2.0");
        mViewModel.setParameter2("3.0");
        mViewModel.solve();
        assertEquals(mViewModel.getVolume(), String.valueOf(
                VolumesComputer.solve(new RightCircularCone(2.0, 3.0))));
    }
    @Test
    public void solveRightCircularConeNegativeParameters() {
        mViewModel.setFigure(FigureName.RIGHT_CIRCULAR_CONE);
        mViewModel.setParameter1("2.0");
        mViewModel.setParameter2("-3.0");
        mViewModel.solve();
        assertEquals(mViewModel.getVolume(),
                ComputerViewModel.BAD_VOLUME_STRING);
    }

    @Test
    public void parametersNamesCuboid() {
        mViewModel.setFigure(FigureName.CUBOID);
        assertArrayEquals(FigureName.CUBOID.getParametersNames(),
                new String[] {
                        mViewModel.getParameter1name(),
                        mViewModel.getParameter2name(),
                        mViewModel.getParameter3name()
                });
    }
    @Test
    public void parametersNamesSpheroid() {
        mViewModel.setFigure(FigureName.SPHEROID);
        assertEquals(mViewModel.getParameter3name(),
                mViewModel.DISABLE_PARAMETER_STRING);
        assertArrayEquals(FigureName.SPHEROID.getParametersNames(),
                new String[] {
                        mViewModel.getParameter1name(),
                        mViewModel.getParameter2name()
                });
    }
    @Test
    public void parametersNamesRightCylinder() {
        mViewModel.setFigure(FigureName.RIGHT_CYLINDER);
        assertArrayEquals(FigureName.RIGHT_CYLINDER.getParametersNames(),
                new String[] {
                        mViewModel.getParameter1name(),
                        mViewModel.getParameter2name(),
                        mViewModel.getParameter3name()
                });
    }
    @Test
    public void parametersNamesRightCircularCone() {
        mViewModel.setFigure(FigureName.RIGHT_CIRCULAR_CONE);
        assertEquals(mViewModel.getParameter3name(),
                mViewModel.DISABLE_PARAMETER_STRING);
        assertArrayEquals(FigureName.RIGHT_CIRCULAR_CONE.getParametersNames(),
                new String[] {
                        mViewModel.getParameter1name(),
                        mViewModel.getParameter2name()
                });
    }
}
