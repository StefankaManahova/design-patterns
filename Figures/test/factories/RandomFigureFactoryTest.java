package factories;

import figures.Circle;
import figures.Figure;
import figures.Rectangle;
import figures.Triangle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class RandomFigureFactoryTest
{
    public final int SIZE = 100000;
    @Test
    @DisplayName("Test that all figure constructors don't throw exceptions")
    public void testNoThrow()
    {
        RandomFigureFactory rand = new RandomFigureFactory();

        for (int i = 0; i < SIZE; i++)
        {
            assertDoesNotThrow(() -> rand.create());
        }
    }

    @Test
    @DisplayName("Test that the sides are generated in the correct range")
    public void testRange()
    {
        RandomFigureFactory rand = new RandomFigureFactory();
        Figure fig;

        for (int i = 0; i < SIZE; i++)
        {
            fig = rand.create();

            assertTrue(fig.perimeter() <= 200);
            assertTrue(fig.perimeter() >= 2);
        }
    }

    @Test
    @DisplayName("Test that the distribution is even")
    public void testDistribution()
    {
        RandomFigureFactory rand = new RandomFigureFactory();
        Figure fig;

        int t_cnt = 0;
        int r_cnt = 0;
        int c_cnt = 0;

        for (int i = 0; i < SIZE; i++)
        {
            fig = rand.create();

            if (fig.getClass() == Triangle.class)
            {
                ++t_cnt;
            }
            else if (fig.getClass() == Circle.class)
            {
                ++c_cnt;
            }
            else if (fig.getClass() == Rectangle.class)
            {
                ++r_cnt;
            }
            else
            {
                assertTrue(false);
            }
        }

        assertTrue(t_cnt > SIZE / 4);
        assertTrue(r_cnt > SIZE / 4);
        assertTrue(c_cnt > SIZE / 4);
    }

}