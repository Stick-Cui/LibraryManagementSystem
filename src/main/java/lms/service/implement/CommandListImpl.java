package lms.service.implement;

import lms.dao.BookDao;
import lms.dao.UserDao;
import lms.entity.Book;
import lms.entity.User;
import lms.service.ICommand;
import lms.util.Constant;

import java.text.MessageFormat;

public class CommandListImpl implements ICommand {
    @Override
    public String process(String[] Arr) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(Constant.BOOK_LIST).append("\r\n");
        for (Book book:BookDao.bookList){
            sb.append(book.getAuthor()).append(",");
            sb.append(book.getName()).append(",");
            sb.append(book.getInventory());
            sb.append("\r\n");
        }
        sb.replace(sb.length()-1,sb.length(),"");
        return sb.toString();
    }

}
