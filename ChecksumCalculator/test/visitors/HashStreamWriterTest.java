package visitors;

import static org.junit.jupiter.api.Assertions.*;

import algorithms.SHA1Calculator;
import fileTree.BaseFile;
import fileTree.FileTreeBuilder;
import formats.SimpleChecksumFile;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.Arrays;

class HashStreamWriterTest
{
    @Test
    @DisplayName("Test hash stream writer")
    public void testHashStreamWriter()
    {
        String path = "test\\resources";
        try
        {
            BaseFile root = FileTreeBuilder.buildTreeFrom(path);

            HashStreamWriterBuilder builder = new HashStreamWriterBuilder();
            builder.setCalculator(new SHA1Calculator()).setChecksumFile(new SimpleChecksumFile());
            HashStreamWriter writer = builder.buildHashStreamWriter();

            String res = writer.calculateChecksums(root);
            String[] lines = res.trim().split("\\r?\\n");
            Arrays.sort(lines);

            StringBuilder sorted = new StringBuilder();
            for (int i = 0; i < lines.length; i++)
            {
                sorted.append(lines[i]).append("\n");
            }

            String exp = """
                    457d08066551a4ee81a3d1ac0fe51071de14e989 \\test\\resources\\folder\\d.txt
                    51252a3214b9198077302e5ec912bdb47c098c4a \\test\\resources\\folder\\e.txt
                    8efdb1e9c430d7fc80c247b770ad6e4974afc258 \\test\\resources\\c.txt
                    a5b81f9cf47b4f7b244c110c37305dab85944c2b \\test\\resources\\b.txt
                    d3486ae9136e7856bc42212385ea797094475802 \\test\\resources\\a.txt
                    """;

            assertEquals(exp, sorted.toString());

        } catch(FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
    }
}