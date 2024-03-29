import java.io.Console;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.io.IOException;
import java.util.List;

public class Main
{
    public static void main(String[] args) throws IOException {
        String text = "a,b,c\n123.4,567.8,91011.12";
        CSVReader reader = new CSVReader(new StringReader(text),",",true);

        List<String> headers = reader.getHeader();
        for(String header : headers)
        {
            System.out.print(header + " ");
        }

        System.out.println();

        while(reader.next())
        {
            for(String column : reader.getColumnLabels())
            {
                System.out.print(reader.get(column) + " ");
            }
            System.out.println();
        }
    }
}
