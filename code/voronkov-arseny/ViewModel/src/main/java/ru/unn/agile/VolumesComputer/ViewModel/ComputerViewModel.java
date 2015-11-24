package ru.unn.agile.VolumesComputer.ViewModel;

public class ComputerViewModel {
    private String m_parameter1str;
    private String m_parameter2str;
    private String m_parameter3str;
    private double m_parameter1;
    private double m_parameter2;
    private double m_parameter3;
    private FigureName m_figureName;

    public ComputerViewModel() {
        m_parameter1str = m_parameter2str = m_parameter3str = "";
        m_parameter1 = m_parameter2 = m_parameter3 = 0.0;
        m_figureName = FigureName.CUBOID;
    }
    public void setParameter1(final String parameterString) {
        m_parameter1str = parameterString;
    }
    public void setParameter2(final String parameterString) {
        m_parameter2str = parameterString;
    }
    public void setParameter3(final String parameterString) {
        m_parameter3str = parameterString;
    }
    public void setFigure(final FigureName figureName) {
        m_figureName = figureName;
    }
    public String getParameter1() {
        return m_parameter1str;
    }
    public String getParameter2() {
        return m_parameter2str;
    }
    public String getParameter3() {
        return m_parameter3str;
    }
    public FigureName getFigure() {
        return m_figureName;
    }
    public boolean isParameter1enabled() {
        return m_figureName.getParametersCount() >= 1;
    }
    public boolean isParameter2enabled() {
        return m_figureName.getParametersCount() >= 2;
    }
    public boolean isParameter3enabled() {
        return m_figureName.getParametersCount() >= 3;
    }

}
