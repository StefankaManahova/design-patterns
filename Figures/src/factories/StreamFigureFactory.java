package factories;
/* factory that reads figures from a stream - from
stdin, from a file or from some other source */

import figures.Figure;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamFigureFactory implements FigureFactory
{
    private BufferedReader in;

    public StreamFigureFactory(InputStream inStream)
    {
        if (inStream == null)
        {
            throw new IllegalArgumentException("The stream can't be NULL!\n");
        }

        this.in = new BufferedReader(new InputStreamReader(inStream));
    }

    @Override
    public Figure create() throws Exception
    {
        String str = in.readLine(); //may throw IOException

        return StringToFigure.createFrom(str); //may throw IllegalArgumentException
    }
}
