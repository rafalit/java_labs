import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

public class Main
{
    public static void main(String [] args)
    {
        Document cv = new Document("Jana Kowalski - CV");
        cv.setPhoto("https://www.thesun.co.uk/wp-content/uploads/2023/08/nk_iShowSpeed_offplatform.jpg?strip=all&quality=100&w=1620&h=1080&crop=1");
        cv.addSection("Wykształcenie")
                .addParagraph("2000-2005 Przedszkole im. Królewny Snieżki w Zielonej Górze")
                .addParagraph("2006-2012 SP7 im Ronalda Regana w Chicago")
                .addParagraph(
                        new ParagraphWithList().setContent("Kursy")
                                .addListItem("Języka Angielskiego")
                                .addListItem("Języka Hiszpańskiego")
                                .addListItem("Szydełkowania")
                );
        cv.addSection("Umiejętności")
                .addParagraph(
                        new ParagraphWithList().setContent("Znane technologie")
                                .addListItem("C")
                                .addListItem("C++")
                                .addListItem("Java")
                );
        cv.writeHTML(System.out);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("cv.html");
            PrintStream ps = new PrintStream(fileOutputStream);

            cv.writeHTML(ps);
            ps.close();
            fileOutputStream.close();

        } catch (Exception e) {
            System.err.println("Coś poszło nie tak" + e.getMessage());
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(cv);
        String filePath = "cv.json";


        try {
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(json);
            fileWriter.close();
        }
        catch (IOException e){
            System.err.println("Coś poszło nie tak" + e.getMessage());
        }

    }
}
