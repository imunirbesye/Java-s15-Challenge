package tr.com.workintech.library.library;

import tr.com.workintech.library.books.Book;
import tr.com.workintech.library.person.Librarian;
import tr.com.workintech.library.person.Reader;

import java.util.HashMap;
import java.util.List;

public interface LibraryMethods {
    void setNewCategory(String category);
    void newBook(Librarian librarian);
    void newBook(Book book);
    void addAuthor(String authorName);
    void addReader();
    void showBooksByCategory(HashMap<String, List<Book>> books);
    void addReader(Reader reader);
    void deleteBook(Librarian librarian);
    void lendBook(Librarian librarian);
    void sellBook(Librarian librarian);
    void takeBackBook(Librarian librarian);
    void showReaders();
    void showReceipts();
    Reader findReader(String rName);
    Reader findReader();
}
