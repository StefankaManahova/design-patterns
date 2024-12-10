package factories;
// a class that creates a figure from a given string

import figures.Circle;
import figures.Figure;
import figures.Rectangle;
import figures.Triangle;

import java.util.Locale;
import java.util.Scanner;

public class StringToFigure
{
    public static Figure createFrom(String str) throws IllegalArgumentException
    {
        try (Scanner sc = new Scanner(str))
        {
            String type = sc.next().toLowerCase(Locale.US);

            switch(type)
            {
                case "triangle" ->
                {
                    double a = sc.nextDouble();
                    double b = sc.nextDouble();
                    double c = sc.nextDouble();

                    return new Triangle(a, b, c);
                }
                case "rectangle" ->
                {
                    double a = sc.nextDouble();
                    double b = sc.nextDouble();

                    return new Rectangle(a, b);
                }
                case "circle" ->
                {
                    double r = sc.nextDouble();

                    return new Circle(r);
                }
                default ->
                {
                    throw new IllegalArgumentException("There is no such figure supported\n");
                }
            }

        } catch (Exception e)
        {
            e.printStackTrace();
            throw new IllegalArgumentException("Failed to create a figure from the string\n");
        }
    }
}
