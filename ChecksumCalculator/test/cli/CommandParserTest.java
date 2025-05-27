package cli;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CommandParserTest
{
    @Test
    @DisplayName("Test parser in first mode - checksum calculation")
    public void testCorrectInputCalc() {
        String input = "calc --path test\\resources --algorithm   sha1 --result test\\utils_2\\resources_report.txt";
        CalculateCommand command = (CalculateCommand) CommandParser.parse(input);
        assert (command != null);

        assertAll(
                () -> assertEquals("sha1", command.getAlgorithm()),
                () -> assertEquals("test\\resources", command.getPath()),
                () -> assertEquals("simple", command.getFormatStr()),
                () -> assertEquals("test\\utils_2\\resources_report.txt", command.getResult())
        );
    }

    @Test
    @DisplayName("Default options (calculate)")
    public void testDefaultCalc() {
        String input = "calc --algorithm sha1 --result test\\utils_2\\current_dir_report.txt";

        assertDoesNotThrow(() -> CommandParser.parse(input));

        CalculateCommand command = (CalculateCommand) CommandParser.parse(input);
        assert(command != null);
        assertAll(
                () -> assertEquals("sha1", command.getAlgorithm()),
                () -> assertEquals(".", command.getPath()),
                () ->assertEquals("simple", command.getFormatStr()),
                () ->assertEquals("test\\utils_2\\current_dir_report.txt", command.getResult())
        );
    }

    @Test
    @DisplayName("Missing path argument")
    public void testIncorrectInputCalc1() {
        String input = "calc --path  --algorithm sha1 --result test\\utils_2\\resources_report.txt";

        assertThrows(RuntimeException.class, () -> CommandParser.parse(input));
    }

    @Test
    @DisplayName("Missing required option")
    public void testIncorrectInputCalc2() {
        String input = "calc --path test\\resources --format xml";

        assertThrows(RuntimeException.class, () -> CommandParser.parse(input));
    }


    @Test
    @DisplayName("Missing algorithm argument")
    public void testIncorrectInputCalc3() {
        String input = "calc --path test\\resources --format xml --algorithm";

        assertThrows(RuntimeException.class, () -> CommandParser.parse(input));
    }

    @Test
    @DisplayName("Unknown option")
    public void testIncorrectInputCalc4() {
        String input = "calc --path test\\resources --format xml --algorithm md5 --size 14";

        assertThrows(RuntimeException.class, () -> CommandParser.parse(input));
    }

    @Test
    @DisplayName("Test correct input")
    public void testCorrectInputVer1()
    {
        String input = "verify --path test\\res_for_modification --algorithm md5 --checksums test\\utils_2\\old_checksums.txt --result test\\utils_2\\new_checksums.txt";
        VerifyCommand command = (VerifyCommand) CommandParser.parse(input);
        assert (command != null);
        assertAll(
                () -> assertEquals("md5", command.getAlgorithm()),
                () -> assertEquals("test\\res_for_modification", command.getPath()),
                () -> assertEquals("test\\utils_2\\old_checksums.txt", command.getChecksums()),
                () -> assertEquals("test\\utils_2\\new_checksums.txt", command.getResult()),
                () -> assertFalse(command.isOnlyDiff())
        );
    }

    @Test
    @DisplayName("Test correct input with --diff")
    public void testCorrectInputVer2()
    {
        String input = "verify --path test\\res_for_modification --algorithm md5 --checksums test\\utils_2\\old_checksums.txt --result test\\utils_2\\new_checksums.txt --diff";
        VerifyCommand command = (VerifyCommand) CommandParser.parse(input);
        assert (command != null);
        assertAll(
                () -> assertEquals("md5", command.getAlgorithm()),
                () -> assertEquals("test\\res_for_modification", command.getPath()),
                () -> assertEquals("test\\utils_2\\old_checksums.txt", command.getChecksums()),
                () -> assertEquals("test\\utils_2\\new_checksums.txt", command.getResult()),
                () -> assertTrue(command.isOnlyDiff())
        );
    }
}
