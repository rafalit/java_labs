import junit.framework.TestCase;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ListItemTest extends TestCase {

    public void testWriteHTML()
    {
        String content = "One more heartbreak and I'll be long gone";

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        new ListItem(content).writeHTML(ps);
        String generatedHTML = os.toString();

        String expectedHTML = "<li>One more heartbreak and I'll be long gone</li>\r\n";
        assertEquals(expectedHTML, generatedHTML);
    }
}