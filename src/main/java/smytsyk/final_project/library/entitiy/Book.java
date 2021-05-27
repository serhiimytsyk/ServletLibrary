package smytsyk.final_project.library.entitiy;

import java.time.LocalDate;

/**
 * Book
 * contains name, author, publisher, date of publication
 */
public class Book implements Entity {
    private int id;
    private String name;
    private String author;
    private String publisher;
    private LocalDate publicationDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Book book = new Book();

        public Book build() {
            return book;
        }

        public Builder id(int id) {
            book.id = id;
            return this;
        }

        public Builder name(String name) {
            book.name = name;
            return this;
        }

        public Builder author(String author) {
            book.author = author;
            return this;
        }

        public Builder publisher(String publisher) {
            book.publisher = publisher;
            return this;
        }

        public Builder publicationDate(LocalDate publicationDate) {
            book.publicationDate = publicationDate;
            return this;
        }
    }
}
