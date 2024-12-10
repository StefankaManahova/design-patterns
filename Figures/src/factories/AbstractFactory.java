package factories;
//abstract factory design pattern

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;

public class AbstractFactory
{
    private ArrayList<FileInputStream> openedStreams;

    public AbstractFactory()
    {
        openedStreams = new ArrayList<>();
    }

    public FigureFactory createFactory(String input) throws FileNotFoundException
    {
        if (input == null) throw new IllegalArgumentException("Argument shouldn't be null\n");

        String[] args = input.toLowerCase(Locale.US).split("\\s+");
        String type = args[0];
        int count = args.length;

        switch(type)
        {
            case "random" ->
            {
                return new RandomFigureFactory();
            }
            case "stdin" ->
            {
                return new StreamFigureFactory(new BufferedInputStream(System.in));
            }
            case "file" ->
            {
                if (count < 2)
                {
                    throw new IllegalArgumentException("Please provide a name of the file\n");
                }

                try
                {
                    FileInputStream in = new FileInputStream(args[1]);

                    openedStreams.add(in);
                    return new StreamFigureFactory(in);

                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
            default ->
            {
                throw new IllegalArgumentException("This is not a supported way of entering" +
                        " figures\n");
            }
        }
    }

    public void closeResources()
    {
        for (FileInputStream in : openedStreams)
        {
            try {
                in.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
