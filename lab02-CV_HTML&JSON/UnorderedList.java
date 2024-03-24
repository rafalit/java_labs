import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
public class UnorderedList
{
    private List<String> items;

    public UnorderedList()
    {
        this.items = new ArrayList<>();
    }
    public UnorderedList addItem(String item)
    {
        items.add(item);
        return this;
    }

    public void writeHTML(PrintStream out)
    {
        out.println("<ul>");

        for(String item:items)
        {
            out.println("<li>" + item + "</li>");
        }

        out.println("</ul>");
    }
}
