package algorithms;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

class ChecksumCalculatorTest
{
    @Test
    @DisplayName("Test with MD5 algorithm")
    public void testMD5()
    {
        ChecksumCalculator md5 = new MD5Calculator();

        String[] s = {"abc", "Hello world!", "design patterns", "Hello there!", "Metallica"};

        InputStream[] in = new InputStream[5];
        for (int i = 0; i < 5; i++)
        {
            in[i] = new ByteArrayInputStream(s[i].getBytes());
        }

        assertAll(
                () -> assertEquals("900150983cd24fb0d6963f7d28e17f72", md5.calculate(in[0])),
                () -> assertEquals("86fb269d190d2c85f6e0468ceca42a20", md5.calculate(in[1])),
                () -> assertEquals("66c3370bf227f8b017a619de7f5897fa", md5.calculate(in[2])),
                () -> assertEquals("a77b55332699835c035957df17630d28", md5.calculate(in[3])),
                () -> assertEquals("8b0ee5a501cef4a5699fd3b2d4549e8f", md5.calculate(in[4]))
        );
    }

    @Test
    @DisplayName("Test with SHA-1 algorithm")
    public void testSHA1()
    {
        ChecksumCalculator sha1 = new SHA1Calculator();

        String[] s = {"abc", "Hello world!", "design patterns", "Hello there!", "Metallica"};

        InputStream[] in = new InputStream[5];
        for (int i = 0; i < 5; i++)
        {
            in[i] = new ByteArrayInputStream(s[i].getBytes());
        }

        assertAll(
                () -> assertEquals("a9993e364706816aba3e25717850c26c9cd0d89d", sha1.calculate(in[0])),
                () -> assertEquals("d3486ae9136e7856bc42212385ea797094475802", sha1.calculate(in[1])),
                () -> assertEquals("833562fd4ae54d1abd7999f56c6f44bb22f3e0fe", sha1.calculate(in[2])),
                () -> assertEquals("6b19cb3790b6da8f7c34b4d8895d78a56d078624", sha1.calculate(in[3])),
                () -> assertEquals("cf28410e21f4a9b9a993c034a1505c537051ef6a", sha1.calculate(in[4]))
        );
    }

    @Test
    @DisplayName("Test with SHA-256 algorithm")
    public void testsha256()
    {
        ChecksumCalculator sha256 = new SHA256Calculator();

        String[] s = {"Fany", "Anakin Skywalker", "Obi-Wan Kenobi", "Padme Amidala",
                        "Leia Organa", "Luke Skywalker", "Han Solo"};

        InputStream[] in = new InputStream[7];
        for (int i = 0; i < 7; i++)
        {
            in[i] = new ByteArrayInputStream(s[i].getBytes());
        }

        assertAll(
                () -> assertEquals("dfd1d8161ce4ffacee276fb4ae2f36f190bb0aa7e19b8ce468b5e9b2cf1141bb", sha256.calculate(in[0])),
                () -> assertEquals("e7c68f75d23b428e7afc5b72ab7fca0d5db7e8f0779d89d5c4b89b3c77f4eadd", sha256.calculate(in[1])),
                () -> assertEquals("6e84377ee76928db67f8e262163d3870356dcaf56be18c56759385d26572738c", sha256.calculate(in[2])),
                () -> assertEquals("299c31f54857470ca0e2608dd0ca9ab213c6b95860dfc69b13f62ec05f11218e", sha256.calculate(in[3])),
                () -> assertEquals("225034c3501e6ae6c465cf74bd92991b2b498183a59b238304ad07b5e93ba129", sha256.calculate(in[4])),
                () -> assertEquals("9d008045040e138bc381072585dd11b053367117edb952b1a20a279f04e2079c", sha256.calculate(in[5])),
                () -> assertEquals("a89025e6f64f390bd60544cd7a6edf31b82e58e29727100fc1e38b35ede41c4e", sha256.calculate(in[6]))
        );
    }
}