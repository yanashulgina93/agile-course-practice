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
    public String getParameter1() {
        return m_parameter1str;
    }

}
