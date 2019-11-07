package infrastructure.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
        @NamedQuery(name = "Book.getAll", query = "SELECT b FROM Book b"),
        @NamedQuery(name = "Book.getAuthorAndTitle", query = "SELECT b.author, b.title FROM Book b")
})
public class Book {

    @Id
    private Integer id;
    private String title;
    private Integer year;
    private String author;

    public Book() {
    }

    public Book(Integer id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("id=").append(id).append(", ");
        sb.append("title=").append(title).append(", ");
        sb.append("year=").append(year).append(", ");
        sb.append("author=").append(author);
        sb.append("}");
        return sb.toString();
    }

}
