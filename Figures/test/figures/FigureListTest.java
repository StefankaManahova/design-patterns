import figures.Circle;
import figures.Rectangle;
import figures.Triangle;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class FigureListTest
{
    @Test
    public void add()
    {
        FigureList list = new FigureList();
        list.add( new Triangle(3, 4, 5) );
        list.add( new Rectangle(6, 7) );
        list.add( new Circle(3) );

        assertEquals(list.size(), 3);
        assertEquals(Triangle.class, list.get(0).getClass());
        assertEquals(Rectangle.class, list.get(1).getClass());
        assertEquals(Circle.class, list.get(2).getClass());
    }

    @Test
    public void delete()
    {
        FigureList list = new FigureList();
        list.add( new Triangle(3, 4, 5) );
        list.add( new Rectangle(6, 7) );
        list.add( new Circle(3) );

        String[] args = {"delete", "2"};
        list.delete(args);

        assertEquals(list.size(), 2);
        assertEquals(Triangle.class, list.get(0).getClass());
        assertEquals(Circle.class, list.get(1).getClass());
    }

    @Test
    public void testClone()
    {
        FigureList list = new FigureList();
        list.add( new Triangle(3, 4, 5) );
        list.add( new Rectangle(6, 7) );
        list.add( new Circle(3) );

        String[] args = {"clone", "1"};
        list.clone(args);

        assertEquals(list.size(), 4);
        assertEquals(Triangle.class, list.get(0).getClass());
        assertEquals(Rectangle.class, list.get(1).getClass());
        assertEquals(Circle.class, list.get(2).getClass());
        assertEquals(Triangle.class, list.get(3).getClass());

        assertEquals(list.get(0).perimeter(), list.get(3).perimeter());
    }

    @Test
    public void save()
    {
        FigureList list = new FigureList();
        list.add( new Triangle(3, 4, 5) );
        list.add( new Rectangle(6, 7) );
        list.add( new Circle(3) );
        list.add( new Circle(3.5));
        list.add( new Rectangle(4.8, 9.3));

        String[] args = {"save", "Figures/test/utils/saved_figures.txt"};
        list.save(args);

        try (BufferedReader in = new BufferedReader(new InputStreamReader
                            (new FileInputStream(args[1]))))
        {
            String s;
            StringBuilder res = new StringBuilder();

            while((s = in.readLine()) != null)
            {
                res.append(s);
                res.append("\n");
            }

            assertEquals(res.toString(),
                    """
                            triangle 3,00 4,00 5,00
                            rectangle 6,00 7,00
                            circle 3,00
                            circle 3,50
                            rectangle 4,80 9,30
                            """);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}