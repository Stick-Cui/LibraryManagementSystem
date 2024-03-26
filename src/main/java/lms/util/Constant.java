package lms.util;

public class Constant {

    public static String COMMAND_WELCOME =
            "================welcome to library management system================\r\n"
            +"Support Below Command:\r\n"
            +"1 register: register Administrator or User\r\n"
            +"  --usage: register role(admin or user) username password\r\n"
            +"2 login: User login\r\n"
            +"  --usage: login username password\r\n"
            +"3 list: list all books\r\n"
            +"  --usage: list\r\n"
            +"4 add: add a new book\r\n"
            +"  --usage: add bookname author inventoty\r\n"
            +"5 search: search book by bookname and author\r\n"
            +"  --usage: search bookname author\r\n"
            +"6 borrow: borrow book by bookname and author\r\n"
            +"  --usage: borrow bookname author\r\n"
            +"7 return: return book by bookname and author\r\n"
            +"  --usage: return bookname author\r\n"
            +"================welcome to library management system================\r\n";
    public static String COMMAND_SPLIT = " ";
    public static String COMMAND_EXIT = "exit";
    //Admin/Role Alice successfully registered.
    public static String SUCCESSFULLY_REGISTER = "{0} {1} successfully registered.";
    public static String SUCCESSFULLY_LOGIN = "{} {} successfully logged in.";
    public static String SUCCESSFULLY_ADD_BOOK = "Book \"{}\" by {} added successfully, inventory: {}.";
    public static String SUCCESSFULLY_BORROW_BOOK = "Book \"{}\" successfully borrowed.";
    public static String SUCCESSFULLY_DELETE_BOOK = "Book \"{}\" successfully deleted.";
    public static String SUCCESSFULLY_RETURN_BOOK = "Book \"{}\" successfully returned.";
    public static String SUCCESSFULLY_UPDATE_BOOK = "Book \"{}\" inventory successfully updated, new inventory: {}";
    public static String UNSUCCESSFULLY_DELETE_BOOK = "Cannot delete book \"{}\" because {}.";
    public static String UNSUCCESSFULLY_DELETE_REASON_BORROWED = "it is currently borrowed";
    public static String BOOK_LIST = "Book List:";
    public static String BOOK_INFO_ROW = "{} - {} - Inventory: {}";


}
