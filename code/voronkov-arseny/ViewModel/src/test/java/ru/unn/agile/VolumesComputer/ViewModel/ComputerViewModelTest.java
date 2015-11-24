package ru.unn.agile.VolumesComputer.ViewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
                assertTrue(!(m_viewModel.isParameter1enabled()
                        && m_viewModel.isParameter2enabled()
                        && m_viewModel.isParameter3enabled()));
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
}
