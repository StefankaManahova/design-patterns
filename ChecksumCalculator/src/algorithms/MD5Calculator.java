package algorithms;

import java.io.IOException;
import java.io.InputStream;

public class MD5Calculator implements ChecksumCalculator
{
    public String calculate(InputStream is) throws IOException
    {
        return org.apache.commons.codec.digest.DigestUtils.md5Hex(is);
    }
}
