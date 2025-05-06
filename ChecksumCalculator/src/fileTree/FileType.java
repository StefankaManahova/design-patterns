package fileTree;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class FileType
{
    public static Set<String> textExtensions = new HashSet<String>(){{
        add(".txt");
        add(".asc");
        add(".doc");
        add(".docx");
        add(".rtf");
        add(".msg");
        add(".pdf");
        add(".wpd");
        add(".wps");
        add(".xml");
        add(".html");
        add(".java");
        add(".h");
        add(".hpp");
        add(".cpp");
    }};

    public static boolean isBinary(File file)
    {
        for (String s : textExtensions)
        {
            if (file.getName().contains(s))
            {
                return false;
            }
        }
        return true;
    }
}
