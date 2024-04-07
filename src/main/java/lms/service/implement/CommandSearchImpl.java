package lms.service.implement;

import lms.dao.BookDao;
import lms.entity.Book;
import lms.service.ICommand;
import lms.util.Constant;

import java.text.MessageFormat;

public class CommandSearchImpl implements ICommand {

    @Override
    public String process(String[] arr) throws Exception {
        Book paramBook = new Book();
        paramBook.setName(arr[1]);
        paramBook.setAuthor(arr[2]);
        if (BookDao.ifBookExist(paramBook)){
            Book existingBook = BookDao.queryBook(paramBook);
            return MessageFormat.format(Constant.BOOK_INFO_ROW,existingBook.getName(),existingBook.getAuthor(),existingBook.getInventory());
        }else{
            return MessageFormat.format(Constant.BOOK_NOT_EXISTED,paramBook.getName());
        }
    }

}
