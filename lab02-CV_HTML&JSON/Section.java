import java.util.ArrayList;
import java.util.List;
import java.io.PrintStream;

public class Section {
    String title;
    List<Paragraph> paragraphs = new ArrayList<>() ;
    Section setTitle(String title)
    {
        this.title = title;
        return this;
    }
    Section addParagraph(String paragraphText)
    {
        Paragraph p = new Paragraph(paragraphText);
        paragraphs.add(p);
        return this;
    }
    Section addParagraph(Paragraph p)
    {
        paragraphs.add(p);
        return this;
    }
    public void writeHTML(PrintStream out)
    {
        out.println("<h2>" + title + "</h2>");

        for(Paragraph paragraph : paragraphs)
        {
            out.println("<p>" + paragraph.getContent() + "</p>");
        }
    }
}
