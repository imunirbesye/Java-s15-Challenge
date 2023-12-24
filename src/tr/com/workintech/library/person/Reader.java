package tr.com.workintech.library.person;

import tr.com.workintech.library.books.Book;

public class Reader extends MemberRecord {
    public Reader(String type, String name, String address, String phoneNo, double cash) {
        super(type, name, address, phoneNo, cash);
    }

    public void purchaseBook(Book book) {
        if(book.getStatus().equals("Free") && this.getMaxBookLimit() > this.getNoBooksIssued()){
            book.updateStatus("Purchased");
            book.changeOwner(this);

            this.noIssuedBook();
            this.payThePrice(book.getpPrice());
        } else {
            System.out.println("Kitap alma limitiniz doldu.");
        }
    }

    public void borrowBook(Book book) {
        if(book.getStatus().equals("Free") && this.getMaxBookLimit() > this.getNoBooksIssued()){
            book.updateStatus("Borrowed");
            book.changeOwner(this);

            this.noIssuedBook();
            this.payThePrice(book.getbPrice());

        } else {
            System.out.println("Kitap alma limitiniz doldu.");
        }
    }

    public void returnBook(Book book, double amount) {
        if(book.getOwner().equals(this)) {
            this.takeThePrice(amount);

            book.updateStatus("Free");
            book.changeOwner(null);
        }
    }

    public void payThePrice(double price){
        this.setCash(this.getCash() - price);
    }

    public void takeThePrice(double price) {
        this.setCash(this.getCash() + price);
    }

    public void displayReader() {
        System.out.println("Ad Soyad: " + this.getName() + ", Adres: " + this.getAddress() + ", Telefon: " + this.getPhoneNo() + ", Nakit: " + this.getCash());
    }
}
