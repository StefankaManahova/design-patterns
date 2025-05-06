package fileTree;

import java.io.File;
import java.io.FileNotFoundException;

public class FileTreeBuilder
{
    public static BaseFile buildTreeFrom(String path) throws FileNotFoundException
    {
        File root = new File(path);
        if (root.exists())
        {
            return buildTreeFrom(root);
        }
        else
        {
            throw new FileNotFoundException("The file " + path + " doesn't exist");
        }
    }

    public static BaseFile buildTreeFrom(File root)
    {
        if (!root.isDirectory())
        {
            boolean binary = FileType.isBinary(root);
            return new SimpleFile(root.getName(), root.getPath(), root.length(), binary);
        } else {
            Folder res = new Folder(root.getName(), root.getPath(), root.length());

            File[] files = root.listFiles();

            if (files != null)
            {
                for (File f : files)
                {
                    BaseFile bf = buildTreeFrom(f);
                    res.add(bf);
                }
            }
            return res;
        }
    }
}
