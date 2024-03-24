public class Paragraph
{
    String content;

    Paragraph(){}

    Paragraph(String content)
    {
        this.content = content;
    }

    Paragraph setContent(String content)
    {
        this.content=content;
        return this;
    }

    String getContent()
    {
        return content;
    }

}
