import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
public class Document {
    String title;
    Photo photo;
    List<Section> sections = new ArrayList<>();

    Document(String title)
    {
        this.title=title;
    }

    Document setTitle(String title){
        this.title = title;
        return this;
    }

    Document setPhoto(String photoUrl){
        this.photo = new Photo(photoUrl);
        return this;
    }

    Section addSection(String sectionTitle){
        Section section = new Section();
        section.setTitle(sectionTitle);
        sections.add(section);
        return section;
    }
    Document addSection(Section s)
    {
        sections.add(s);
        return this;
    }


    void writeHTML(PrintStream out){
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");

        out.println("<title>" + title + "</title>");
        out.println("</head>");
        out.println("<body>");

        if(photo != null)
        {
            photo.writeHTML(out);
        }

        for(Section section : sections)
        {
            section.writeHTML(out);
        }

        out.println("</body>");
        out.println("</html>");
    }

}
