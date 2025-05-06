package formats;

import fileTree.SimpleFile;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;

class XMLChecksumFileTest
{
    @Test
    @DisplayName("Test XML checksum file")
    public void test()
    {
        HashMap<SimpleFile, String> checksums = new LinkedHashMap<SimpleFile, String>()
        {{
            put(
                    new SimpleFile("Harry Potter.svg","C:\\Fani\\Fani\\12 клас\\Графичен дизайн\\Harry Potter.svg", 20026, true),
                    "c75c9c46abc4642e2b86a5405adb27fe"
            );
            put(
                    new SimpleFile("CV_Stefanka_Manahova.png","C:\\Fani\\Fani\\12 клас\\Графичен дизайн\\CV_Stefanka_Manahova.png", 192035, true),
                    "9c7f1859c559b59214e05c230b2332ac"
            );
            put(
                    new SimpleFile("kmr_project.docx", "C:\\Fani\\Fani\\12 клас\\Графичен дизайн\\kmr_project.docx", 13352, true),
                    "3b775f20be8e1afc766a1dca51a8d050"
            );
            put(
                    new SimpleFile("june.png", "C:\\Fani\\Fani\\12 клас\\Графичен дизайн\\june.png", 36901, true),
                    "39883b573dec261d55a1e7cc4dd7b06d"
            );
        }};

        String data =
                """
                        <checksums>
                        \t<item>
                        \t\t<checksum>c75c9c46abc4642e2b86a5405adb27fe</checksum>
                        \t\t<path>C:\\Fani\\Fani\\12 клас\\Графичен дизайн\\Harry Potter.svg</path>
                        \t\t<size>20026</size>
                        \t</item>
                        \t<item>
                        \t\t<checksum>9c7f1859c559b59214e05c230b2332ac</checksum>
                        \t\t<path>C:\\Fani\\Fani\\12 клас\\Графичен дизайн\\CV_Stefanka_Manahova.png</path>
                        \t\t<size>192035</size>
                        \t</item>
                        \t<item>
                        \t\t<checksum>3b775f20be8e1afc766a1dca51a8d050</checksum>
                        \t\t<path>C:\\Fani\\Fani\\12 клас\\Графичен дизайн\\kmr_project.docx</path>
                        \t\t<size>13352</size>
                        \t</item>
                        \t<item>
                        \t\t<checksum>39883b573dec261d55a1e7cc4dd7b06d</checksum>
                        \t\t<path>C:\\Fani\\Fani\\12 клас\\Графичен дизайн\\june.png</path>
                        \t\t<size>36901</size>
                        \t</item>
                        </checksums>
                        """;

        try
        {
            XMLChecksumFile xml = new XMLChecksumFile();
            String res = xml.createReport(checksums);
            assertEquals(data, res);
        } catch (IllegalArgumentException ex)
        {
            System.out.println(ex.toString());
        }
    }
}