package readers;

import java.io.*;
import java.util.HashMap;

public class ChecksumFileReader
{
    public static HashMap<String,String> readChecksumFile(File oldChecksums) throws IOException, IllegalArgumentException
    {
        HashMap<String, String> res;

        try (InputStream in = new FileInputStream(oldChecksums))
        {
            res = SimpleChecksumFileReader.readChecksumFile(in);
            return res;
        } catch (IllegalArgumentException e)
        {
            try (InputStream in = new FileInputStream(oldChecksums))
            {
                res = XMLChecksumFileReader.readChecksumFile(in);
                return res;
            } catch (IllegalArgumentException ex)
            {
                System.out.println(ex.toString());
                throw new IllegalArgumentException("The file is not a checksum file.");
            }
        }
    }
}
