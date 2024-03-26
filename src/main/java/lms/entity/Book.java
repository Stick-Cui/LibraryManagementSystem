package lms.entity;

public class Book {

    private String name;
    private String author;
    private String inventory;
    private Boolean isBorrowed;
    private String borrowedBy;

    public Book() {
    }

    public Book(String name, String author, String inventory) {
        this.name = name;
        this.author = author;
        this.inventory = inventory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getInventory() {
        return inventory;
    }

    public void setInventory(String inventory) {
        this.inventory = inventory;
    }

    public Boolean getBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(Boolean borrowed) {
        isBorrowed = borrowed;
    }

    public String getBorrowedBy() {
        return borrowedBy;
    }

    public void setBorrowedBy(String borrowedBy) {
        this.borrowedBy = borrowedBy;
    }
}
