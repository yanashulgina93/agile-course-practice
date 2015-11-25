package ru.unn.agile.VolumesComputer.ViewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.VolumesComputer.Model.*;

import static org.junit.Assert.*;

public class ComputerViewModelTest {
    private ComputerViewModel m_viewModel;

    @Before
    public void initialize() {
        m_viewModel = new ComputerViewModel();
    }
    @After
    public void free() {
        m_viewModel = null;
    }
    @Test
    public void setParameter1() {
        final String param = "2.0";
        m_viewModel.setParameter1(param);
        assertEquals(m_viewModel.getParameter1(), param);
    }
    @Test
    public void setParameter2() {
        final String param = "3.0";
        m_viewModel.setParameter2(param);
        assertEquals(m_viewModel.getParameter2(), param);
    }
    @Test
    public void setParameter3() {
        final String param = "4.0";
        m_viewModel.setParameter3(param);
        assertEquals(m_viewModel.getParameter3(), param);
    }
    @Test
    public void setFigure() {
        final FigureName figure = FigureName.RIGHT_CYLINDER;
        m_viewModel.setFigure(figure);
        assertEquals(m_viewModel.getFigure(), figure);
    }
    @Test
    public void parametersEnabling() {
        final FigureName[] figures = FigureName.values();
        int paramsCount;
        for (FigureName figure : figures) {
            m_viewModel.setFigure(figure);
            paramsCount = figure.getParametersCount();
            if (paramsCount < 1) {
                assertFalse(m_viewModel.isParameter1enabled()
                        && m_viewModel.isParameter2enabled()
                        && m_viewModel.isParameter3enabled());
            } else if (paramsCount < 2) {
                assertTrue(m_viewModel.isParameter1enabled()
                        && !(m_viewModel.isParameter2enabled()
                        && m_viewModel.isParameter3enabled()));
            } else if (paramsCount < 3) {
                assertTrue(m_viewModel.isParameter1enabled()
                        && m_viewModel.isParameter2enabled()
                        && !m_viewModel.isParameter3enabled());
            } else if (paramsCount == 3) {
                assertTrue(m_viewModel.isParameter1enabled()
                        && m_viewModel.isParameter2enabled()
                        && m_viewModel.isParameter3enabled());
            } else {
                fail("View model need more parameters!");
            }
        }
    }
    @Test
    public void parseTwoGoodParameters() {
        m_viewModel.setFigure(FigureName.SPHEROID);
        m_viewModel.setParameter1("2.0");
        m_viewModel.setParameter2("3.0");
        m_viewModel.parse();
        assertTrue(m_viewModel.isInputCorrect());
    }
    @Test
    public void parseTwoBadParameters() {
        m_viewModel.setFigure(FigureName.SPHEROID);
        m_viewModel.setParameter1("2.0");
        m_viewModel.setParameter2("oioioi");
        m_viewModel.parse();
        assertFalse(m_viewModel.isInputCorrect());
    }
    @Test
    public void parseTwoEmptyParameters() {
        m_viewModel.setFigure(FigureName.SPHEROID);
        m_viewModel.setParameter1("");
        m_viewModel.setParameter2("");
        m_viewModel.parse();
        assertFalse(m_viewModel.isInputCorrect());
    }
    @Test
    public void parseThreeGoodParameters() {
        m_viewModel.setFigure(FigureName.CUBOID);
        m_viewModel.setParameter1("2.0");
        m_viewModel.setParameter2("3.0");
        m_viewModel.setParameter3("4.0");
        m_viewModel.parse();
        assertTrue(m_viewModel.isInputCorrect());
    }
    @Test
    public void parseThreeBadParameters() {
        m_viewModel.setFigure(FigureName.CUBOID);
        m_viewModel.setParameter1("2.0");
        m_viewModel.setParameter2("3.0");
        m_viewModel.setParameter3("oioioi");
        m_viewModel.parse();
        assertFalse(m_viewModel.isInputCorrect());
    }
    @Test
    public void parseThreeEmptyParameters() {
        m_viewModel.setFigure(FigureName.CUBOID);
        m_viewModel.setParameter1("");
        m_viewModel.setParameter2("");
        m_viewModel.setParameter3("");
        m_viewModel.parse();
        assertFalse(m_viewModel.isInputCorrect());
    }
    @Test
    public void solveBadParameters() {
        m_viewModel.setParameter1("hehehe");
        m_viewModel.solve();
        assertEquals(m_viewModel.getVolume(),
                ComputerViewModel.EMPTY_VOLUME_STRING);
    }

