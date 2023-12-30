import tr.com.workintech.library.books.Book;
import tr.com.workintech.library.person.Librarian;
import tr.com.workintech.library.library.Library;
import tr.com.workintech.library.automation.Automation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library munirsLibrary = new Library();

        Librarian lMunir = new Librarian("munirbesye", "12321");

        Automation atmn = new Automation();
        atmn.addBook(munirsLibrary);

        int secenek = 0;
        System.out.println("Kullanıcı adı ve şifre giriniz:");
        System.out.print("Kullanıcı adı: ");
        String kadi = scanner.nextLine();

        System.out.print("Şifre giriniz: ");
        String sifre = scanner.nextLine();


        while(secenek != -1){
            if(kadi.equals(lMunir.getName()) && sifre.equals(lMunir.getPassword())){
                System.out.print ("--------------------------------------\nMünir'in Kütüphane'sine hoşgeldiniz.\nNe yapmak istersiniz, tuşlayınız. \n1- Okuyucu kaydetmek istiyorum\n2- Kitap eklemek istiyorum\n3- Kitap ödünç vermek istiyorum\n4- Kitap satmak istiyorum\n5- Kitap iade almak istiyorum\n6- Kayıtlı kitapları listele\n7- Yazarları listele\n8- Kategoriye göre kitapları listele\n9- Kitap güncelle\n10- Kitap silmek istiyorum\n11- Yazarın kitaplarını listele\n12- Okuyucuları listele\n13- Fişleri Listele\n14- Çıkış\nGirdiğiniz Sayı: ");
                secenek = Integer.parseInt(scanner.nextLine());

                switch(secenek) {
                    case 1:
                        munirsLibrary.addReader();
                        break;
                    case 2:
                        munirsLibrary.newBook(lMunir);
                        break;
                    case 3:
                        munirsLibrary.lendBook(lMunir);
                        break;
                    case 4:
                        munirsLibrary.sellBook(lMunir);
                        break;
                    case 5:
                        munirsLibrary.takeBackBook(lMunir);
                        break;
                    case 6:
                        munirsLibrary.getBooks().forEach((k, v) -> {
                            v.forEach(Book::display);
                        });
                        break;
                    case 7:
                        munirsLibrary.getAuthors().forEach(y -> {
                            System.out.println(y.getName());
                        });
                        break;
                    case 8:
                        munirsLibrary.showBooksByCategory(munirsLibrary.getBooks());
                        break;
                    case 9:
                        lMunir.updateBook(munirsLibrary);
                        break;
                    case 10:
                        munirsLibrary.deleteBook(lMunir);
                        break;
                    case 11:
                        lMunir.showBooksByAuthor(munirsLibrary);
                        break;
                    case 12:
                        munirsLibrary.showReaders();
                        break;
                    case 13:
                        munirsLibrary.showReceipts();
                        break;
                    default:
                        secenek = -1;
                        break;
                }
            } else {
                System.out.println("Girilen bilgilerle eşleşen bir kayıt bulunamadı. Kullanıcı adı ve şifre giriniz:");
                System.out.print("Kullanıcı adı: ");
                kadi = scanner.nextLine();

                System.out.print("Şifre giriniz: ");
                sifre = scanner.nextLine();
            }
        }
    }
}