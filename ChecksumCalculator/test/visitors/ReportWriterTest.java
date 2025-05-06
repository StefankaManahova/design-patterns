package visitors;

import algorithms.MD5Calculator;
import fileTree.BaseFile;
import fileTree.FileTreeBuilder;
import formats.XMLChecksumFile;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ReportWriterTest
{
    @Test
    @DisplayName("Test report writer")
    public void test()
    {
        HashStreamWriter md5_writer = new HashStreamWriter(new MD5Calculator(), new XMLChecksumFile());

        String path = "test\\res_for_modification";
        try
        {
            BaseFile root = FileTreeBuilder.buildTreeFrom(path);
            String report = md5_writer.calculateChecksums(root);

            //save checksums in file old_checksums.txt
            File oldChecksums = new File("test\\utils\\old_checksums.txt");

            FileOutputStream out = new FileOutputStream(oldChecksums);
            out.write(report.getBytes());
            out.close();

            //make changes
            out = new FileOutputStream("test\\res_for_modification\\3.txt");
            String text3 = "Your love is like bad medicine,\nbad medicine is what I need";
            out.write(text3.getBytes());
            out.close();

            out = new FileOutputStream("test\\res_for_modification\\1.txt");
            String text1 = "You take a mortal man\nand put him in control\nWatch him become a god";
            out.write(text1.getBytes());
            out.close();

            out = new FileOutputStream("test\\res_for_modification\\pack\\4.txt", true);
            String text4 = "by Nirvana";
            out.write(text4.getBytes());
            out.close();

            out = new FileOutputStream("test\\res_for_modification\\pack\\5.txt", true);
            String text5 = "Give me fuel, give me fire\nGive that which I desire\n";
            out.write(text5.getBytes());
            out.close();

            //report modifications
            root = FileTreeBuilder.buildTreeFrom(path);
            ReportWriter reportWriter = new ReportWriter(new MD5Calculator());
            String modifications = reportWriter.reportModifications(root, oldChecksums, false);

            //we don't know the exact order in which the files are visited,
            //so we sort the lines in the report
            String[] lines = modifications.trim().split("\\r?\\n");
            Arrays.sort(lines);

            StringBuilder sorted = new StringBuilder();
            for (int i = 0; i < lines.length; i++)
            {
                sorted.append(lines[i]).append("\n");
            }

            //restore previous state
            out = new FileOutputStream("test\\res_for_modification\\1.txt");
            String oldText1 = "Hello me\nMeet the real me\n";
            out.write(oldText1.getBytes());
            out.close();

            out = new FileOutputStream("test\\res_for_modification\\pack\\4.txt");
            String oldText4 = "Come as you are, as you were\nAs I want you to be\nAs a friend, as a friend\nAs an old enemy\n";
            out.write(oldText4.getBytes());
            out.close();

            File toDelete = new File("test\\res_for_modification\\3.txt");
            if(!toDelete.delete())
            {
                System.out.println("Failed to delete 3.txt");
            }

            File toDelete2 = new File("test\\res_for_modification\\pack\\5.txt");
            if(!toDelete2.delete())
            {
                System.out.println("Failed to delete 5.txt");
            }

            String expected = """
                    test\\res_for_modification\\1.txt: MODIFIED
                    test\\res_for_modification\\2.txt: OK
                    test\\res_for_modification\\3.txt: NEW
                    test\\res_for_modification\\pack\\4.txt: MODIFIED
                    test\\res_for_modification\\pack\\5.txt: NEW
                    """;

            assertEquals(expected, sorted.toString());

        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}