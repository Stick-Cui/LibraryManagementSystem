package lms.dao;

import lms.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class BookDao {

    private static List<Book> bookList = new ArrayList<>();

    public static void addBook(Book book) {
        bookList.add(book);
    }

    public static void deleteBook(Book book) {
        bookList.removeIf(temBook -> temBook.getName().equals(book.getName())&&temBook.getAuthor().equals(book.getAuthor()));
    }

    public static boolean ifBookExist(Book book) {
        boolean flag = false;
        for (Book temBook: bookList){
            if (temBook.getName().equals(book.getName()) && temBook.getAuthor().equals(book.getAuthor())) {
                flag = true;
                break;
            }
        }
        return flag;
    }
    public static Book queryBook(Book book) {
        for (Book temBook: bookList){
            if (temBook.getName().equals(book.getName()) && temBook.getAuthor().equals(book.getAuthor())) {
                return temBook;
            }
        }
        return null;
    }

}
