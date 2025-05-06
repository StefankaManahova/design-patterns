package fileTree;

import visitors.Visitor;

import java.util.ArrayList;
import java.util.List;

public class Folder implements BaseFile
{
    private String name;
    private String path;
    private long size;

    private List<BaseFile> files;

    Folder(String name, String path, long size)
    {
        this.name = name;
        this.path = path;
        this.size = size;
        files = new ArrayList<BaseFile>();
    }

    public String getName()
    {
        return name;
    }

    public String getPath()
    {
        return path;
    }

    public long getSize()
    {
        return size;
    }

    public List<BaseFile> getFiles()
    {
        return files;
    }

    public void add(BaseFile baseFile)
    {
        files.add(baseFile);
        size += baseFile.getSize();
    }

    public String accept(Visitor visitor)
    {
        return visitor.visitFolder(this);
    }

    public void print(int indent)
    {
        for (int i = 0; i < indent; i++)
        {
            System.out.print("\t");
        }

        String sizeToString = String.format("%,d", size).replace(",", " ");
        System.out.printf(name + ", size: " + sizeToString + " bytes");
        System.out.println();

        for (BaseFile f : files)
        {
            f.print(indent + 1);
        }
    }
}
