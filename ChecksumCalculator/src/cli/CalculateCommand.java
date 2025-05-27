package cli;

import algorithms.CalculatorUtils;
import algorithms.ChecksumCalculator;
import fileTree.BaseFile;
import fileTree.FileTreeBuilder;
import formats.ChecksumFile;
import formats.ChecksumFileUtils;
import progress.ProgressReporter;
import visitors.HashStreamWriter;

import java.io.FileOutputStream;
import java.io.IOException;

public class CalculateCommand implements Command
{
    private final String algorithm;
    private final String path;
    private final String formatStr;
    private final String result;

    public CalculateCommand(String algorithm, String path, String formatStr, String result)
    {
        this.algorithm = algorithm;
        this.path = path;
        this.formatStr = formatStr;
        this.result = result;
    }

    @Override
    public void run()
    {
        try
        {
            ChecksumCalculator calc = CalculatorUtils.getCalc(algorithm);
            BaseFile root = FileTreeBuilder.buildTreeFrom(path);
            ChecksumFile format = ChecksumFileUtils.getFormat(formatStr);
            HashStreamWriter writer = new HashStreamWriter(calc, format);

            ProgressReporter progress = new ProgressReporter();
            writer.addSubscriber(progress);

            String res = writer.calculateChecksums(root);

            if (result.isEmpty())
            {
                System.out.println();
                System.out.println(res);
            }
            else
            {
                FileOutputStream out = new FileOutputStream(result);
                out.write(res.getBytes());
                out.close();

                System.out.println("\nResult saved in " + result);
            }
        } catch (IOException | IllegalArgumentException e)
        {
            throw new RuntimeException(e);
        }
    }

    public String getAlgorithm()
    {
        return algorithm;
    }

    public String getPath()
    {
        return path;
    }

    public String getFormatStr()
    {
        return formatStr;
    }

    public String getResult()
    {
        return result;
    }
}
