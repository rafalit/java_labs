import java.io.Console;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.io.IOException;
import java.util.List;

public class Main
{
    public static void main(String[] args) throws IOException {
        String text = "Now faith is confidence in what we hope for and assurance about what we do not see\n" +
                "Now faith is confidence in what we hope for and assurance about what we do not see";
        CSVReader r = new CSVReader(new StringReader(text), " ", true);

        while(r.next())
        {
            System.out.println(r.get(1));
            System.out.print(r.get("faith"));
        }
    }

}
