package lms.entity;

import java.util.List;

public class User {

    private String role;
    private String name;
    private String password;
    private List<Book> borrowedBookList;

    public User() {
    }

    public User(String role, String name, String password) {
        this.role = role;
        this.name = name;
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Book> getBorrowedBookList() {
        return borrowedBookList;
    }

    public void setBorrowedBookList(List<Book> borrowedBookList) {
        this.borrowedBookList = borrowedBookList;
    }
}
