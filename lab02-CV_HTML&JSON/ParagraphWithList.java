import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class ParagraphWithList extends Paragraph
{

    private UnorderedList unorderedList;

    public ParagraphWithList()
    {
        super("");
        this.unorderedList = new UnorderedList();
    }
    ParagraphWithList setContent(String content)
    {
        super.setContent(content);
        return this;
    }

    ParagraphWithList addListItem(String item)
    {
        unorderedList.addItem(item);
        return this;
    }

    public void writeHTML(PrintStream out)
    {
        out.println("<p>");
        out.println(getContent());

        if(unorderedList!=null)
        {
            unorderedList.writeHTML(out);
        }

        out.println("</p>");
    }
}
