package formats;

import fileTree.SimpleFile;

import java.util.HashMap;

public class SimpleChecksumFile implements ChecksumFile
{
    @Override
    public String createReport(HashMap<SimpleFile, String> checksums)
    {
        StringBuilder res = new StringBuilder();
        for (SimpleFile f : checksums.keySet())
        {
           res.append(checksums.get(f));
           if (f.binary())
           {
               res.append(" *\\");
           }
           else
           {
               res.append(" \\");
           }

            res.append(f.getPath()).append("\n");
        }
        return res.toString();
    }
}
