package figures;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class CircleTest
{
    @Test
    @DisplayName("Test invalid construction")
    public void invalidConstruction()
    {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class,
                        () -> new Circle(-4)),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> new Circle(0))
        );
    }

    @Test
    @DisplayName("Test perimeter()")
    public void perimeter()
    {
        Circle c1 = new Circle(1);
        Circle c2  = new Circle(2.8);
        Circle c3 = new Circle(1.765);

        assertEquals(c1.perimeter(), 6.283185307, 0.0001);
        assertEquals(c2.perimeter(), 17.592918860, 0.0001);
        assertEquals(c3.perimeter(), 11.089822067, 0.0001);
    }

    @Test
    @DisplayName("Test toString()")
    public void testToString()
    {
        Circle c1 = new Circle(1);
        Circle c2  = new Circle(2.8);
        Circle c3 = new Circle(1.765);

        assertEquals(c1.toString(), "circle 1,00");
        assertEquals(c2.toString(), "circle 2,80");
        assertEquals(c3.toString(), "circle 1,77");
    }

    @Test
    @DisplayName("Test clone()")
    public void testClone()
    {
        Circle original = new Circle(5);
        Circle copy = original.clone();

        assertEquals(original.perimeter(), copy.perimeter());
        assertNotSame(copy, original);
    }
}