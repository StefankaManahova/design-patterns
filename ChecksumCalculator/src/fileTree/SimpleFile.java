package fileTree;

import visitors.Visitor;

public class SimpleFile implements BaseFile
{
    private String name;
    private String path;
    private long size;
    private boolean binary;

    public SimpleFile(String name, String path, long size, boolean binary)
    {
        this.name = name;
        this.path = path;
        this.size = size;
        this.binary = binary;
    }

    public String getName()
    {
        return name;
    }

    public String getPath()
    {
        return path;
    }

    public boolean binary()
    {
        return binary;
    }

    public long getSize()
    {
        return size;
    }

    public String accept(Visitor visitor)
    {
        return visitor.visitFile(this);
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
    }
}
