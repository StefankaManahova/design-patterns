package readers;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;

class XMLChecksumFileReaderTest
{
    @Test
    @DisplayName("Test XML checksum file reader with a correct file")
    public void testCorrect()
    {
        String data = """
                <checksums>
                    <item>
                        <checksum>c75c9c46abc4642e2b86a5405adb27fe</checksum>
                        <path>C:\\Fani\\Fani\\12 клас\\Графичен дизайн\\Harry Potter.svg</path>
                        <size>20026</size>
                    </item>
                    <item>
                        <checksum>9c7f1859c559b59214e05c230b2332ac</checksum>
                        <path>C:\\Fani\\Fani\\12 клас\\Графичен дизайн\\CV_Stefanka_Manahova.png</path>
                        <size>192035</size>
                    </item>
                    <item>
                        <checksum>3b775f20be8e1afc766a1dca51a8d050</checksum>
                        <path>C:\\Fani\\Fani\\12 клас\\Графичен дизайн\\kmr_project.docx</path>
                        <size>13352</size>
                    </item>
                    <item>
                        <checksum>39883b573dec261d55a1e7cc4dd7b06d</checksum>
                        <path>C:\\Fani\\Fani\\12 клас\\Графичен дизайн\\june.png</path>
                        <size>36901</size>
                    </item>
                </checksums>""";

        InputStream in = new ByteArrayInputStream(data.getBytes());

        HashMap<String, String> checksums = new HashMap<String, String>()
        {{
            put("C:\\Fani\\Fani\\12 клас\\Графичен дизайн\\Harry Potter.svg", "c75c9c46abc4642e2b86a5405adb27fe");
            put("C:\\Fani\\Fani\\12 клас\\Графичен дизайн\\CV_Stefanka_Manahova.png", "9c7f1859c559b59214e05c230b2332ac");
            put("C:\\Fani\\Fani\\12 клас\\Графичен дизайн\\kmr_project.docx", "3b775f20be8e1afc766a1dca51a8d050");
            put("C:\\Fani\\Fani\\12 клас\\Графичен дизайн\\june.png", "39883b573dec261d55a1e7cc4dd7b06d");
        }};

        try
        {
            HashMap<String, String> res = XMLChecksumFileReader.readChecksumFile(in);
            assertEquals(checksums, res);
        } catch (IllegalArgumentException ex)
        {
            System.out.println(ex.toString());
        }
    }

    @Test
    @DisplayName("Test XML checksum file reader with an incorrect file 1")
    public void testIncorrect()
    {
        String data = """
                     <item>
                         <checksum>c75c9c46abc4642e2b86a5405adb27fe</checksum>
                         <path>C:\\Fani\\Fani\\12 клас\\Графичен дизайн\\Harry Potter.svg</path>
                         <size>20026</size>
                     </item>
                     """;

        InputStream in = new ByteArrayInputStream(data.getBytes());

        assertThrows(IllegalArgumentException.class, () -> SimpleChecksumFileReader.readChecksumFile(in));

    }

    @Test
    @DisplayName("Test simple checksum file reader with an incorrect file 2")
    public void testIncorrect2()
    {
        String data = """
                <checksums>
                    <item>
                        <checksum>c75c9c46abc4642e2b86a5405adb27fe</checksum>
                        <path>C:\\Fani\\Fani\\12 клас\\Графичен дизайн\\Harry Potter.svg</path>
                    </item>
                    <item>
                        <checksum>9c7f1859c559b59214e05c230b2332ac</checksum>
                        <path>C:\\Fani\\Fani\\12 клас\\Графичен дизайн\\CV_Stefanka_Manahova.png</path>
                        <size>192035</size>
                    </item>
                </checksums>""";

        InputStream in = new ByteArrayInputStream(data.getBytes());

        assertThrows(IllegalArgumentException.class, () -> SimpleChecksumFileReader.readChecksumFile(in));
    }

    @Test
    @DisplayName("Test XML checksum file reader with an incorrect file 3")
    public void testIncorrect3()
    {
        String data = """
                <checksums>
                    <item>
                        <checksum>c75c9c46abc4642e2b86a5405adb27fe</checksum>
                        <path>C:\\Fani\\Fani\\12 клас\\Графичен дизайн\\Harry Potter.svg</path>
                        <size>20026</size>
                    <item>
                        <checksum>9c7f1859c559b59214e05c230b2332ac</checksum>
                        <path>C:\\Fani\\Fani\\12 клас\\Графичен дизайн\\CV_Stefanka_Manahova.png</path>
                        <size>192035</size>
                </checksums>""";

        InputStream in = new ByteArrayInputStream(data.getBytes());

        assertThrows(IllegalArgumentException.class, () -> SimpleChecksumFileReader.readChecksumFile(in));
    }

    @Test
    @DisplayName("Test XML checksum file reader with an incorrect file 4")
    public void testIncorrect4()
    {
        String data = """
                <checksums>
                    <item>
                        <sum>c75c9c46abc4642e2b86a5405adb27fe</sum>
                        <path>C:\\Fani\\Fani\\12 клас\\Графичен дизайн\\Harry Potter.svg</path>
                        <size>20026</size>
                    </item>
                </checksums>""";

        InputStream in = new ByteArrayInputStream(data.getBytes());

        assertThrows(IllegalArgumentException.class, () -> SimpleChecksumFileReader.readChecksumFile(in));
    }

    @Test
    @DisplayName("Test XML checksum file reader with an incorrect file 5")
    public void testIncorrect5()
    {
        String data = """
                <checksums>
                    <item>
                        <checksum>c75c9c46abc4642e2b86a5405adb27fe</checksum>
                        <size>20026</size>
                    </item>
                </checksums>""";

        InputStream in = new ByteArrayInputStream(data.getBytes());

        assertThrows(IllegalArgumentException.class, () -> SimpleChecksumFileReader.readChecksumFile(in));
    }

    @Test
    @DisplayName("Test XML checksum file reader with an incorrect file 6")
    public void testIncorrect6()
    {
        String data = """
                <checksums>
                    <item>
                        <checksum>c75c9c46abc4642e2b86a5405adb27fe</checksum>
                        <path>C:\\Fani\\Fani\\12 клас\\Графичен дизайн\\Harry Potter.svg</path>
                        <size>20026</size>
                    <item>
                        <checksum>9c7f1859c559b59214e05c230b2332ac</checksum>
                        <path>C:\\Fani\\Fani\\12 клас\\Графичен дизайн\\CV_Stefanka_Manahova.png</path>
                        <size>192035</size>""";

        InputStream in = new ByteArrayInputStream(data.getBytes());

        assertThrows(IllegalArgumentException.class, () -> SimpleChecksumFileReader.readChecksumFile(in));
    }
}