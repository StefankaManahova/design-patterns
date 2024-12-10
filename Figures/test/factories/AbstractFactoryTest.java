package factories;

import figures.Circle;
import figures.Figure;
import figures.Rectangle;
import figures.Triangle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class AbstractFactoryTest
{
    @Test
    @DisplayName("Test that the abstract factory creates a stdin factory correctly")
    public void createFromSTDIN()
    {
        AbstractFactory abstractFactory = new AbstractFactory();

        try
        {
            FigureFactory factory = abstractFactory.createFactory("STDIN");

            assertEquals(factory.getClass(), StreamFigureFactory.class);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Test that the abstract factory creates a random factory correctly")
    public void createRandom()
    {
        AbstractFactory abstractFactory = new AbstractFactory();

        try
        {
            FigureFactory factory = abstractFactory.createFactory("Random");

            assertEquals(factory.getClass(), RandomFigureFactory.class);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Test that the abstract factory creates a file input stream factory correctly")
    public void createFromFile()
    {
        AbstractFactory abstractFactory = new AbstractFactory();

        try
        {
            FigureFactory factory = abstractFactory.createFactory("File test/utils/test_figures.txt");

            assertEquals(factory.getClass(), StreamFigureFactory.class);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Test that the abstract factory creates figures from a file input stream correctly")
    public void createFromFile2()
    {
        AbstractFactory abstractFactory = new AbstractFactory();

        try
        {
            FigureFactory factory = abstractFactory.createFactory("File test/utils/test_figures.txt");

            Figure fig = factory.create();

            assertEquals(fig.getClass(), Triangle.class);
            assertEquals(fig.perimeter(), 12, 0.0001);

            fig = factory.create();

            assertEquals(fig.getClass(), Rectangle.class);
            assertEquals(fig.perimeter(), 22, 0.0001);

            fig = factory.create();

            assertEquals(fig.getClass(), Circle.class);
            assertEquals(fig.perimeter(), 16.964600329, 0.0001);

            assertThrows(IllegalArgumentException.class, () -> factory.create());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Test that the abstract factory throws an exception if an incorrect string is passed in constructor")
    public void invalidString()
    {
        AbstractFactory abstractFactory = new AbstractFactory();

        try
        {
            assertAll(
                    () -> assertThrows(IllegalArgumentException.class,
                            () -> abstractFactory.createFactory("File ")),
                    () -> assertThrows(IllegalArgumentException.class,
                            () -> abstractFactory.createFactory("Song lyrics")),
                    () -> assertThrows(IllegalArgumentException.class,
                            () -> abstractFactory.createFactory("Horrible Histories"))
            );

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}