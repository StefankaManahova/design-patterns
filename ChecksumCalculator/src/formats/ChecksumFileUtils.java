package formats;

public class ChecksumFileUtils
{
    public static ChecksumFile getFormat(String format) throws IllegalArgumentException
    {
        if (format.equals("simple") || format.equals("simple-text"))
        {
            return new SimpleChecksumFile();
        }
        else if (format.equals("xml"))
        {
            return new XMLChecksumFile();
        }
        else
        {
            throw new IllegalArgumentException("Incorrect format of report");
        }
    }
}
