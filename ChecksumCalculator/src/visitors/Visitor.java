package visitors;

import fileTree.Folder;
import fileTree.SimpleFile;

public interface Visitor
{
    public String visitFile(SimpleFile file);
    public String visitFolder(Folder folder);
}
