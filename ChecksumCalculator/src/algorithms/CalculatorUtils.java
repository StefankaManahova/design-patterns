package algorithms;

public class CalculatorUtils
{
    public static ChecksumCalculator getCalc(String algorithm) throws IllegalArgumentException
    {
        ChecksumCalculator calc;

        switch(algorithm)
        {
            case "md5" -> calc = new MD5Calculator();
            case "sha1" -> calc = new SHA1Calculator();
            case "sha256" -> calc = new SHA256Calculator();
            default -> throw new IllegalArgumentException("Unknown algorithm.");
        }

        return calc;
    }
}
