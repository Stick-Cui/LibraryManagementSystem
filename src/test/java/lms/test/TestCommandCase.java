package lms.test;


import lms.dao.BookDao;
import lms.dao.UserDao;
import lms.service.ICommand;
import lms.service.implement.CommandLoginImpl;
import lms.service.implement.CommandRegisterImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestCommandCase {

    @BeforeAll
    public static void beforeAll() throws Exception {
        UserDao.userDataCache();
        BookDao.bookDataCache();
    }

    @Test
    public void testRegisterCommand() throws Exception {
        String command = "register Admin jun jun";
        String expect = "Admin jun successfully registered.";
        ICommand registerCommandService = new CommandRegisterImpl();
        String actual = registerCommandService.process(command.split(" "));
        System.out.println(actual);
        Assertions.assertEquals(expect,actual);
    }

    @Test
    public void testLoginCommand() throws Exception {
        String command = "login jun jun";
        String expect = "User jun successfully logged in.";
        ICommand loginCommandService = new CommandLoginImpl();
        String actual = loginCommandService.process(command.split(" "));
        System.out.println(actual);
        Assertions.assertEquals(expect,actual);
    }






}
