import java.util.*;
import java.io.*;

/**
 * Test
 */
public class Test {

    public static void main(String[] args) {
        Scanner sc;
        try {

            File file = new File("b_read_on.txt");
            sc = new Scanner(file);
            int bookSize = sc.nextInt();
            ArrayList<Book> books = new ArrayList<Book>(bookSize);
            int librarySize = sc.nextInt();
            ArrayList<Library> library = new ArrayList<Library>(librarySize);
            int days = sc.nextInt();
            for (int i = 0; i < bookSize; i++) {
                books.add(new Book(i, sc.nextInt()));
            }
            HashSet<Book> orignalBooks = new HashSet<Book>();
            for (int i = 0; i < librarySize; i++) {
                int size = sc.nextInt();
                int signUp = sc.nextInt();
                int bookDay = sc.nextInt();
                Library l = new Library(i, size, signUp, bookDay);
                for (int j = 0; j < size; j++) {
                    l.addBook(getBookByIndex(books, sc.nextInt()));
                }
                Collections.sort(l.books);
                l.findLibraryScore();
                library.add(l);
            }
            Collections.sort(library);
            for (Library l : library) {
                l.removeDuplicateBooks(orignalBooks);
            }
            for (Library l : library) {
                l.findLibraryScore();
            }
            Collections.sort(library);
            // ArrayList of important library
            ArrayList<Library> importantLibrary = new ArrayList<Library>();
            int daysLeft = days;
            for (Library l : library) {
                if(l.books.size()==0){
                    continue;
                }
                if (daysLeft >= 0) {
                    importantLibrary.add(l);
                    daysLeft -= l.signUpTime;
                } else {
                    break;
                }
            }
            sc.close();
            FileWriter writer = new FileWriter("out_b_read_on.txt");
            BufferedWriter buffer = new BufferedWriter(writer);
            buffer.write(importantLibrary.size() + "\n");
            for (Library impLibrary : importantLibrary) {
                buffer.write(impLibrary.libId+" "+impLibrary.books.size()+"\n");
                // if(impLibrary.books.size()!=0){
                    for (Book book : impLibrary.books) {
                        buffer.write(book.id+" ");
                    }
                    buffer.write("\n");
                // }
                
            }
            buffer.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static Book getBookByIndex(ArrayList<Book> books, int bookId) {
        for (Book book : books) {
            if (book.id == bookId) {
                return book;
            }
        }
        return null;

    }
}