package tr.com.workintech.library.person;

import java.util.Date;

public class MemberRecord extends Person {
    private static int id = 0;
    private int member_id;
    private String type;
    private Date dateOfMembership;
    private int noBooksIssued;
    private int maxBookLimit;
    private String address;
    private String phoneNo;
    private double cash;

    public MemberRecord(String type, String name, String address, String phoneNo, double cash) {
        super(name);
        member_id = id++;

        this.type = type;
        this.address = address;
        this.phoneNo = phoneNo;
        this.cash = cash;

        dateOfMembership = new Date();

        this.noBooksIssued = 0;
        this.maxBookLimit = 5;
    }

    public int getMember_id() {
        return member_id;
    }

    public int getNoBooksIssued() {
        return noBooksIssued;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public int getMaxBookLimit() {
        return maxBookLimit;
    }

    public double getCash() { return cash; }

    public void noIssuedBook() {
        this.noBooksIssued++;
    }

    public void setCash(double cash) { this.cash = cash; }
}
