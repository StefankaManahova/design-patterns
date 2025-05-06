package readers;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

class ChecksumFileReaderTest
{
    @Test
    @DisplayName("Test checksum file reader with a correct XML file")
    public void testCorrectXML()
    {
        File xmlReport = new File("test/utils/xml_report.txt");

        HashMap<String, String> checksums = new HashMap<String, String>()
        {{
            put("C:\\Fani\\Fani\\12 клас\\Графичен дизайн\\Harry Potter.svg", "c75c9c46abc4642e2b86a5405adb27fe");
            put("C:\\Fani\\Fani\\12 клас\\Графичен дизайн\\CV_Stefanka_Manahova.png", "9c7f1859c559b59214e05c230b2332ac");
            put("C:\\Fani\\Fani\\12 клас\\Графичен дизайн\\kmr_project.docx", "3b775f20be8e1afc766a1dca51a8d050");
            put("C:\\Fani\\Fani\\12 клас\\Графичен дизайн\\june.png", "39883b573dec261d55a1e7cc4dd7b06d");
        }};

        try
        {
            HashMap<String, String> res = ChecksumFileReader.readChecksumFile(xmlReport);
            assertEquals(checksums, res);
        } catch (IllegalArgumentException | IOException ex)
        {
            System.out.println(ex.toString());
        }
    }

    @Test
    @DisplayName("Test checksum file reader with a correct file in simple text format")
    public void testCorrectSimple()
    {
        File simpleReport = new File("test/utils/simple_report.txt");

        HashMap<String, String> checksums = new HashMap<String, String>()
        {{
            put("C:\\Fani\\Fani\\12 клас\\Графичен дизайн\\Harry Potter.svg", "c75c9c46abc4642e2b86a5405adb27fe");
            put("C:\\Fani\\Fani\\12 клас\\Графичен дизайн\\CV_Stefanka_Manahova.png", "9c7f1859c559b59214e05c230b2332ac");
            put("C:\\Fani\\Fani\\12 клас\\Графичен дизайн\\kmr_project.docx", "3b775f20be8e1afc766a1dca51a8d050");
            put("C:\\Fani\\Fani\\12 клас\\Графичен дизайн\\june.png", "39883b573dec261d55a1e7cc4dd7b06d");
        }};

        try
        {
            HashMap<String, String> res = ChecksumFileReader.readChecksumFile(simpleReport);
            assertEquals(checksums, res);
        } catch (IllegalArgumentException | IOException ex)
        {
            System.out.println(ex.toString());
        }
    }

    @Test
    @DisplayName("Test checksum file reader with an incorrect file")
    public void testIncorrect()
    {
        File incorrect = new File("test/utils/incorrect_format.txt");

        assertThrows(IllegalArgumentException.class, () -> ChecksumFileReader.readChecksumFile(incorrect));
    }
}