package eu.branislavpalacka.library.domain;

import org.springframework.lang.NonNull;

import java.util.Objects;

public class BooksGenres {
    @NonNull
    private Integer bookId;
    @NonNull
    private Integer genreId;

    public BooksGenres(@NonNull Integer bookId, @NonNull Integer genreId) {
        this.bookId = bookId;
        this.genreId = genreId;
    }

    @NonNull
    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(@NonNull Integer bookId) {
        this.bookId = bookId;
    }

    @NonNull
    public Integer getGenreId() {
        return genreId;
    }

    public void setGenreId(@NonNull Integer genreId) {
        this.genreId = genreId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BooksGenres that = (BooksGenres) o;
        return bookId.equals(that.bookId) && genreId.equals(that.genreId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, genreId);
    }
}
