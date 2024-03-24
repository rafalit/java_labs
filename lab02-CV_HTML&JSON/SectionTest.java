import junit.framework.TestCase;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class SectionTest extends TestCase {

    public void testSetTitle()
    {
        Section element = new Section();
        element.setTitle("title");
        String expected = "title";
        String notExpected = "titt";
        assertEquals(expected, element.title);
        assertNotSame(notExpected, element.title);
    }

    public void testAddParagraph() {
        List<Paragraph> paragraphs = new ArrayList<>() ;
        Paragraph p1 = new Paragraph("Long Gone");
        Paragraph p2 = new Paragraph("Used to");
        paragraphs.add(p1);
        paragraphs.add(p2);
        int expected = 2;
        assertEquals(expected, paragraphs.size());
    }

    public void testTestAddParagraph()
    {
       Section section = new Section();
       Paragraph paragraph = new Paragraph("Hurt me");
       Section updated = section.addParagraph(paragraph);
       assertEquals("Hurt me", updated.getParagraph(0).getContent());
    }

    public void testWriteHTML()
    {
        Section element = new Section();
        element.setTitle("Doom");
        String expected = "<h2>Doom</h2>\r\n";

        ByteArrayOutputStream b = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(b);
        element.writeHTML(printStream);

        String generatedHTML = b.toString();

        assertEquals(expected, generatedHTML);
    }
}