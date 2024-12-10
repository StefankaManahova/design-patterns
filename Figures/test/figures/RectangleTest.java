package figures;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class RectangleTest
{
    @Test
    @DisplayName("Test constructor")
    public void constructor()
    {
        assertAll(
                () -> assertDoesNotThrow(
                        () -> new Rectangle(1, 2)),
                () -> assertDoesNotThrow(
                        () -> new Rectangle(4, 3)),
                () -> assertDoesNotThrow(
                        () -> new Rectangle(4.87, 3.65)),
                () -> assertDoesNotThrow(
                        () -> new Rectangle(0.007, 0.002))
        );
    }

    @Test
    @DisplayName("Test invalid construction")
    public void invalidConstruction()
    {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class,
                        () -> new Rectangle(-4, 7)),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> new Rectangle(4, -7)),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> new Rectangle(4, 0)),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> new Rectangle(0, 7))
        );
    }

    @Test
    @DisplayName("Test perimeter()")
    public void perimeter()
    {
        Rectangle r1 = new Rectangle(3, 4);
        Rectangle r2 = new Rectangle(9.0, 7.6);

        assertEquals(r1.perimeter(), 14, 0.0001);
        assertEquals(r2.perimeter(), 33.2, 0.0001);
    }

    @Test
    @DisplayName("Test toString()")
    public void testToString()
    {
        Rectangle r1 = new Rectangle(9.875, 7.07);
        Rectangle r2 = new Rectangle(9, 7);
        Rectangle r3 = new Rectangle(3.06532345, 1.583625);
        Rectangle r4 = new Rectangle(2.8423527, 8.4252729);

        assertEquals(r1.toString(), "rectangle 9,88 7,07");
        assertEquals(r2.toString(), "rectangle 9,00 7,00");
        assertEquals(r3.toString(), "rectangle 3,07 1,58");
        assertEquals(r4.toString(), "rectangle 2,84 8,43");
    }

    @Test
    @DisplayName("Test clone()")
    public void testClone()
    {
        Rectangle original = new Rectangle(2,6);
        Rectangle copy = original.clone();

        assertEquals(original.perimeter(), copy.perimeter());
        assertNotSame(copy, original);
    }
}