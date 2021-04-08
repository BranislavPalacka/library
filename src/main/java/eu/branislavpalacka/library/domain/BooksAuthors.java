package eu.branislavpalacka.library.domain;

import org.springframework.lang.NonNull;

import java.util.Objects;

public class BooksAuthors {
    @NonNull
    private Integer bookId;
    @NonNull
    private Integer authorId;

    public BooksAuthors(@NonNull Integer bookID, @NonNull Integer authorId) {
        this.bookId = bookID;
        this.authorId = authorId;
    }

    @NonNull
    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(@NonNull Integer bookId) {
        this.bookId = bookId;
    }

    @NonNull
    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(@NonNull Integer authorId) {
        this.authorId = authorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BooksAuthors that = (BooksAuthors) o;
        return bookId.equals(that.bookId) && authorId.equals(that.authorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, authorId);
    }
}