    @Test
    public void solveCuboidGoodParameters() {
        m_viewModel.setFigure(FigureName.CUBOID);
        m_viewModel.setParameter1("2.0");
        m_viewModel.setParameter2("3.0");
        m_viewModel.setParameter3("4.0");
        m_viewModel.solve();
        assertEquals(m_viewModel.getVolume(), String.valueOf(
                VolumesComputer.solve(new Cuboid(2.0, 3.0, 4.0))));
    }
    @Test
    public void solveCuboidNegativeParameters() {
        m_viewModel.setFigure(FigureName.CUBOID);
        m_viewModel.setParameter1("2.0");
        m_viewModel.setParameter2("3.0");
        m_viewModel.setParameter3("-4.0");
        m_viewModel.solve();
        assertEquals(m_viewModel.getVolume(),
                ComputerViewModel.BAD_VOLUME_STRING);
    }

    @Test
    public void solveSpheroidGoodParameters() {
        m_viewModel.setFigure(FigureName.SPHEROID);
        m_viewModel.setParameter1("2.0");
        m_viewModel.setParameter2("3.0");
        m_viewModel.solve();
        assertEquals(m_viewModel.getVolume(), String.valueOf(
                VolumesComputer.solve(new Spheroid(2.0, 3.0))));
    }
    @Test
    public void solveSpheroidNegativeParameters() {
        m_viewModel.setFigure(FigureName.SPHEROID);
        m_viewModel.setParameter1("2.0");
        m_viewModel.setParameter2("-3.0");
        m_viewModel.solve();
        assertEquals(m_viewModel.getVolume(),
                ComputerViewModel.BAD_VOLUME_STRING);
    }

    @Test
    public void solveRightCylinderGoodParameters() {
        m_viewModel.setFigure(FigureName.RIGHT_CYLINDER);
        m_viewModel.setParameter1("2.0");
        m_viewModel.setParameter2("3.0");
        m_viewModel.setParameter3("4.0");
        m_viewModel.solve();
        assertEquals(m_viewModel.getVolume(), String.valueOf(
                VolumesComputer.solve(new RightCylinder(2.0, 3.0, 4.0))));
    }
    @Test
    public void solveRightCylinderNegativeParameters() {
        m_viewModel.setFigure(FigureName.RIGHT_CYLINDER);
        m_viewModel.setParameter1("2.0");
        m_viewModel.setParameter2("-3.0");
        m_viewModel.setParameter3("4.0");
        m_viewModel.solve();
        assertEquals(m_viewModel.getVolume(),
                ComputerViewModel.BAD_VOLUME_STRING);
    }

    @Test
    public void solveRightCircularConeGoodParameters() {
        m_viewModel.setFigure(FigureName.RIGHT_CIRCULAR_CONE);
        m_viewModel.setParameter1("2.0");
        m_viewModel.setParameter2("3.0");
        m_viewModel.solve();
        assertEquals(m_viewModel.getVolume(), String.valueOf(
                VolumesComputer.solve(new RightCircularCone(2.0, 3.0))));
    }
    @Test
    public void solveRightCircularConeNegativeParameters() {
        m_viewModel.setFigure(FigureName.RIGHT_CIRCULAR_CONE);
        m_viewModel.setParameter1("2.0");
        m_viewModel.setParameter2("-3.0");
        m_viewModel.solve();
        assertEquals(m_viewModel.getVolume(),
                ComputerViewModel.BAD_VOLUME_STRING);
    }

    @Test
    public void parametersNamesCuboid() {
        m_viewModel.setFigure(FigureName.CUBOID);
        assertArrayEquals(FigureName.CUBOID.getParametersNames(),
                new String[] {
                        m_viewModel.getParameter1name(),
                        m_viewModel.getParameter2name(),
                        m_viewModel.getParameter3name()
                });
    }
    @Test
    public void parametersNamesSpheroid() {
        m_viewModel.setFigure(FigureName.SPHEROID);
        assertEquals(m_viewModel.getParameter3name(),
                m_viewModel.DISABLE_PARAMETER_STRING);
        assertArrayEquals(FigureName.SPHEROID.getParametersNames(),
                new String[] {
                        m_viewModel.getParameter1name(),
                        m_viewModel.getParameter2name()
                });
    }
    @Test
    public void parametersNamesRightCylinder() {
        m_viewModel.setFigure(FigureName.RIGHT_CYLINDER);
        assertArrayEquals(FigureName.RIGHT_CYLINDER.getParametersNames(),
                new String[] {
                        m_viewModel.getParameter1name(),
                        m_viewModel.getParameter2name(),
                        m_viewModel.getParameter3name()
                });
    }
    @Test
    public void parametersNamesRightCircularCone() {
        m_viewModel.setFigure(FigureName.RIGHT_CIRCULAR_CONE);
        assertEquals(m_viewModel.getParameter3name(),
                m_viewModel.DISABLE_PARAMETER_STRING);
        assertArrayEquals(FigureName.RIGHT_CIRCULAR_CONE.getParametersNames(),
                new String[] {
                        m_viewModel.getParameter1name(),
                        m_viewModel.getParameter2name()
                });
    }
}
