package tr.com.workintech.library.receipt;

import tr.com.workintech.library.books.Book;
import tr.com.workintech.library.person.Reader;

import java.util.Date;

public class Receipt {
    private Reader reader;
    private Book bBook;
    private Date dateofPurchase;
    private double amount;
    private String status;

    public Receipt(Reader reader, Book bBook, double amount, String status) {
        this.reader = reader;
        this.bBook = bBook;
        this.dateofPurchase = new Date();
        this.amount = amount;
        this.status = status;
    }

    public Reader getReader() {
        return reader;
    }

    public Book getBook() {
        return bBook;
    }

    public double getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }
}
