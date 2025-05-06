package readers;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;

public class SimpleChecksumFileReader
{
    public static HashMap<String, String> readChecksumFile(InputStream inputStream) throws IllegalArgumentException
    {
        Scanner in = new Scanner(inputStream);
        HashMap<String, String> res = new HashMap<>();

        String checksum, fileName;
        String[] line;

        while (in.hasNext())
        {
            line = in.nextLine().trim().split("\\s+", 2);
            if (line.length < 2) {
                in.close();
                throw new IllegalArgumentException("The file is not a checksum file in simple text format.");
            }
            checksum = line[0];

            if (line[1].charAt(0) == '*') //binary file
            {
                fileName = line[1].substring(2);
            }
            else //text file
            {
                fileName = line[1].substring(1);
            }

            res.put(fileName, checksum);
        }

        in.close();
        return res;
    }
}
