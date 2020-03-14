/**
 * Book
 */
public class Book implements Comparable<Book> {

    int id;
    int score;

    Book(int id, int score) {
        this.id = id;
        this.score = score;
    }

    public int compareTo(Book b) {
        return b.score - this.score;
    }
}