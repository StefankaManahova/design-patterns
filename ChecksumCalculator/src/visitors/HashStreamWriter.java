package visitors;

import algorithms.ChecksumCalculator;
import fileTree.BaseFile;
import fileTree.Folder;
import fileTree.SimpleFile;
import formats.ChecksumFile;
import progress.Observable;
import progress.Observer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class HashStreamWriter extends Observable implements Visitor
{
    private ChecksumCalculator calc;
    private ChecksumFile checksumFile;

    private HashMap<SimpleFile, String> checksums;

    public HashStreamWriter(ChecksumCalculator calc, ChecksumFile checksumFile)
    {
        super();
        this.calc = calc;
        this.checksumFile = checksumFile;
        checksums = new HashMap<>();
    }

    public String calculateChecksums(BaseFile root)
    {
        //for event types: 1 -> set root folder size; 2 -> new file visited
        notify(1, root.getSize());

        root.accept(this);
        return checksumFile.createReport(checksums);
    }

    @Override
    public String visitFile(SimpleFile file)
    {
        try {
            InputStream is = new FileInputStream(file.getPath());
            String checksum = calc.calculate(is);
            checksums.put(file, checksum);
            is.close();

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

    public void setChecksumFile(ChecksumFile checksumFile)
    {
        this.checksumFile = checksumFile;
    }

    public void setCalc(ChecksumCalculator calc)
    {
        this.calc = calc;
    }

}
