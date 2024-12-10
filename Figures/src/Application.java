//the application

import factories.AbstractFactory;
import factories.FigureFactory;
import figures.Figure;

import java.util.Locale;
import java.util.Scanner;

public class Application implements Runnable
{
    private FigureList figureList;

    public Application()
    {
        figureList = new FigureList();
    }

    @Override
    public void run()
    {
        System.out.println("Hello! Welcome to our geometric world!");
        System.out.println("How would you like to enter your figures? Please type \"stdin\", " +
                "\"random\" or \"file\" (followed by the file name) for your preferred method.");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        AbstractFactory abstractFactory = new AbstractFactory();

        try
        {
            FigureFactory factory = abstractFactory.createFactory(input);
            Figure fig;

            System.out.println("How many figures would you like to create?");
            int n = sc.nextInt();
            sc.nextLine();

            for (int i = 0; i < n; i++)
            {
                fig = factory.create();
                figureList.add(fig);
            }

            System.out.println(n + " figures were successfully created.");

            System.out.println("Now you can do all sorts of things: ");
            System.out.println("\tto delete a figure, type \"delete\" followed by " +
                                "the index of the figure you wish to remove");
            System.out.println("\tto list all figures, type \"list\"");
            System.out.println("\tto clone a figure, type \"clone\" followed by " +
                    "the index of the figure you wish to duplicate; it will be added at the end of the list");
            System.out.println("\tto store the figures in a file, type \"save\" followed by " +
                    "the name of the file where you wish to save them");
            System.out.println("\tto quit, type \"quit\"");

            boolean run = true;

            while (run)
            {
                System.out.print("> ");
                String[] args = sc.nextLine().toLowerCase(Locale.US).split("\\s+");

                switch(args[0])
                {
                    case "delete" -> figureList.delete(args);
                    case "list" -> figureList.list();
                    case "clone" -> figureList.clone(args);
                    case "save" -> figureList.save(args);
                    case "quit" ->
                    {
                        abstractFactory.closeResources();
                        System.out.println("Goodbye!");
                        run = false;
                    }
                    default ->
                    {
                        System.out.println("Unrecognised command");
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
