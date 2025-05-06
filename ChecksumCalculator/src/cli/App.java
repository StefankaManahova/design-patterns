package cli;

import java.util.Scanner;

public class App
{
    public void run()
    {
        CommandParser parser = new CommandParser();
        Scanner sc = new Scanner(System.in);

        while (true)
        {
            System.out.print("> ");
            String input = sc.nextLine();

            if (input.equalsIgnoreCase("exit")
                    || input.equalsIgnoreCase("quit"))
            {
                System.out.println("Goodbye! :)");
                break;
            }

            try
            {
                parser.execute(input);
            } catch (Exception ex)
            {
                System.out.println(ex.toString());
            }

        }
    }
}
