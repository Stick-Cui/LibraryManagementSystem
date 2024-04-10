package lms.util;

public class Constant {

    public static String COMMAND_RIGHT_CHECK = "This command:{0} is not allowed for the Role:{1} user!";
    public static String COMMAND_LINE_CHECK = "{0} command: invalid command line,{1}";
    public static String COMMAND_LIST_USAGE = "usage: list";
    public static String COMMAND_ADD_USAGE = "usage: add \"bookName\" \"author\" <inventory:0-9>";
    public static String COMMAND_BORROW_USAGE = "usage: borrow \"bookName\" \"author\"";
    public static String COMMAND_DELETE_USAGE = "usage: delete \"bookName\" \"author\"";
    public static String COMMAND_LOGIN_USAGE = "usage: login username password";
    public static String COMMAND_REGISTER_USAGE = "usage: register role(admin or user) username password";
    public static String COMMAND_RETURN_USAGE = "usage: return \"bookName\" \"author\"";
    public static String COMMAND_SEARCH_USAGE = "usage: search \"bookName\" \"author\"";
    public static String YES_OR_NO = "YES_OR_NO";
    public static String NO = "NO";
    public static String YES = "YES";
    public static String MSG = "MSG";
    public static String COMMAND_SPLIT = "\\s";
    public static String ROLE_ADMIN = "Admin";
    public static String ROLE_USER = "User";
    public static String BOOK_DATA_FILE = "book.txt";
    public static String USER_DATA_FILE = "user.txt";
    public static String COMMAND_EXIT = "exit";
    public static String SUCCESSFULLY_REGISTER = "{0} {1} successfully registered.";
    public static String SUCCESSFULLY_LOGIN = "{0} {1} successfully logged in.";
    public static String FAIL_LOGIN = "login fail, please check username&password, or this user has not registered!";
    public static String SUCCESSFULLY_ADD_BOOK = "Book \"{0}\" by {1} added successfully, inventory: {2}.";
    public static String SUCCESSFULLY_BORROW_BOOK = "Book \"{0}\" successfully borrowed.";
    public static String PLEASE_LOGIN = "please login first!";
    public static String SUCCESSFULLY_DELETE_BOOK = "Book \"{0}\" successfully deleted.";
    public static String SUCCESSFULLY_RETURN_BOOK = "Book \"{0}\" successfully returned.";
    public static String SUCCESSFULLY_UPDATE_BOOK = "Book \"{0}\" inventory successfully updated, new inventory: {1}.";
    public static String UNSUCCESSFULLY_DELETE_BOOK = "Cannot delete book \"{0}\" because {1}.";
    public static String BOOK_ALREADY_BORROWED = "it is currently borrowed";
    public static String BOOK_NOT_EXISTED = "this book:{0} is not existing in the system,please check this book name";
    public static String BOOK_LIST = "Book List:";
    public static String BOOK_INFO_ROW = "{0} - {1} - Inventory: {2}";
    public static String REGEX_ADD = "add\\s\"[A-Za-z0-9\\s\\-\\.]+\"\\s\"[A-Za-z\\s\\-\\.]+\"\\s[\\d]";
    public static String REGEX_BORROW = "borrow\\s\"[A-Za-z0-9\\s\\-\\.]+\"\\s\"[A-Za-z\\s\\-\\.]+\"";
    public static String REGEX_DELETE = "delete\\s\"[A-Za-z0-9\\s\\-\\.]+\"\\s\"[A-Za-z\\s\\-\\.]+\"";
    public static String REGEX_RETURN = "return\\s\"[A-Za-z0-9\\s\\-\\.]+\"\\s\"[A-Za-z\\s\\-\\.]+\"";
    public static String REGEX_SEARCH = "search\\s\"[A-Za-z0-9\\s\\-\\.]+\"\\s\"[A-Za-z\\s\\-\\.]+\"";
    public static String REGEX_LIST = "list";
    public static String REGEX_LOGIN = "login\\s[A-Za-z\\s\\-\\.]{3,20}\\s[A-Za-z0-9\\s\\-\\.\\!\\@\\#\\$\\%\\^\\&\\*]{3,15}";
    public static String REGEX_REGISTER = "register\\s(admin|user)\\s[A-Za-z\\s\\-\\.]{3,20}\\s[A-Za-z0-9\\s\\-\\.\\!\\@\\#\\$\\%\\^\\&\\*]{3,15}";


}
