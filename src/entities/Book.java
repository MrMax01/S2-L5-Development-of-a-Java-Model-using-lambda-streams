package entities;

public class Book extends ReadingElement{

    private String author;
    private String type;

    public Book(int ISBN, String title, int pageNumber, int year, String author, String type) {
        super(ISBN, title, pageNumber, year);
        this.author = author;
        this.type = type;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if(author.equals("")) this.author ="Autore anonimo";
        else this.author = author;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if(type.equals("")) throw new RuntimeException("Inserire il genere...");
        else this.type = type;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + getTitle() + '\'' +
                ", year=" + getYear() +
                ", pageNumber=" + getPageNumber() +
                ", author='" + author + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
