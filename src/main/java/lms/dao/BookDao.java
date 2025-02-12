package lms.dao;

import lms.entity.Book;
import lms.util.Constant;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.sun.org.apache.bcel.internal.util.SecuritySupport.getResourceAsStream;

public class BookDao {

    public static final List<Book> bookList = new ArrayList<>();

    public static void bookDataCache() {
        try {
            InputStream inputStream = getResourceAsStream(Constant.BOOK_DATA_FILE);
            BufferedReader input = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String line;
            while ((line = input.readLine()) != null){
                String[] arr = line.split(",");
                Book book = new Book();
                book.setAuthor(arr[0]);
                book.setName(arr[1]);
                book.setInventory(arr[2]);
                book.setIfBorrowed(arr.length>3 && !arr[3].isEmpty() && arr[3].equals("1"));
                book.setBorrowedBy(arr.length>4?arr[4]:null);
                bookList.add(book);
            }
            input.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void bookDataPersistence() {
        BufferedWriter bufferedWriter = null;
        try {
            String resourcePath = Objects.requireNonNull(UserDao.class.getClassLoader().getResource("")).getPath();
            //System.out.println(resourcePath);
            File file = new File(resourcePath+Constant.BOOK_DATA_FILE);
            if(!bookList.isEmpty()&&file.exists()&&file.delete()){
                if(file.createNewFile()){
                    bufferedWriter = new BufferedWriter(new FileWriter(file));
                    StringBuilder sb = new StringBuilder();
                    for (Book book:bookList){
                        sb.append(book.getAuthor()).append(",");
                        sb.append(book.getName()).append(",");
                        sb.append(book.getInventory()).append(",");
                        if (book.isIfBorrowed()) {
                            sb.append("1").append(",");
                        } else {
                            sb.append("0").append(",");
                        }
                        if (book.getBorrowedBy()!=null){
                            sb.append(book.getBorrowedBy());
                        }
                        sb.append("\r\n");
                    }
                    bufferedWriter.write(sb.toString());
                    bufferedWriter.flush();
                    bufferedWriter.close();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    public static void addBook(Book book) {
        bookList.add(book);
    }

    public static void updateBook(Book book) {
        Book existingBook = queryBook(book);
        existingBook.setInventory(book.getInventory());
        deleteBook(book);
        bookList.add(existingBook);
    }

    public static void deleteBook(Book book) {
        bookList.removeIf(temBook -> temBook.getName().equals(book.getName())&&temBook.getAuthor().equals(book.getAuthor()));
    }

    public static boolean ifBookExist(Book book) {
        return bookList.stream()
                        .anyMatch(temBook -> temBook.getName().equals(book.getName()) && temBook.getAuthor().equals(book.getAuthor()));
    }
    public static Book queryBook(Book book) {
        Optional<Book> ifExist = bookList.stream()
                .filter(temBook -> temBook.getName().equals(book.getName()) && temBook.getAuthor().equals(book.getAuthor()))
                .findFirst();
        return ifExist.orElseGet(Book::new);
    }
    public static void borrowBook(Book book) {
        for (Book temBook: bookList){
            if (temBook.getName().equals(book.getName()) && temBook.getAuthor().equals(book.getAuthor())) {
                temBook.setIfBorrowed(true);
                temBook.setBorrowedBy(UserDao.currentLoginUser.getName());
            }
        }
    }
    public static void returnBook(Book book) {
        for (Book temBook: bookList){
            if (temBook.getName().equals(book.getName()) && temBook.getAuthor().equals(book.getAuthor())) {
                temBook.setIfBorrowed(false);
                temBook.setBorrowedBy(null);
            }
        }
    }
}
