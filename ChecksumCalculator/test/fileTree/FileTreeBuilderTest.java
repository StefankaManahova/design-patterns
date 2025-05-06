package fileTree;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import visitors.PrintVisitor;

import java.io.IOException;


import static org.junit.jupiter.api.Assertions.*;

class FileTreeBuilderTest
{
    @Test
    @DisplayName("Test the building of a file tree with folder \"utils\"")
    public void test1()
    {
        String path = "test\\utils";
        try
        {
            BaseFile root = FileTreeBuilder.buildTreeFrom(path);
            PrintVisitor visitor = new PrintVisitor();

            String res = root.accept(visitor);

            String expected = """
                    utils, size: 6 043 bytes
                    \tincorrect_format.txt, size: 205 bytes
                    \told_checksums.txt, size: 444 bytes
                    \tsimple_report.txt, size: 432 bytes
                    \txml_report.txt, size: 866 bytes
                    """;

            assertEquals(expected, res);

        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    @Test
    @DisplayName("Test the building of a file tree with folder \"resources\"")
    public void test2()
    {
        String path = "test\\resources";
        try
        {
            BaseFile root = FileTreeBuilder.buildTreeFrom(path);
            PrintVisitor visitor = new PrintVisitor();

            String res = root.accept(visitor);

            String expected = """
                    resources, size: 9 896 bytes
                    \ta.txt, size: 12 bytes
                    \tb.txt, size: 10 bytes
                    \tc.txt, size: 1 745 bytes
                    \tfolder, size: 4 033 bytes
                    \t\td.txt, size: 1 556 bytes
                    \t\te.txt, size: 2 477 bytes
                    """;

            assertEquals(expected, res);

        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    @Test
    @DisplayName("Test the building of a file tree with folder \"res_for_modification\"")
    public void test3()
    {
        String path = "test\\res_for_modification";
        try
        {
            BaseFile root = FileTreeBuilder.buildTreeFrom(path);
            PrintVisitor visitor = new PrintVisitor();

            String res = root.accept(visitor);

            String expected = """
                    res_for_modification, size: 165 bytes
                    \t1.txt, size: 26 bytes
                    \t2.txt, size: 49 bytes
                    \tpack, size: 90 bytes
                    \t\t4.txt, size: 90 bytes
                    """;

            assertEquals(expected, res);

        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
}