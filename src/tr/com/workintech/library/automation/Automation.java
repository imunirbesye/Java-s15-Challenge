package tr.com.workintech.library.automation;

import tr.com.workintech.library.person.Author;
import tr.com.workintech.library.books.Book;
import tr.com.workintech.library.person.Librarian;
import tr.com.workintech.library.library.Library;
import tr.com.workintech.library.person.Reader;

import java.util.List;

public class Automation {
    public void addBook(Library library, Librarian librarian) {
        library.setNewCategory("Macera");
        library.setNewCategory("Polisiye");
        library.setNewCategory("Romantik");

        library.addAuthor("Münir BESYE");
        library.addAuthor("Arda BESYE");
        library.addAuthor("Ali BESYE");

        List<Author> authors = library.getAuthors().stream().toList();

        Reader rMunir = new Reader("Normal", "Münir BESYE", "Emek Mh. 521 Sk. A:1 D:2 Gebze/KOCAELİ", "5318179227", 500);
        library.addReader(rMunir);

        Reader rArda = new Reader("Normal", "Arda BESYE", "Emek Mh. 521 Sk. A:1 D:2 Gebze/KOCAELİ", "5318179227", 750);
        library.addReader(rArda);

        Book b1 = new Book(authors.get(0), "Sherlock 1", "Polisiye", 75, 15, 1);
        Book b2 = new Book(authors.get(0), "Sherlock 2", "Polisiye", 65, 20, 2);
        Book b3 = new Book(authors.get(1), "Güzel ve Çirkin", "Romantik", 95, 25, 1);
        Book b4 = new Book(authors.get(0), "Fazla Paranın Sonucu", "Macera", 115, 35, 1);
        Book b5 = new Book(authors.get(0), "Samanyolu", "Macera", 115, 35, 1);
        Book b6 = new Book(authors.get(0), "Star Wars", "Macera", 150, 65, 18);

        library.newBook(b1);
        library.newBook(b2);
        library.newBook(b3);
        library.newBook(b4);
        library.newBook(b5);
        library.newBook(b6);

    }
}
