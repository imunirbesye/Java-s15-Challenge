package tr.com.workintech.library.person;

import tr.com.workintech.library.books.Book;
import tr.com.workintech.library.library.Library;

import java.util.*;

public class Librarian extends Person {
    private String password;

    public Librarian(String name, String password) {
        super(name);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public Author searchAuthor(Set<Author> authors, String authorName) {
        Author result = null;

        if(authors != null) {
            for(Author author: authors){
                if(author.getName().equals(authorName))
                    result = author;
            }
        }

        return result;
    }

    public Book searchBook(HashMap<String, List<Book>> books){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Arama seçiminize göre tuşlayınız: \n1- Kitap adına\n2- Yazar adına\n3- Kitap ID'si\nGirdiğiniz sayı: ");
        int secenek = Integer.parseInt(scanner.nextLine());

        Book result = null;

        if(secenek == 1){
            System.out.print("Kitap adını giriniz: ");
            String bName = scanner.nextLine();

            for(String key : books.keySet()){
                for(Book book : books.get(key)){
                    if(book.getTitle().equals(bName))
                        result =  book;
                }
            }
            if(result == null) {
                System.out.println("Kitap bulunamadı.");
            }
        } else if (secenek == 2) {
            System.out.print("Yazar adını giriniz: ");
            String aName = scanner.nextLine();

            List<Book> authorBooks = new ArrayList<>();

            for(String key : books.keySet()){
                for(Book book : books.get(key)){
                    if(book.getAuthor().getName().equals(aName))
                        authorBooks.add(book);
                }
            }

            if(authorBooks.isEmpty()) {
                System.out.println("Kitap bulunamadı.");
            } else {
                for(int i = 0; i < authorBooks.size(); i++) {
                    System.out.println((i+1) + "- Kitap Adı: " + authorBooks.get(i).getTitle());
                }
                System.out.print("Seçiminizi yapınız: ");
                int s = Integer.parseInt(scanner.nextLine());

                result = authorBooks.get(s-1);
            }
        } else if (secenek == 3) {
            System.out.println("Kitap ID giriniz: ");
            int kID = Integer.parseInt(scanner.nextLine());

            for(String key : books.keySet()){
                for(Book book : books.get(key)){
                    if(book.getBook_ID() == kID)
                        result =  book;
                }
            }

            if(result == null) {
                System.out.println("Kitap bulunamadı.");
            }
        }
        else {
            System.out.println("Yanlış seçtiniz.");
        }

        return result;
    }

    public Book isBookExists(HashMap<String, List<Book>> books, String name){
        Book result = null;

        for(String k : books.keySet()){
            for(Book b : books.get(k)){
                if(b.getTitle().equals(name))
                    result = b;
            }
        }

        return result;
    }

    public void updateBook(Library library) {
        Scanner scanner = new Scanner(System.in);

        Book book = this.searchBook(library.getBooks());
        System.out.println("Kitap bilgileri: " + book);
        if(book != null) {
            System.out.println("Hangi bilgiyi güncellemek istersiniz? \n 1- Yazar \n 2-Kitap Adı \n 3- Kitap türü \n 4- Satın alma fiyatı \n 5- Ödünç alma fiyatı \n 6- Basım\n Girdiğiniz sayı: ");
            int  secenek = scanner.nextInt();

            secenekSwitch(secenek, book, library);
        } else {
            System.out.println("Bu isimde bir kitap bulunamadı.");
        }
    }

    public void showBooksByAuthor(Library library) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Yazarın adını giriniz: ");
        String aName = scanner.nextLine();

        boolean isAuthorExists = false;
        for(Author a: library.getAuthors()){
            if(a.getName().equals(aName)){
                isAuthorExists = true;
                break;
            }
        }

        if(isAuthorExists) {
            System.out.println("-------------------------- \nBu yazara ait kitaplar:");

            library.getBooks().forEach((k, v) -> {
                v.forEach(b -> {
                    if(b.getAuthor().getName().equals(aName))
                        System.out.println(b.getTitle());
                });
            });
        } else {
            System.out.println("Bu yazara ait herhangi bir kitap bulunamamıştır.");
        }

    }

    private void secenekSwitch(int secenek, Book book, Library library) {
        Scanner scanner = new Scanner(System.in);
        String iText;
        switch (secenek) {
            case 1:
                book.setAuthor(library);
                break;
            case 2:
                System.out.print("Yeni kitap adı:");
                iText = scanner.nextLine();
                book.setName(iText);
                break;
            case 3:
                System.out.print("Yeni kitap türü:");
                iText = scanner.nextLine();
                library.setNewCategory(iText);
                book.setType(iText);
                break;
            case 4:
                System.out.print("Yeni satın alma fiyatı:");
                double pPrice = scanner.nextDouble();
                book.setPPrice(pPrice);
                break;
            case 5:
                System.out.print("Yeni ödünç alma fiyatı:");
                double bPrice = scanner.nextDouble();
                book.setBPrice(bPrice);
                break;
            case 6:
                System.out.print("Yeni basım:");
                int basim = scanner.nextInt();
                book.setEdition(basim);
                break;
        }
    }
}
