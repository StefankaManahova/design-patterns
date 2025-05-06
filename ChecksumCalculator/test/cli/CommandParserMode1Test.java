package cli;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class CommandParserMode1Test {
    @Test
    @DisplayName("Test parser in first mode - checksum calculation")
    public void testCorrectInputCalc() {
        CommandParser parser = new CommandParser();
        String input = "calc --path test\\resources --algorithm   sha1 --result test\\utils_2\\resources_report.txt";

        try {
            parser.execute(input);

            FileInputStream inputStream = new FileInputStream("test\\utils_2\\resources_report.txt");
            Scanner in = new Scanner(inputStream);

            StringBuilder res = new StringBuilder();
            while (in.hasNextLine()) {
                res.append(in.nextLine()).append('\n');
            }
            in.close();

            String[] lines = res.toString().trim().split("\\r?\\n");
            Arrays.sort(lines);

            StringBuilder sorted = new StringBuilder();
            for (String line : lines) {
                sorted.append(line).append("\n");
            }

            String exp = """
                    457d08066551a4ee81a3d1ac0fe51071de14e989 \\test\\resources\\folder\\d.txt
                    51252a3214b9198077302e5ec912bdb47c098c4a \\test\\resources\\folder\\e.txt
                    8efdb1e9c430d7fc80c247b770ad6e4974afc258 \\test\\resources\\c.txt
                    a5b81f9cf47b4f7b244c110c37305dab85944c2b \\test\\resources\\b.txt
                    d3486ae9136e7856bc42212385ea797094475802 \\test\\resources\\a.txt
                    """;

            assertEquals(exp, sorted.toString());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    @DisplayName("Default options")
    public void testDefaultCalc() {
        CommandParser parser = new CommandParser();
        String input = "calc --algorithm sha1 --result test\\utils_2\\current_dir_report.txt";

        assertDoesNotThrow(() -> parser.execute(input));
    }

    @Test
    @DisplayName("Missing path argument")
    public void testIncorrectInputCalc() {
        CommandParser parser = new CommandParser();
        String input = "calc --path  --algorithm sha1 --result test\\utils_2\\resources_report.txt";

        assertThrows(RuntimeException.class, () -> parser.execute(input));
    }

    @Test
    @DisplayName("Missing required option")
    public void testIncorrectInputCalc2() {
        CommandParser parser = new CommandParser();
        String input = "calc --path test\\resources --format xml";

        assertThrows(RuntimeException.class, () -> parser.execute(input));
    }

    @Test
    @DisplayName("Unknown algorithm argument")
    public void testIncorrectInputCalc3() {
        CommandParser parser = new CommandParser();
        String input = "calc --algorithm sha7 --format xml --result test\\utils_2\\resources_report.txt";

        assertThrows(RuntimeException.class, () -> parser.execute(input));
    }

    @Test
    @DisplayName("Missing algorithm argument")
    public void testIncorrectInputCalc4() {
        CommandParser parser = new CommandParser();
        String input = "calc --path test\\resources --format xml --algorithm";

        assertThrows(RuntimeException.class, () -> parser.execute(input));
    }

    @Test
    @DisplayName("Unknown option")
    public void testIncorrectInputCalc5() {
        CommandParser parser = new CommandParser();
        String input = "calc --path test\\resources --format xml --algorithm md5 --size 14";

        assertThrows(RuntimeException.class, () -> parser.execute(input));
    }

    @Test
    @DisplayName("Incorrect format argument")
    public void testIncorrectInputCalc6() {
        CommandParser parser = new CommandParser();
        String input = "calc --path test\\resources --format gnr --algorithm md5";

        assertThrows(IllegalArgumentException.class, () -> parser.execute(input));
    }

    @Test
    @DisplayName("Incorrect path argument")
    public void testIncorrectInputCalc7() {
        CommandParser parser = new CommandParser();
        String input = "calc --path test\\resource --format xml --algorithm md5";

        assertThrows(FileNotFoundException.class, () -> parser.execute(input));
    }
}
