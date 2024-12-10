package figures;

// a class for the triangles

public class Triangle extends Figure
{
    //variables for side lengths are final because triangles are immutable
    private final double a;
    private final double b;
    private final double c;

    public Triangle(double a, double b, double c)
    {
        if (a <= 0 || b <= 0 || c <= 0)
        {
            throw new IllegalArgumentException("The triangle sides must be " +
                                                "positive numbers\n");
        }
        else if (a + b <= c || a + c <= b || b + c <= a)
        {
            throw new IllegalArgumentException("The triangle sides must " +
                                                "follow the triangle inequality\n");
        }
        //we have to make sure we don't overflow
        else if (a > Double.MAX_VALUE - b || a + b > Double.MAX_VALUE - c)
        {
            throw new IllegalArgumentException("The values of the triangle sides are too " +
                                            "large and would cause overflow\n");
        }
        else
        {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    public Triangle(Triangle other)
    {
        if (other == null)
        {
            throw new IllegalArgumentException("Null object can't be passed as an " +
                    "argument");
        }

        this.a = other.a;
        this.b = other.b;
        this.c = other.c;
    }

    @Override
    public double perimeter()
    {
        return a + b + c;
    }

    @Override
    public String toString()
    {
        return "triangle " + String.format("%.2f", a) + " "
                + String.format("%.2f", b) + " " + String.format("%.2f", c);
    }

    @Override
    public Triangle clone() {
        return new Triangle(this);
    }
}
