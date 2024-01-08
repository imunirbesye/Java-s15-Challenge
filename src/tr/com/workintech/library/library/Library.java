package tr.com.workintech.library.library;

import tr.com.workintech.library.books.Book;
import tr.com.workintech.library.receipt.Receipt;
import tr.com.workintech.library.person.Author;
import tr.com.workintech.library.person.Librarian;
import tr.com.workintech.library.person.Reader;

import java.util.*;

public class Library implements LibraryMethods {
    private HashMap<String, List<Book>> books = new HashMap<>();
    private Set<Reader> readers = new HashSet<>();
    private Set<Author> authors = new HashSet<>();
    private List<Receipt> receipts = new ArrayList<>();

    public HashMap<String, List<Book>> getBooks() {
        return books;
    }

    public Set<Author> getAuthors() { return authors; }

    public void setNewCategory(String category) {
        if(!books.containsKey(category)) {
            books.put(category, new ArrayList<>());
        }
        else
            System.out.println("Bu kategori sisteme çoktan eklenmiş!");
    }

    public void newBook(Librarian librarian){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Eklemek istediğiniz kitabın adını giriniz:");
        String name = scanner.nextLine();

        Book foundBook = librarian.isBookExists(books, name);

        if(foundBook != null){
            System.out.println("Bu isimde bir kitap şu anda kütüphanemizde bulunmaktadır (Aşağıda bilgilerini görebilirsiniz.)");
            foundBook.display();
            System.out.print("Buna rağmen devam etmek istiyor musunuz? (1- Evet, 2- Hayır)");

            int secenek = scanner.nextInt();

            if(secenek == 1) {
                newBookGetInformation(librarian, name);
            } else {
                System.out.println("Kitap ekleme işlemi iptal edilmiştir.");
            }
        } else {
            newBookGetInformation(librarian, name);
        }
    }

    public void newBook(Book book) {
        books.get(book.getType()).add(book);
    }

    private void newBookGetInformation(Librarian librarian, String bookName) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Kitabın yazarının adını giriniz: ");
        String authorName = scanner.nextLine();

        if(librarian.searchAuthor(authors, authorName) == null){
            addAuthor(authorName);
        }

        Author author = null;
        for(Author a : authors){
            if(a.getName().equals(authorName))
                author = a;
        }

        System.out.print("Hangi kategoride? (" + books.keySet() + "): ");
        String category = scanner.nextLine();

        if(!books.containsKey(category)) {
            System.out.println("Yeni kategori tanımlanıyor...");
            books.put(category, new ArrayList<>());
        }

        System.out.print("Satın alma fiyatı ne olacak?: ");
        double pPrice = scanner.nextDouble();

        System.out.print("Ödünç alma fiyatı ne olacak?: ");
        double bPrice = scanner.nextDouble();

        System.out.print("Kaçıncı basım?: ");
        int edition = scanner.nextInt();

        Book nBook = new Book(author, bookName, category, pPrice, bPrice, edition);
        books.get(category).add(nBook);

