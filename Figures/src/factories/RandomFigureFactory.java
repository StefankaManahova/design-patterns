package factories;
// factory creating random figures

import figures.Circle;
import figures.Figure;
import figures.Rectangle;
import figures.Triangle;

public class RandomFigureFactory implements FigureFactory
{
    @Override
    public Figure create()
    {
        Figure res;
        int type = (int) (Math.random() * 3);

        switch (type)
        {
            case 0 ->
            {
                //create a triangle - we need three parameters

                //we generate two sides that are at least 1 and are less than 41
                double a = 1 + Math.random() * 40;
                double b = 1 + Math.random() * 40;

                //we round them up to 2 decimal digits for simplicity
                a = Math.round(a * 100) / 100.0;
                b = Math.round (b * 100) / 100.0;

                /*  Suppose a < b.
                    c > a + b - ((a - 1) + 1) = b = max(a,b) and
                    c <= a + b - (0 + 1) = a + b - 1,
                    so it is the longest side
                    and it is smaller than a + b  */
                double c = a + b - (Math.random() * (Math.min(a,b) - 1) + 1);

                //we round up c as well
                c = Math.round(c * 100) / 100.0;

                return new Triangle(a, b, c);
            }
            case 1 ->
            {
                //create a rectangle - we need two parameters

                //we generate two sides that are at least 1 and are less than 41
                double a = 1 + Math.round( (Math.random() * 40) * 100) / 100.0;
                double b = 1 + Math.round( (Math.random() * 40) * 100) / 100.0;

                //we round them up to 2 decimal digits for simplicity
                a = Math.round(a * 100) / 100.0;
                b = Math.round (b * 100) / 100.0;

                return new Rectangle(a, b);
            }
            case 2 ->
            {
                //create a circle - we need one parameter

                //we generate a radius that is at least 1 and are less than 31
                double r = 1 + Math.round( (Math.random() * 30) * 100) / 100.0;

                //and we round it up to 2 decimal digits for simplicity
                r = Math.round(r * 100) / 100.0;

                return new Circle(r);
            }
            default ->
            {
                throw new RuntimeException("Incorrect random generation");
            }
        }
    }
}


