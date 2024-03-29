import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVReader {
    BufferedReader reader;
    String delimiter;
    boolean hasHeader;

    /**
     *
     * @param filename - nazwa pliku
     * @param delimiter - separator pól
     * @param hasHeader - czy plik ma wiersz nagłówkowy
     */

    public CSVReader(String filename,String delimiter,boolean hasHeader) throws IOException {
        reader = new BufferedReader(new FileReader(filename));
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;
        if(hasHeader)parseHeader();
    }

    public CSVReader(String filename,String delimiter) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(filename));
        this.delimiter = delimiter;
    }

    public CSVReader(String filename) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(filename));
    }

    public CSVReader(Reader reader, String delimiter, boolean hasHeader) throws IOException
    {
        this.reader = new BufferedReader(reader);
        this.delimiter=delimiter;
        this.hasHeader=hasHeader;
        if(hasHeader){parseHeader();}
    }

    // nazwy kolumn w takiej kolejności, jak w pliku
    List<String> columnLabels = new ArrayList<>();

    // odwzorowanie: nazwa kolumny -> numer kolumny
    Map<String,Integer> columnLabelsToInt = new HashMap<>();

    public void parseHeader() throws IOException {
        String line = reader.readLine();
        //System.out.println("Raw line from file: " + line);
        if (line == null) {
            return;
        }
        String[] header = line.split(delimiter);
        for (int i = 0; i < header.length; i++) {
            columnLabels.add(header[i]);
            columnLabelsToInt.put(header[i].trim(), i);
        }
    }

    String[]current;
    public boolean next() throws IOException {
        String line = reader.readLine();
        if (line == null) {
            return false;
        }
        String[] el = line.split(delimiter);
        current = el;
        return true;
    }

    public List<String> getColumnLabels()
    {
        return columnLabels;
    }

    public int getRecordLength()
    {
        if(current != null) {
            return current.length;
        }
        return 0;
    }

    public boolean isMissing(int columnIndex)
    {
        if(current != null && columnIndex>=0 && columnIndex< current.length)
        {
            return current[columnIndex] != null && !current[columnIndex].isEmpty();
        }
        return false;
    }

    public boolean isMissing(String columnLabel)
    {
        Integer columnIndex = columnLabelsToInt.get(columnLabel);
        return columnIndex == null;
    }

    String get(int columnIndex)
    {
        if(current != null && columnIndex >= 0 && columnIndex < current.length)
        {
            return current[columnIndex];
        }
        return " ";
    }

    String get(String columnLabel)
    {
        if (!hasHeader) {
            throw new IllegalStateException("The CSV file does not have a header.");
        }

        Integer columnIndex = columnLabelsToInt.get(columnLabel);
        //System.out.print(columnLabel);
        if (columnIndex != null)
        {
            //System.out.println(current);
            return current[columnIndex];
        }
        return " ";
    }

    public int getInt(int columnIndex) throws NumberFormatException
    {
        if(current!=null && columnIndex>=0 && columnIndex< current.length)
        {
            return Integer.parseInt(current[columnIndex]);
        }
        else
        {
            throw new IllegalArgumentException("index out of range");
        }
    }



    public int getInt(String columnLabel)
    {
        Integer columnIndex = columnLabelsToInt.get(columnLabel);
        if(columnIndex != null)
        {
            return Integer.parseInt(current[columnIndex]);
        }
        else
        {
            throw new IllegalArgumentException(String.format("There is no column named '%s'", columnLabel));
        }
    }

    public long getLong(int columnIndex) throws NumberFormatException
    {
        if(current != null && columnIndex>=0 && columnIndex<current.length)
        {
            return Long.parseLong(current[columnIndex]);
        }
        throw new IllegalArgumentException("index out of range");
    }

    public long getLong(String columnLabel) throws NumberFormatException
    {
        Integer columnIndex = columnLabelsToInt.get(columnLabel);
        if(columnIndex != null)
        {
            return Long.parseLong(current[columnIndex]);
        }
        throw new NumberFormatException(String.format("there is no column like '%s'", columnLabel));
    }

    public double getDouble(int columnIndex) throws NumberFormatException
    {
        if(current!=null && columnIndex>=0 && columnIndex<current.length)
        {
            return Double.parseDouble(current[columnIndex]);
        }
        else
        {
            throw new NumberFormatException("index out of range");
        }
    }

    double getDouble(String columnLabel) throws NumberFormatException
    {
        Integer columnIndex = columnLabelsToInt.get(columnLabel);
        if(columnIndex != null)
        {
            return Double.parseDouble(current[columnIndex]);
        }
        throw new NumberFormatException(String.format("there is no column named '%s'", columnLabel));
    }

}