        System.out.println(nBook.getTitle() + " isimli kitap sisteme başarılı bir şekilde eklenmiştir.");
        nBook.display();
    }

    public void addAuthor(String authorName) {
        Author newAuthor = new Author(authorName);
        authors.add(newAuthor);
    }

    public void addReader() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ad soyad: ");
        String name = scanner.nextLine();

        Reader extistingR = null;
        for(Reader r: readers){
            if(r.getName().equals(name))
                extistingR = r;
        }

        if(extistingR == null){
            addReaderControl(name);
        } else {
            System.out.print("Bu isimde bir kullanıcı bulunmaktadır. Buna rağmen girmek istiyor musunuz? \n");
            extistingR.displayReader();
            System.out.print("1- Eklemeye devam et\n2- Eklemekten vazgeç\nGirdiğiniz sayi: ");
            int sec = Integer.parseInt(scanner.nextLine());
            if(sec == 1) {
                addReaderControl(name);
            }
            else {
                System.out.println("Okuyu eklemekten vazgeçtiniz. Ana menüye yönlendiriliyorsunuz...");
            }
        }
    }

    private void addReaderControl(String name){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Okuyucu tip: ");
        String type = scanner.nextLine();

        System.out.print("Adres: ");
        String address = scanner.nextLine();

        System.out.print("Telefon numarası: ");
        String telefon = scanner.nextLine();

        System.out.print("Bakiye: ");
        double cash = Double.parseDouble(scanner.nextLine());

        Reader r = new Reader(type, name, address, telefon, cash);
        readers.add(r);
    }

    public void addReader(Reader reader) {
        readers.add(reader);
    }

    public void deleteBook(Librarian librarian) {
        Book book = null;

        book = librarian.searchBook(books);
        System.out.println("Kitap bilgileri: " + book);

        for(String key : books.keySet()){
            boolean sonuc = false;
            for(Book vBook : books.get(key)) {
                if(vBook == book){
                    sonuc = books.get(key).remove(book);
                    break;
                }
            }
            if(sonuc) break;
        }
    }

    public void lendBook(Librarian librarian) {
        Book book = librarian.searchBook(books);

        if(book != null) {
            if(book.getStatus().equals("Free")) {
                Scanner scanner = new Scanner(System.in);

                System.out.print("Okuyucu adını giriniz: ");
                String rName = scanner.nextLine();

                Reader reader = findReader(rName);

                if(reader == null) {
                    addReaderControl(rName);
                    reader = findReader(rName);
                }

                reader.borrowBook(book);

                Receipt receipt = new Receipt(reader, book, book.getbPrice(), "Lent");
                receipts.add(receipt);

                System.out.println(book.getTitle() + " isimli kitap başarılı bir şekilde ödünç verilmiştir.");
            } else {
                System.out.println("Bu kitap şu anda başkası tarafından alınmış.");
            }
        }
    }

    public void sellBook(Librarian librarian){
        Book book = librarian.searchBook(books);
        Reader reader = findReader();
        if(book != null) {
            if(book.getStatus().equals("Free")) {
                reader.purchaseBook(book);

                Receipt receipt = new Receipt(reader, book, book.getbPrice(), "Sold");
                receipts.add(receipt);

                System.out.println(book.getTitle() + " isimli kitap başarılı bir şekilde satılmıştır.");
            } else {
                System.out.println("Bu kitap şu anda başkası tarafından alınmış.");
            }
        }
    }

    public void takeBackBook(Librarian librarian) {
        if(receipts != null) {
            Book book = librarian.searchBook(books);

            if(book != null) {
                receipts.forEach(r -> {
                    if(r.getBook().getTitle().equals(book.getTitle())){
                        Reader reader = r.getReader();

                        reader.returnBook(book, r.getAmount());
                        r.setStatus("Taken");
                    } else {
                        System.out.println("Bu kitap herhangi biri tarafından alınmış değil.");
                    }
                });
            }
        }
    }

    public void showBooksByCategory(HashMap<String, List<Book>> books) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Kitaplarını listelemek istediğiniz kategoriyi giriniz: ");
        String category = scanner.nextLine();

        if(books.containsKey(category)) {
            System.out.println("-------------------------- \nBu kategoride bulunan kitaplar:");

            books.get(category).forEach(b -> {
                System.out.println(b.getTitle());
            });
        } else {
            System.out.println("Kütüphanemizde olmayan bir kategori girdiniz.");
        }
    }

    public void showReaders() {
        readers.forEach(r -> {
            System.out.println("Okuyucu ID: " + r.getMember_id() + ", İsim soyisim: " + r.getName() + ", Aldığı kitap sayısı: " + r.getNoBooksIssued() + ", Address: " + r.getAddress() + ", Telefon No: " + r.getPhoneNo() + ", Bakiye: " + r.getCash());
        });
    }

    public void showReceipts() {
        for (Receipt receipt : receipts) {
            System.out.println("Okuyucu: " + receipt.getReader().getName() + ", Kitap adı: " + receipt.getBook().getTitle() + ", Tutar: " + receipt.getAmount() + ", Durum: " + receipt.getStatus());
        }
    }

    public Reader findReader(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Okuyucu adını giriniz: ");
        String rName = scanner.nextLine();

        for(Reader reader : readers){
            if(reader.getName().equals(rName))
                return reader;
        }
        return null;
    }

    public Reader findReader(String rName) {
        for(Reader reader : readers){
            if(reader.getName().equals(rName))
                return reader;
        }
        return null;
    }
}
