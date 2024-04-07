package lms.entity;

public class Book {

    private String name;
    private String author;
    private String inventory;
    private boolean ifBorrowed;
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

    public boolean isIfBorrowed() {
        return ifBorrowed;
    }

    public void setIfBorrowed(boolean ifBorrowed) {
        this.ifBorrowed = ifBorrowed;
    }

    public String getBorrowedBy() {
        return borrowedBy;
    }

    public void setBorrowedBy(String borrowedBy) {
        this.borrowedBy = borrowedBy;
    }
}
