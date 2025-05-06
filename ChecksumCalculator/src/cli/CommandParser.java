package cli;

import algorithms.*;
import fileTree.BaseFile;
import fileTree.FileTreeBuilder;
import formats.ChecksumFile;
import formats.ChecksumFileUtils;
import org.apache.commons.cli.*;
import progress.ProgressReporter;
import visitors.HashStreamWriter;
import visitors.ReportWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.text.*;

public class CommandParser
{
    static Option helpOption = Option.builder().option("h").longOpt("help").hasArg(false).required(false)
            .desc("Print help information").build();
    static Option algOption = Option.builder().option("a").longOpt("algorithm").hasArg().required(true)
            .desc("Specify an algorithm to be used for calculating the checksums: md5, sha1, sha256 (required option)").build();
    static Option pathOption = Option.builder().option("p").longOpt("path").hasArg().required(false)
            .desc("Specify the path (absolute or relative) to the file or directory; default value is the current directory").build();
    static Option verifOption = Option.builder().option("ch").longOpt("checksums").hasArg().required(true)
            .desc("Specify the file containing the pre-calculated checksums (required option)").build();
    static Option saveOption = Option.builder().option("r").longOpt("result").hasArg().required(false)
            .desc("Specify a file name where the result will be saved; if missing the result is printed on the standard output").build();
    static Option formatOption = Option.builder().option("f").longOpt("format").hasArg().required(false)
            .desc("Specify the format of the report (xml or simple/simple-text); default value is simple-text").build();
    static Option diffOption = Option.builder().option("d").longOpt("diff").hasArg(false).required(false)
            .desc("Include only new, deleted or modified files in the report").build();


    public void execute(String input) throws IOException, IllegalArgumentException, RuntimeException
    {
        //parsing the input
        StringTokenizer tokenizer = new StringTokenizer(input, ' ', '\"');
        String[] args = tokenizer.getTokenArray();

        Options calcOptions = createCalcOptions();
        Options verifOptions = createVerifOptions();

        HelpFormatter fmt = new HelpFormatter();

        if (checkForHelp(args))
        {
            if (args[0].equalsIgnoreCase("calculate") || args[0].equalsIgnoreCase("calc"))
            {
                fmt.printHelp("calculate --path path --algorithm alg --format format -- result res", calcOptions);
                return;
            }
            else if (args[0].equalsIgnoreCase("verify"))
            {
                fmt.printHelp("verify --path path --algorithm alg --checksums checksum_file --result res", verifOptions);
                return;
            }
            else
            {
                System.out.println("The following commands are supported:");
                fmt.printHelp("calculate --path path --algorithm alg --format format -- result res", calcOptions);
                fmt.printHelp("verify --path path --algorithm alg --checksums checksum_file --result res", verifOptions);
                System.out.println("usage: exit/quit\nexit/quit: exit the app");
                return;
            }
        }

        CommandLineParser parser = new DefaultParser();

        try
        {
            CommandLine cmdLine;

            if (args[0].equalsIgnoreCase("calculate") || args[0].equalsIgnoreCase("calc"))
            {
                cmdLine = parser.parse(calcOptions, args);
            }
            else if (args[0].equalsIgnoreCase("verify"))
            {
                cmdLine = parser.parse(verifOptions, args);
            }
            else
            {
                throw new IllegalArgumentException("Unrecognised command. Try --help or -h for help.");
            }

            String algorithm = cmdLine.getOptionValue(algOption.getOpt()).trim().toLowerCase();

            String path = ".";
            if (cmdLine.hasOption(pathOption.getOpt()))
            {
                path = cmdLine.getOptionValue("path").trim();
            }

            String checksums = "";
            if (cmdLine.hasOption(verifOption.getOpt()))
            {
                checksums = cmdLine.getOptionValue("checksums").trim();
            }

            String result = "";
            if (cmdLine.hasOption(saveOption.getOpt()))
            {
                result = cmdLine.getOptionValue("result").trim();
            }

            String format = "simple";
            if (cmdLine.hasOption(formatOption.getOpt()))
            {
                format = cmdLine.getOptionValue("format").trim().toLowerCase();
            }

            boolean onlyDiff = cmdLine.hasOption(diffOption.getOpt());

            //executing the command
            executeHelper(algorithm, path, format, checksums, result, onlyDiff);

        } catch (ParseException e) {
            throw new RuntimeException(e.getMessage() + "\nTry --help or -h for help.");
        }
    }

    public void executeHelper(String algorithm, String path, String formatStr, String checksums,
                         String resName, boolean onlyDiff) throws IllegalArgumentException, IOException
    {
        ChecksumCalculator calc = CalculatorUtils.getCalc(algorithm);

        BaseFile root = FileTreeBuilder.buildTreeFrom(path);
        String res;

        if (checksums.isEmpty())
        {
            ChecksumFile format = ChecksumFileUtils.getFormat(formatStr);

            HashStreamWriter writer = new HashStreamWriter(calc, format);

            ProgressReporter progress = new ProgressReporter();
            writer.addSubscriber(progress);

            res = writer.calculateChecksums(root);
        }
        else
        {
            File oldChecksums = new File(checksums);

            ReportWriter writer = new ReportWriter(calc);

            ProgressReporter progress = new ProgressReporter();
            writer.addSubscriber(progress);

            res = writer.reportModifications(root, oldChecksums, onlyDiff);
        }

        if (resName.isEmpty())
        {
            System.out.println();
            System.out.println(res);
        }
        else
        {
            FileOutputStream out = new FileOutputStream(resName);
            out.write(res.getBytes());
            out.close();

            System.out.println("\nResult saved in " + resName);
        }
    }

    private Options createCalcOptions()
    {
        Options calcOptions = new Options();
        calcOptions.addOption(algOption).addOption(pathOption).addOption(saveOption)
                    .addOption(formatOption).addOption(helpOption);

        return calcOptions;
    }

    private Options createVerifOptions()
    {
        Options verifOptions = new Options();
        verifOptions.addOption(algOption).addOption(pathOption).addOption(saveOption)
                    .addOption(verifOption).addOption(diffOption).addOption(helpOption);

        return verifOptions;
    }

    private static boolean checkForHelp(String[] args) throws RuntimeException
    {
        boolean hasHelp = false;
        Options options = new Options();
        options.addOption(helpOption);

        try
        {
            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(options, args);

            hasHelp = cmd.hasOption(helpOption.getOpt());
        }
        catch (ParseException e)
        {
            if (Arrays.asList(args).contains("--help") || Arrays.asList(args).contains("-h"))
            {
                throw new RuntimeException("Incorrect syntax of help option: you mustn't provide any other options along with --help.");
            }
        }
        return hasHelp;
    }
}
