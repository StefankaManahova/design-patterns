package fileTree;

import visitors.Visitor;

public interface BaseFile
{
    public void print(int indent);

    public String getName();
    public String getPath();
    public long getSize();

    public String accept(Visitor visitor);
}
