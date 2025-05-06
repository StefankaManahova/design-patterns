package readers;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;


class SimpleChecksumFileReaderTest
{
    @Test
    @DisplayName("Test simple checksum file reader with a correct file")
    public void testCorrect()
    {
        String data = """
                bf1cde9c94c301cdc3b5486f2f3fe66b */bin/zip
                72dcfc86d29028446e52f33a8937cd69 */bin/zipcloak
                b820b8cb1e28403decc1734fc19a26d0 */bin/zipdetails
                ddf51a6f1d31b015b654033bd027c94a */bin/zipgrep
                7ac7b307c04fcd5ee4a4bb6f3c0b70b5 */bin/zipinfo""";

        InputStream in = new ByteArrayInputStream(data.getBytes());

        HashMap<String, String> checksums = new HashMap<String, String>()
        {{
            put("bin/zip", "bf1cde9c94c301cdc3b5486f2f3fe66b");
            put("bin/zipcloak", "72dcfc86d29028446e52f33a8937cd69");
            put("bin/zipdetails", "b820b8cb1e28403decc1734fc19a26d0");
            put("bin/zipgrep", "ddf51a6f1d31b015b654033bd027c94a");
            put("bin/zipinfo", "7ac7b307c04fcd5ee4a4bb6f3c0b70b5");
        }};

        try
        {
            HashMap<String, String> res = SimpleChecksumFileReader.readChecksumFile(in);
            assertEquals(checksums, res);
        } catch (IllegalArgumentException ex)
        {
            System.out.println(ex.toString());
        }
    }

    @Test
    @DisplayName("Test simple checksum file reader with an incorrect file")
    public void testIncorrect()
    {
        String data = """
                bf1cde9c94c301cdc3b5486f2f3fe66b */bin/zip
                72dcfc86d29028446e52f33a8937cd69 */bin/zipcloak
                alabalatralala
                ddf51a6f1d31b015b654033bd027c94a */bin/zipgrep
                7ac7b307c04fcd5ee4a4bb6f3c0b70b5 */bin/zipinfo""";

        InputStream in = new ByteArrayInputStream(data.getBytes());

        assertThrows(IllegalArgumentException.class, () -> SimpleChecksumFileReader.readChecksumFile(in));
    }
}