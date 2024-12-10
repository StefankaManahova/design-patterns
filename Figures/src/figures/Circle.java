package figures;

//a class for the circles

public class Circle extends Figure
{
    //variable for radius length is final because circles are immutable
    private final double radius;

    public Circle(double radius)
    {
        if (radius <= 0)
        {
            throw new IllegalArgumentException("The circle radius must be a " +
                                                "positive number\n");
        }
        //we have to make sure we don't overflow
        else if (radius > Double.MAX_VALUE / (2 * Math.PI))
        {
            throw new IllegalArgumentException("The value of the radius is too large " +
                                                "and would cause overflow\n");
        }
        else
        {
            this.radius = radius;
        }
    }

    public Circle(Circle other)
    {
        if (other == null)
        {
            throw new IllegalArgumentException("Null object can't be passed as an " +
                    "argument");
        }
        this.radius = other.radius;
    }

    @Override
    public double perimeter()
    {
        return 2 * radius * Math.PI;
    }

    @Override
    public String toString()
    {
        return "circle " + String.format("%.2f", radius);
    }

    @Override
    public Circle clone() {
        return new Circle(this);
    }
}
