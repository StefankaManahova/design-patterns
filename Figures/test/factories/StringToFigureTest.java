package factories;

import figures.Circle;
import figures.Figure;
import figures.Rectangle;
import figures.Triangle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class StringToFigureTest {

    @Test
    @DisplayName ("Test createFrom() for triangle")
    public void createFromTriangle()
    {
        Figure fig = StringToFigure.createFrom("triangle 3 4 5");
        assertEquals(fig.perimeter(), 12, 0.0001);
        assertEquals(fig.getClass(), Triangle.class);
        assertNotEquals(fig.getClass(), Circle.class);
    }

    @Test
    @DisplayName("Test createFrom() for circle")
    public void createFromCircle()
    {
        Figure fig = StringToFigure.createFrom("circle 2");
        assertEquals(fig.perimeter(), 12.566370614, 0.0001);
        assertEquals(fig.getClass(), Circle.class);
        assertNotEquals(fig.getClass(), Rectangle.class);
    }

    @Test
    @DisplayName ("Test createFrom() for triangle")
    public void createFromRectangle()
    {
        Figure fig = StringToFigure.createFrom("rectangle 6,893 4,626");
        assertEquals(fig.perimeter(), 23.038, 0.0001);
        assertEquals(fig.getClass(), Rectangle.class);
        assertNotEquals(fig.getClass(), Triangle.class);
    }

    @Test
    @DisplayName ("Test createFrom() with wrong number of parameters")
    public void wrongArgCount()
    {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> StringToFigure.createFrom("triangle 2,4 3")),
                () -> assertThrows(IllegalArgumentException.class, () -> StringToFigure.createFrom("rectangle 1,8")),
                () -> assertThrows(IllegalArgumentException.class, () -> StringToFigure.createFrom("circle"))
        );
    }

    @Test
    @DisplayName ("Test createFrom() with ill-formed of parameters")
    public void illFormedArgs()
    {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> StringToFigure.createFrom("triangle 1 abc 3")),
                () -> assertThrows(IllegalArgumentException.class, () -> StringToFigure.createFrom("rectangle 1,8 -")),
                () -> assertThrows(IllegalArgumentException.class, () -> StringToFigure.createFrom("circle %")),
                () -> assertThrows(IllegalArgumentException.class, () -> StringToFigure.createFrom("triangle a b cd"))
        );
    }

    @Test
    @DisplayName ("Test createFrom() with unknown figure type")
    public void unknownType()
    {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> StringToFigure.createFrom("traingle 4 2 3")),
                () -> assertThrows(IllegalArgumentException.class, () -> StringToFigure.createFrom("retcangle 1,8 3,6")),
                () -> assertThrows(IllegalArgumentException.class, () -> StringToFigure.createFrom("cricle 2")),
                () -> assertThrows(IllegalArgumentException.class, () -> StringToFigure.createFrom("unknown 1 4 7"))
        );
    }

    @Test
    @DisplayName ("Test createFrom() with negative arguments")
    public void negativeArgs()
    {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> StringToFigure.createFrom("triangle 3 4 -5")),
                () -> assertThrows(IllegalArgumentException.class, () -> StringToFigure.createFrom("triangle -3,9 8 5,7")),
                () -> assertThrows(IllegalArgumentException.class, () -> StringToFigure.createFrom("triangle 7 -6 8")),
                () -> assertThrows(IllegalArgumentException.class, () -> StringToFigure.createFrom("triangle 0 9 7")),
                () -> assertThrows(IllegalArgumentException.class, () -> StringToFigure.createFrom("rectangle 0 8")),
                () -> assertThrows(IllegalArgumentException.class, () -> StringToFigure.createFrom("rectangle 6 0")),
                () -> assertThrows(IllegalArgumentException.class, () -> StringToFigure.createFrom("rectangle 3 -8")),
                () -> assertThrows(IllegalArgumentException.class, () -> StringToFigure.createFrom("rectangle 5 -2"))

        );
    }
}