package figures;

//a class for the rectangles

public class Rectangle extends Figure
{
    private final double a;
    private final double b;

    public Rectangle(double a, double b)
    {
        if (a <= 0 || b <= 0)
        {
            throw new IllegalArgumentException("The rectangle sides must be " +
                                                "positive numbers\n");
        }
        else if (a > Double.MAX_VALUE - a || 2 * a > Double.MAX_VALUE - b ||
                2 * a + b > Double.MAX_VALUE - b)
        {
            throw new IllegalArgumentException("The values of the rectangle sides are " +
                                                "too large and would cause overflow\n");
        }
        else
        {
            this.a = a;
            this.b = b;
        }
    }

    public Rectangle(Rectangle other)
    {
        if (other == null)
        {
            throw new IllegalArgumentException("Null object can't be passed as an " +
                    "argument");
        }

        this.a = other.a;
        this.b = other.b;
    }

    @Override
    public double perimeter()
    {
        return 2 * a + 2 * b;
    }

    @Override
    public String toString()
    {
        return "rectangle " + String.format("%.2f", a) +
                " " + String.format("%.2f", b);
    }

    @Override
    public Rectangle clone() {
        return new Rectangle(this);
    }
}
