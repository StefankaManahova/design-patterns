package visitors;

import algorithms.ChecksumCalculator;
import progress.Observable;
import readers.ChecksumFileReader;
import fileTree.BaseFile;
import fileTree.Folder;
import fileTree.SimpleFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class ReportWriter extends Observable implements Visitor
{
    private HashMap<String, String> old;
    private ChecksumCalculator calc;

    private HashMap<String, States> modified;

    public ReportWriter(ChecksumCalculator calc)
    {
        super();
        this.calc = calc;
        modified = new HashMap<>();
    }
    @Override
    public String visitFile(SimpleFile file)
    {
        try {
            String name = file.getPath();
            InputStream is = new FileInputStream(name);
            String checksum = calc.calculate(is);
            is.close();

            if (old.containsKey(name))
            {
                String oldChecksum = old.get(name);

                if (oldChecksum.equals(checksum))
                {
                    modified.put(name, States.OK);
                }
                else
                {
                    modified.put(name, States.MODIFIED);
                }

                old.remove(name);
            }
            else
            {
                modified.put(name, States.NEW);
            }

            notify(2, file);

        } catch (IOException ex)
        {
            System.out.println("The file \"" + file.getPath() + "\" couldn't be opened");
        }

        return null;
    }

    @Override
    public String visitFolder(Folder folder)
    {
        for (BaseFile bf : folder.getFiles())
        {
            bf.accept(this);
        }

        return null;
    }

    public String reportModifications(BaseFile root, File oldChecksums, boolean onlyDiff) throws IOException, IllegalArgumentException
    {
        //for event types: 1 -> set root folder size; 2 -> new file visited
        notify(1, root.getSize());

        old = ChecksumFileReader.readChecksumFile(oldChecksums);
        root.accept(this);
        markRemoved();
        return makeReport(onlyDiff);
    }

    private void markRemoved()
    {
        for (String name : old.keySet())
        {
            modified.put(name, States.REMOVED);
        }
    }

    private String makeReport(boolean onlyDiff)
    {
        StringBuilder res = new StringBuilder();

        //if onlyDiff is set, we don't include OK files in the report
        if (onlyDiff)
        {
            for (String name : modified.keySet())
            {
                if (modified.get(name) != States.OK)
                {
                    res.append(name).append(": ").append(modified.get(name).toString()).append("\n");
                }
            }

            if (res.isEmpty())
            {
                res.append("No modifications");
            }
        }
        //else we include all files in the report
        else
        {
            for (String name : modified.keySet())
            {
                res.append(name).append(": ").append(modified.get(name).toString()).append("\n");
            }
        }

        return res.toString();
    }
}
