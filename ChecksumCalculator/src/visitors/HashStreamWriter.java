package visitors;

import algorithms.ChecksumCalculator;
import fileTree.BaseFile;
import fileTree.Folder;
import fileTree.SimpleFile;
import formats.ChecksumFile;
import progress.Observable;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Stack;

public class HashStreamWriter extends Observable implements Visitor
{
    private ChecksumCalculator calc;
    private ChecksumFile checksumFile;

    private HashMap<SimpleFile, String> checksums;
    private Stack<BaseFile> stack;

    public HashStreamWriter(ChecksumCalculator calc, ChecksumFile checksumFile)
    {
        super();
        this.calc = calc;
        this.checksumFile = checksumFile;
        checksums = new HashMap<>();
        stack = new Stack<>();
    }

    public String calculateChecksums(BaseFile root)
    {
        //for event types: 1 -> set root folder size; 2 -> new file visited
        notify(1, root.getSize());

        stack.push(root);
        root.accept(this);
        return checksumFile.createReport(checksums);
    }

    @Override
    public String visitFile(SimpleFile file)
    {
        stack.pop();
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
        stack.pop();

        for (BaseFile bf : folder.getFiles()) {
            stack.push(bf);
        }
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

    //nested snapshot class for memento pattern
    public class Snapshot
    {
        private final ChecksumCalculator calc;
        private final ChecksumFile checksumFile;

        private final HashMap<SimpleFile, String> checksums;
        private final Stack<BaseFile> stack;

        public Snapshot(ChecksumCalculator calc, ChecksumFile checksumFile,
                        HashMap<SimpleFile, String> checksums, Stack<BaseFile> stack)
        {
            this.calc = calc;
            this.checksumFile = checksumFile;
            this.checksums = checksums;
            this.stack = stack;
        }
    }

    public Snapshot createSnapShot()
    {
        return new Snapshot(calc, checksumFile, checksums, stack);
    }

    public void restore(Snapshot snapshot)
    {
        this.calc = snapshot.calc;
        this.checksumFile = snapshot.checksumFile;
        this.checksums = snapshot.checksums;
        this.stack = snapshot.stack;

        stack.peek().accept(this);
    }
}
