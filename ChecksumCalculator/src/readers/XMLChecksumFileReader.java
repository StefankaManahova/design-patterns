package readers;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;

public class XMLChecksumFileReader
{
    public static HashMap<String, String> readChecksumFile(InputStream inputStream)
    {
        Scanner in = new Scanner(inputStream);
        HashMap<String, String> res = new HashMap<>();

        String msgError = "The file is not a checksum file in XML text format.";

        String line = in.nextLine().trim();

        if (!line.equals("<checksums>"))
        {
            in.close();
            throw new IllegalArgumentException(msgError);
        }

        while (in.hasNextLine())
        {
            line = in.nextLine().trim();

            if (line.equals("<item>"))
            {
                line = in.nextLine().trim();

                String begin = line.substring(0, 10);
                String end = line.substring(line.length() - 11);
                String checksum = line.substring(10, line.length() - 11);

                if (!begin.equals("<checksum>") || !end.equals("</checksum>"))
                {
                    in.close();
                    throw new IllegalArgumentException(msgError);
                }

                line = in.nextLine().trim();
                begin = line.substring(0,6);
                end = line.substring(line.length() - 7);
                String name = line.substring(6, line.length() - 7);

                if (!begin.equals("<path>") || !end.equals("</path>"))
                {
                    in.close();
                    throw new IllegalArgumentException(msgError);
                }

                line = in.nextLine().trim();
                begin = line.substring(0,6);
                end = line.substring(line.length() - 7);
                if (!begin.equals("<size>") || !end.equals("</size>"))
                {
                    in.close();
                    throw new IllegalArgumentException(msgError);
                }

                line = in.nextLine().trim();
                if (!line.equals("</item>"))
                {
                    in.close();
                    throw new IllegalArgumentException(msgError);
                }

                res.put(name, checksum);

            } else if (line.equals("</checksums>"))
            {
                if (in.hasNext() && !in.nextLine().isEmpty())
                {
                    in.close();
                    throw new IllegalArgumentException(msgError);
                }
                break;
            } else
            {
                in.close();
                throw new IllegalArgumentException(msgError);
            }
        }

        return res;
    }
}
