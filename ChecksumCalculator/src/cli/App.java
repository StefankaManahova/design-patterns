package cli;

import java.util.Scanner;

public class App
{
    public void run()
    {
        Scanner sc = new Scanner(System.in);

        while (true)
        {
            //System.out.print("\033[5d\r> "); // Set cursor to the command line
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
                Command command = CommandParser.parse(input);

                if (command != null)
                {
                    command.run();
                }
            } catch (Exception ex)
            {
                System.out.println(ex.toString());
            }

        }
    }
}
