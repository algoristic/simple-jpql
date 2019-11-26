package infrastructure.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
        @NamedQuery(name = "Book.getAll", query = "SELECT b FROM Book b")
})
public class Book {

    @Id
    private Integer id;
    private String title;
    private Integer year;
    @ManyToOne
    @JoinColumn(name = "AUTHOR_ID")
    private Author author;

    public Book() {
    }

    public Book(Integer id, String title, Integer year, Author author) {
        this.id = id;
        this.title = title;
        this.year = year;
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

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
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
