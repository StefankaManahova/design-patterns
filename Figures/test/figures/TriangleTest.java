package figures;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class TriangleTest
{
    @Test
    @DisplayName("Test constructor")
    public void constructor()
    {
        assertAll(
                () -> assertDoesNotThrow(
                        () -> new Triangle(1, 2, 1.6)),
                () -> assertDoesNotThrow(
                        () -> new Triangle(4, 3, 2)),
                () -> assertDoesNotThrow(
                        () -> new Triangle(4.87, 3.65, 2.02)),
                () -> assertDoesNotThrow(
                        () -> new Triangle(0.007, 0.002, 0.006))
        );
    }

    @Test
    @DisplayName("Test invalid construction")
    public void invalidConstruction()
    {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class,
                        () -> new Triangle(1, 7, 5)),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> new Triangle(4, 1, 2)),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> new Triangle(10, 7, 50)),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> new Triangle(-4, 7, 5)),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> new Triangle(4, -7, 5)),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> new Triangle(4, 7, -5)),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> new Triangle(4, 7, 0)),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> new Triangle(4, 0, 7)),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> new Triangle(0, 7, 5))
        );
    }

    @Test
    @DisplayName("Test perimeter()")
    public void perimeter()
    {
        Triangle t1 = new Triangle(3, 4, 5);
        Triangle t2 = new Triangle(2.8, 9.0, 7.6);

        assertEquals(t1.perimeter(), 12, 0.0001);
        assertEquals(t2.perimeter(), 19.4, 0.0001);
    }

    @Test
    @DisplayName("Test toString()")
    public void testToString()
    {
        Triangle t1 = new Triangle(3, 4, 5);
        Triangle t2 = new Triangle(2.8, 9.0, 7.6);
        Triangle t3 = new Triangle(3.0863562, 4.073662, 5.0424677);
        Triangle t4 = new Triangle(1.8, 2.4, 1.6);

        assertEquals(t1.toString(), "triangle 3,00 4,00 5,00");
        assertEquals(t2.toString(), "triangle 2,80 9,00 7,60");
        assertEquals(t3.toString(), "triangle 3,09 4,07 5,04");
        assertEquals(t4.toString(), "triangle 1,80 2,40 1,60");
    }

    @Test
    @DisplayName("Test clone()")
    public void testClone()
    {
        Triangle original = new Triangle(2,5,6);
        Triangle copy = original.clone();

        assertEquals(original.perimeter(), copy.perimeter());
        assertNotSame(copy, original);
    }
}