package cli;

import algorithms.CalculatorUtils;
import algorithms.ChecksumCalculator;
import fileTree.BaseFile;
import fileTree.FileTreeBuilder;
import progress.ProgressReporter;
import visitors.ReportWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class VerifyCommand implements Command
{
    private final String algorithm;
    private final String path;
    private final String checksums;
    private final String result;
    private final boolean onlyDiff;

    public VerifyCommand(String algorithm, String path, String checksums,
                         String result, boolean onlyDiff)
    {
        this.algorithm = algorithm;
        this.path = path;
        this.checksums = checksums;
        this.result = result;
        this.onlyDiff = onlyDiff;
    }

    @Override
    public void run()
    {
        try
        {
            ChecksumCalculator calc = CalculatorUtils.getCalc(algorithm);
            BaseFile root = FileTreeBuilder.buildTreeFrom(path);
            File oldChecksums = new File(checksums);
            ReportWriter writer = new ReportWriter(calc);

            ProgressReporter progress = new ProgressReporter();
            writer.addSubscriber(progress);

            String res = writer.reportModifications(root, oldChecksums, onlyDiff);

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

    public String getChecksums()
    {
        return checksums;
    }

    public String getResult()
    {
        return result;
    }

    public boolean isOnlyDiff()
    {
        return onlyDiff;
    }
}
