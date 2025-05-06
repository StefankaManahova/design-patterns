package algorithms;

import progress.Observer;

import java.io.IOException;
import java.io.InputStream;

public interface ChecksumCalculator
{
    public abstract String calculate(InputStream is) throws IOException;
}
