import java.util.ArrayList;
import java.util.HashSet;

/**
 * Library
 */
public class Library implements Comparable<Library> {

    int libId;
    ArrayList<Book> books;
    int signUpTime;
    int booksPerDay;
    double libraryScore;

    Library(int libId, int bookSize, int signUpTime, int booksPerDay) {
        this.libId = libId;
        books = new ArrayList<Book>(bookSize);
        this.signUpTime = signUpTime;
        this.booksPerDay = booksPerDay;
        libraryScore = 0;
    }

    void addBook(Book b) {
        books.add(b);
    }

    public int compareTo(Library l) {
        return (int) (l.libraryScore - this.libraryScore);
    }

    void findLibraryScore() {
        // int count = 0;
        int bookScore = 0;
        for (Book book : books) {
            // count++;
            bookScore += book.score;
        }
        libraryScore = (double) booksPerDay * bookScore  /Math.sqrt((double)signUpTime) ;
    }

    void removeDuplicateBooks(HashSet<Book> orignalBooks) {
        ArrayList<Book> duplicateBook=new ArrayList<Book>();
        for (Book book : books) {
            if (orignalBooks.contains(book)) {
                duplicateBook.add(book);
            } else {
                orignalBooks.add(book);
            }
        }
        books.removeAll(duplicateBook);
    }
}