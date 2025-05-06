package visitors;

import fileTree.BaseFile;
import fileTree.Folder;
import fileTree.SimpleFile;

public class PrintVisitor implements Visitor
{
    @Override
    public String visitFile(SimpleFile f)
    {
        StringBuilder builder = new StringBuilder();
        String sizeToString = String.format("%,d", f.getSize()).replace(",", " ");
        builder.append(f.getName()).append(", size: ").append(sizeToString).append(" bytes\n");
        return builder.toString();
    }

    @Override
    public String visitFolder(Folder f)
    {
        StringBuilder builder = new StringBuilder();
        String sizeToString = String.format("%,d", f.getSize()).replace(",", " ");
        builder.append(f.getName()).append(", size: ").append(sizeToString).append(" bytes\n");

        for (BaseFile bf : f.getFiles())
        {
            String str = bf.accept(this);

            str = "\t" + str.replace("\n", "\n\t");
            str = str.substring(0, str.length() - 1);

            builder.append(str);
        }
        return builder.toString();
    }
}
