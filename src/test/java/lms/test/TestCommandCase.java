package lms.test;


import lms.dao.BookDao;
import lms.dao.UserDao;
import lms.entity.Book;
import lms.entity.User;
import lms.service.ICommand;
import lms.service.implement.*;
import org.junit.jupiter.api.*;

public class TestCommandCase {

    @BeforeAll
    public static void beforeAll() throws Exception {
        Book book1 = new Book("where to go","Ivan C. Mars","4");
        Book book2 = new Book("Sky War","John Smith","3");
        Book book3 = new Book("Moon War","Tom Will","3");
        book3.setIfBorrowed(true);
        book3.setBorrowedBy("Bob");
        Book book4 = new Book("Water War","Han Will","3");
        book4.setIfBorrowed(true);
        book4.setBorrowedBy("Bob");
        BookDao.addBook(book1);
        BookDao.addBook(book2);
        BookDao.addBook(book3);
        BookDao.addBook(book4);
        User user1 = new User("Admin","Alice","password1");
        User user2 = new User("User","Bob","password2");
        UserDao.addUser(user1);
        UserDao.addUser(user2);
    }

    @Test
    public void testRegisterCommand() throws Exception {
        String command = "register";
        String commandLine = "register admin Jim Jim";
        String expect = "Admin Jim successfully registered.";
        ICommand registerCommandService = new CommandRegisterImpl();
        String actual = registerCommandService.process(command,commandLine);
        System.out.println(actual);
        Assertions.assertEquals(expect,actual);
    }

    @Test
    public void testLoginCommand() throws Exception {
        String command = "login";
        String commandLine = "login Bob password2";
        String expect = "User Bob successfully logged in.";
        ICommand loginCommandService = new CommandLoginImpl();
        String actual = loginCommandService.process(command,commandLine);
        System.out.println(actual);
        Assertions.assertEquals(expect,actual);
    }

    @Test
    public void testAddCommand() throws Exception {
        String command = "add";
        String commandLine = "add \"Clean Code\" \"Robert C. Martin\" 5";
        String expect = "Book \"Clean Code\" by Robert C. Martin added successfully, inventory: 5.";
        adminUserLogin();
        ICommand loginCommandService = new CommandAddImpl();
        String actual = loginCommandService.process(command,commandLine);
        System.out.println(actual);
        Assertions.assertEquals(expect,actual);
        commandLine = "add \"Clean Code\" \"Robert C. Martin\" 6";
        expect = "Book \"Clean Code\" inventory successfully updated, new inventory: 6.";
        actual = loginCommandService.process(command,commandLine);
        System.out.println(actual);
        Assertions.assertEquals(expect,actual);
    }

    @Test
    public void testListCommand() throws Exception {
        String command = "list";
        String commandLine = "list";
        String expect = "Book List:";
        ICommand loginCommandService = new CommandListImpl();
        userUserLogin();
        String actual = loginCommandService.process(command,commandLine);
        System.out.println(actual);
        Assertions.assertTrue(actual.startsWith(expect));
    }

    @Test
    public void testBorrowCommand() throws Exception {
        String command = "borrow";
        String commandLine = "borrow \"where to go\" \"Ivan C. Mars\"";
        String expect = "Book \"where to go\" successfully borrowed.";
        ICommand loginCommandService = new CommandBorrowImpl();
        userUserLogin();
        String actual = loginCommandService.process(command,commandLine);
        System.out.println(actual);
        Assertions.assertEquals(expect,actual);
        expect = "it is currently borrowed";
        actual = loginCommandService.process(command,commandLine);
        System.out.println(actual);
        Assertions.assertEquals(expect,actual);
    }

    @Test
    public void testDeleteCommand() throws Exception {
        String command = "delete";
        String commandLine = "delete \"Sky War\" \"John Smith\"";
        String expect = "Book \"Sky War\" successfully deleted.";
        ICommand loginCommandService = new CommandDeleteImpl();
        adminUserLogin();
        String actual = loginCommandService.process(command,commandLine);
        System.out.println(actual);
        Assertions.assertEquals(expect,actual);
        commandLine = "delete \"Moon War\" \"Tom Will\"";
        expect = "Cannot delete book \"Moon War\" because it is currently borrowed.";
        actual = loginCommandService.process(command,commandLine);
        System.out.println(actual);
        Assertions.assertEquals(expect,actual);
    }

    @Test
    public void testReturnCommand() throws Exception {
        String command = "return";
        String commandLine = "return \"Water War\" \"Han Will\"";
        String expect = "Book \"Water War\" successfully returned.";
        ICommand loginCommandService = new CommandReturnImpl();
        String actual = loginCommandService.process(command,commandLine);
        System.out.println(actual);
        Assertions.assertEquals(expect,actual);
    }

    @Test
    public void testSearchCommand() throws Exception {
        String command = "search";
        String commandLine = "search \"Moon War\" \"Tom Will\"";
        String expect = "Moon War - Tom Will - Inventory: 3";
        ICommand loginCommandService = new CommandSearchImpl();
        userUserLogin();
        String actual = loginCommandService.process(command,commandLine);
        System.out.println(actual);
        Assertions.assertEquals(expect,actual);
    }

    private void adminUserLogin() throws Exception {
        UserDao.currentLoginUser.setRole("Admin");
        UserDao.currentLoginUser.setName("Alice");
        UserDao.currentLoginUser.setPassword("password1");
    }

    private void userUserLogin() throws Exception {
        UserDao.currentLoginUser.setRole("User");
        UserDao.currentLoginUser.setName("Bob");
        UserDao.currentLoginUser.setPassword("password2");
    }

}
