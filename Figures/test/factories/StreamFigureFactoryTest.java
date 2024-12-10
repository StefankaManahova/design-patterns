package factories;

import figures.Circle;
import figures.Figure;
import figures.Rectangle;
import figures.Triangle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class StreamFigureFactoryTest
{
    @Test
    @DisplayName("Test creating figures from a file stream")
    public void readFromFile()
    {
        try (FileInputStream in = new FileInputStream("Figures/test/utils/test_figures.txt"))
        {
            StreamFigureFactory factory = new StreamFigureFactory(in);

            ArrayList<Figure> figs = new ArrayList<>();
            Figure fig;

            for (int i = 0; i < 3; i++)
            {
                fig = factory.create();
                figs.add(i, fig);
            }
            assertAll(
                    () -> assertEquals(figs.get(0).getClass(), Triangle.class),
                    () -> assertEquals(figs.get(0).perimeter(), 12, 0.0001),
                    () -> assertEquals(figs.get(1).getClass(), Rectangle.class),
                    () -> assertEquals(figs.get(1).perimeter(), 22, 0.0001),
                    () -> assertEquals(figs.get(2).getClass(), Circle.class),
                    () -> assertEquals(figs.get(2).perimeter(), 16.964600329384, 0.0001)
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Test creating invalid figures from a file stream")
    public void readInvalidFromFile()
    {
        try (FileInputStream in = new FileInputStream("Figures/test/utils/invalid_figures.txt"))
        {
            StreamFigureFactory factory = new StreamFigureFactory(in);

            Figure fig = factory.create();

            assertAll(
                    () -> assertEquals(fig.getClass(), Circle.class),
                    () -> assertEquals(fig.perimeter(), 56.548667764616, 0.0001),
                    () -> assertThrows(IllegalArgumentException.class, () -> factory.create()),
                    () -> assertThrows(IllegalArgumentException.class, () -> factory.create()),
                    () -> assertThrows(IllegalArgumentException.class, () -> factory.create()),
                    () -> assertThrows(IllegalArgumentException.class, () -> factory.create()),
                    () -> assertEquals(factory.create().perimeter(), 6, 0.0001)
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}