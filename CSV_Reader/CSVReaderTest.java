import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CSVReaderTest {

    @org.junit.jupiter.api.Test
    void parseHeader() throws IOException {
        String text = "a,b,c\n123.4,567.8,91011.12\n1,2,3";
        CSVReader r = new CSVReader(new StringReader(text), ",", true);
        List<String> expected = Arrays.asList("a", "b", "c");
        assertEquals(expected, r.getColumnLabels());
    }

    @org.junit.jupiter.api.Test
    void next() throws IOException
    {
        String text = "I can do all things through him who strengthens me";
        CSVReader r = new CSVReader(new StringReader(text), " ", false);
        while(r.next())
        {
            assertEquals("I", r.get(0));
            assertEquals("can", r.get(1));
            assertEquals("do", r.get(2));
        }
        assertFalse(r.next());
    }

    @org.junit.jupiter.api.Test
    void getColumnLabels() throws IOException
    {
        String text = "a,b,c\n123.4,567.8,91011.12\n1,2,3";
        CSVReader r = new CSVReader(new StringReader(text), ",", true);

        List<String> expected = Arrays.asList("a", "b", "c");
        assertEquals(expected, r.getColumnLabels());
    }

    @org.junit.jupiter.api.Test
    void getRecordLength() throws IOException
    {
        String text = "Taste and see that the Lord is good blessed is the one who takes refuge in him";
        CSVReader r = new CSVReader(new StringReader(text), " ", false);
        while(r.next())
        {
            assertEquals("Taste", r.get(0));
            assertEquals(17, r.getRecordLength());
        }
    }

    @org.junit.jupiter.api.Test
    void isMissing() throws IOException {
        String text = "Cast all your anxieties on him because he cares for you";
        CSVReader r = new CSVReader(new StringReader(text), " ", false);
        while (r.next()) {
            assertEquals("all", r.get(1));
            assertTrue(r.isMissing(9));
        }
    }

    @org.junit.jupiter.api.Test
    void testIsMissing() throws IOException {
        String text = "Come to Me all you who are weary and burdened and I will give you rest";
        CSVReader r = new CSVReader(new StringReader(text), " ", false);
        while(r.next())
        {
            assertEquals("to", r.get(1));
            assertTrue(r.isMissing("are"));
        }
    }


    @org.junit.jupiter.api.Test
    void get() throws IOException
    {
        String text = "As a mother comforts her child so will I comfort you and you will be comforted over Jerusalem\n"
                + "1 2 3 4 5 6 will 8 9 10 11 12 13 14";
        CSVReader r = new CSVReader(new StringReader(text), " ", true);
        while(r.next())
        {
            assertEquals("2", r.get(1));
            assertNotEquals("comforts", r.get(1));
            assertEquals("8", r.get(7));
            assertEquals("will", r.get(6));
        }
    }

    @org.junit.jupiter.api.Test
    void testGet() throws IOException
    {
        String text = "Now faith is confidence in what we hope for and assurance about what we do not see";
        CSVReader r = new CSVReader(new StringReader(text), " ", false);

        String text2 = "Life is a constant process of dying\n" +
                       "dying is a constant process of life";
        CSVReader r2 = new CSVReader(new StringReader(text2), " ", true);
        while (r2.next())
        {
            assertEquals("tttttttt", r2.get("Life"));
        }
    }

    @org.junit.jupiter.api.Test
    void getInt() throws IOException
    {
        String text = "I keep my eyes always on the LORD. With him at my right hand, I shall not be shaken";
        CSVReader r = new CSVReader(new StringReader(text), " ", true);
        while(r.next())
        {
            assertNotEquals(40, r.getInt(40));
            assertEquals(6, r.getInt(4));
        }
    }

    @org.junit.jupiter.api.Test
    void testGetInt() throws IOException
    {
        String text = "Do not be like them, for your Father knows what you need before you ask him";
        CSVReader r = new CSVReader(new StringReader(text), " ", true);
        while(r.next())
        {
            assertThrows(IllegalArgumentException.class, () -> r.getInt("jahwe"));
            assertEquals("dgdo", r.get("0"));
        }
    }

    @org.junit.jupiter.api.Test
    void getLong() {
    }

    @org.junit.jupiter.api.Test
    void testGetLong() {
    }

    @org.junit.jupiter.api.Test
    void getDouble() {
    }

    @org.junit.jupiter.api.Test
    void testGetDouble() {
    }
}