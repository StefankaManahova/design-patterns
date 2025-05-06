package cli;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;


public class CommandParserMode2Test
{

    @Test
    @DisplayName("Test verification mode")
    public void testVerification() {
        CommandParser parser = new CommandParser();

        String input = "calc --path test\\res_for_modification --algorithm md5 --format xml --result test\\utils_2\\old_checksums.txt";
        try {
            //save checksums in file old_checksums.txt
            parser.execute(input);

            //make changes
            FileOutputStream out = new FileOutputStream("test\\res_for_modification\\3.txt");
            String text3 = "Your love is like bad medicine,\nbad medicine is what I need";
            out.write(text3.getBytes());
            out.close();

            out = new FileOutputStream("test\\res_for_modification\\1.txt");
            String text1 = "You take a mortal man\nand put him in control\nWatch him become a god";
            out.write(text1.getBytes());
            out.close();

            out = new FileOutputStream("test\\res_for_modification\\pack\\4.txt", true);
            String text4 = "by Nirvana";
            out.write(text4.getBytes());
            out.close();

            out = new FileOutputStream("test\\res_for_modification\\pack\\5.txt", true);
            String text5 = "Give me fuel, give me fire\nGive that which I desire\n";
            out.write(text5.getBytes());
            out.close();

            //report modifications
            input = "verify --path test\\res_for_modification --algorithm md5 --checksums test\\utils_2\\old_checksums.txt --result test\\utils_2\\new_checksums.txt";
            parser.execute(input);

            StringBuilder modifications = new StringBuilder();
            FileInputStream inputStream = new FileInputStream("test\\utils_2\\new_checksums.txt");
            Scanner in = new Scanner(inputStream);
            while (in.hasNextLine()) {
                modifications.append(in.nextLine()).append('\n');
            }
            in.close();

            //we don't know the exact order in which the files are visited,
            //so we sort the lines in the report
            String[] lines = modifications.toString().trim().split("\\r?\\n");
            Arrays.sort(lines);

            StringBuilder sorted = new StringBuilder();
            for (String line : lines) {
                sorted.append(line).append("\n");
            }

            //restore previous state
            out = new FileOutputStream("test\\res_for_modification\\1.txt");
            String oldText1 = "Hello me\nMeet the real me\n";
            out.write(oldText1.getBytes());
            out.close();

            out = new FileOutputStream("test\\res_for_modification\\pack\\4.txt");
            String oldText4 = "Come as you are, as you were\nAs I want you to be\nAs a friend, as a friend\nAs an old enemy\n";
            out.write(oldText4.getBytes());
            out.close();

            File toDelete = new File("test\\res_for_modification\\3.txt");
            if (!toDelete.delete()) {
                System.out.println("Failed to delete 3.txt");
            }

            File toDelete2 = new File("test\\res_for_modification\\pack\\5.txt");
            if (!toDelete2.delete()) {
                System.out.println("Failed to delete 5.txt");
            }

            String expected = """
                    test\\res_for_modification\\1.txt: MODIFIED
                    test\\res_for_modification\\2.txt: OK
                    test\\res_for_modification\\3.txt: NEW
                    test\\res_for_modification\\pack\\4.txt: MODIFIED
                    test\\res_for_modification\\pack\\5.txt: NEW
                    """;

            assertEquals(expected, sorted.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Incorrect checksums file")
    public void testIncorrectInputCalc6() {
        CommandParser parser = new CommandParser();
        String input = "verify --path test\\res_for_modification --algorithm md5 --checksums src\\fileTree\\BaseFile.java";

        assertThrows(IllegalArgumentException.class, () -> parser.execute(input));
    }
}
