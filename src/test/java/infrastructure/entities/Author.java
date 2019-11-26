package infrastructure.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Author {

    @Id
    private Integer id;
    private String name;
    @OneToMany(mappedBy = "author")
    private List<Book> books;

    public Author() {
    }

    public Author(Integer id, String name, List<Book> books) {
        super();
        this.id = id;
        this.name = name;
        this.books = books;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("id=").append(id).append(", ");
        sb.append("name=").append(name);
        sb.append("}");
        return sb.toString();
    };

}
