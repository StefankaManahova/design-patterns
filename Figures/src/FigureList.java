//wrapper class for a list of figures

import figures.Figure;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class FigureList
{
    private ArrayList<Figure> figures;

    public FigureList()
    {
        figures = new ArrayList<>();
    }

    public Figure get(int idx)
    {
        return figures.get(idx);
    }

    public void add(Figure fig)
    {
        figures.add(fig);
    }

    public int size()
    {
        return figures.size();
    }

    public void delete(String[] args)
    {
        int count = args.length;

        if (count < 2)
        {
            System.out.println("You must provide an index.");
            return;
        }

        int idx = Integer.parseInt(args[1]);

        if (idx < 1 || idx > figures.size())
        {
            System.out.println("Index out of bounds.");
            return;
        }

        figures.remove(idx - 1);
        System.out.println("Successfully deleted!");
    }

    public void list()
    {
        int size = figures.size();

        for (int i = 0; i < size; i++)
        {
            System.out.println((i + 1) + ". " + figures.get(i).toString());
        }
    }

    public void clone(String[] args)
    {
        int count = args.length;

        if (count < 2)
        {
            System.out.println("You must provide an index.");
            return;
        }

        int idx = Integer.parseInt(args[1]);

        if (idx < 1 || idx > figures.size())
        {
            System.out.println("Index is out of bounds.");
            return;
        }

        Figure fig = figures.get(idx - 1).clone();
        figures.add(fig);

        System.out.println("Successfully cloned!");
    }

    public void save(String[] args)
    {
        int count = args.length;

        if (count < 2)
        {
            System.out.println("You must provide a file name.");
            return;
        }

        try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]))))
        {
            int size = figures.size();

            for (int i = 0; i < size; i++)
            {
                out.write(figures.get(i).toString());
                out.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
