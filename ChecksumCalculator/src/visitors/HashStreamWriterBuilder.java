package visitors;

import algorithms.ChecksumCalculator;
import formats.ChecksumFile;
import formats.SimpleChecksumFile;

public class HashStreamWriterBuilder
{
    private ChecksumCalculator calc;
    private ChecksumFile checksumFile = new SimpleChecksumFile();

    public HashStreamWriterBuilder setCalculator(ChecksumCalculator calc)
    {
        this.calc = calc;
        return this;
    }

    public HashStreamWriterBuilder setChecksumFile(ChecksumFile checksumFile)
    {
        this.checksumFile = checksumFile;
        return this;
    }

    public HashStreamWriter buildHashStreamWriter()
    {
        return new HashStreamWriter(this.calc, this.checksumFile);
    }
}
