package formats;

import fileTree.SimpleFile;

import java.util.HashMap;

public interface ChecksumFile
{
    public String createReport(HashMap<SimpleFile, String> checksums);
}
