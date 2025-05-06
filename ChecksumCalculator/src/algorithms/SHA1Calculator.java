package algorithms;

import java.io.IOException;
import java.io.InputStream;

public class SHA1Calculator implements ChecksumCalculator
{
    public String calculate(InputStream is) throws IOException
    {
        return org.apache.commons.codec.digest.DigestUtils.sha1Hex(is);
    }
}
