package figures;

//interface for figures

public abstract class Figure implements Cloneable
{
    public abstract double perimeter();

    public abstract Figure clone();
}