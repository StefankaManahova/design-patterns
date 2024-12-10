package factories;
//interface for all factories

import figures.Figure;

public interface FigureFactory
{
    public Figure create() throws Exception;
}
