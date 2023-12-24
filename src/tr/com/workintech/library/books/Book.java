package tr.com.workintech.library.books;

import tr.com.workintech.library.library.Library;
import tr.com.workintech.library.person.Author;
import tr.com.workintech.library.person.Reader;

import java.util.Date;
import java.util.Scanner;

public class Book {
    private static int id = 0;
    private int book_ID;
    private Author author;
    private String name;
    private String type;
    private double pPrice;
    private double bPrice;
    private String status;
    private int edition;
    private Date date_of_purchase;
    private Reader owner;

    public Book(Author author, String name, String type, double pPrice, double bPrice, int edition) {
        book_ID = id++;
        this.author = author;
        this.name = name;
        this.type = type;
        this.pPrice = pPrice;
        this.bPrice = bPrice;
        this.edition = edition;
        this.date_of_purchase = new Date();
        this.status = "Free";
    }

    public int getBook_ID() {
        return book_ID;
    }

    public Author getAuthor() {
        return author;
    }

    public String getTitle() {
        return name;
    }

    public String getType() { return type; }

    public double getpPrice() {
        return pPrice;
    }

    public double getbPrice() {
        return bPrice;
    }

    public String getStatus() {
        return status;
    }

    public Reader getOwner(){
        return owner;
    }

    public void setAuthor(Library library) {
        System.out.print("Yeni yazar adı giriniz: ");
        Scanner s = new Scanner(System.in);
        String aName = s.nextLine();

        boolean isExists = false;

        for(Author a : library.getAuthors()){
            if(a.getName().equals(aName)) {
                isExists = true;
                this.author = a;
            }
        }

        if(!isExists) {
            library.addAuthor(aName);

            library.getAuthors().forEach(author -> {
                if(author.getName().equals(aName))
                    this.author = author;
            });
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPPrice(double pPrice) {
        this.pPrice = pPrice;
    }

    public void setBPrice(double bBprice) {
        this.bPrice = bBprice;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public void display() {
        System.out.println("Kitap ID: " + book_ID + ", Kitap Adı: " + name + ", Yazar adı: " + author.getName() + ", Kategori: " + type + ", Satın alma ücreti: " + pPrice + ", Ödünç alma ücreti: " + bPrice + ", Basım: " + edition + ", Durum: " + status);
    }

    public void changeOwner(Reader owner) {
        this.owner = owner;
    }

    public void updateStatus(String status) {
        this.status = status;
    }
}