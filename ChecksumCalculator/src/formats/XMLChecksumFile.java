package formats;

import fileTree.SimpleFile;

import java.util.HashMap;

public class XMLChecksumFile implements ChecksumFile
{
    @Override
    public String createReport(HashMap<SimpleFile, String> checksums)
    {
        StringBuilder res = new StringBuilder();
        res.append("<checksums>\n");

        for (SimpleFile f : checksums.keySet())
        {
            res.append("\t<item>\n");
            res.append("\t\t<checksum>").append(checksums.get(f)).append("</checksum>\n");
            res.append("\t\t<path>").append(f.getPath()).append("</path>\n");
            res.append("\t\t<size>").append(f.getSize()).append("</size>\n");
            res.append("\t</item>\n");
        }

        res.append("</checksums>\n");
        return res.toString();
    }
}
