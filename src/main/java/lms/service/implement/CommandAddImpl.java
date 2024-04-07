package lms.service.implement;

import lms.dao.BookDao;
import lms.entity.Book;
import lms.service.ICommand;
import lms.util.Constant;

import java.text.MessageFormat;

public class CommandAddImpl implements ICommand {

    @Override
    public String process(String[] arr) throws Exception {
        Book paramBook = new Book();
        paramBook.setName(arr[1]);
        paramBook.setAuthor(arr[2]);
        paramBook.setInventory(arr[3]);
        if (BookDao.ifBookExist(paramBook)){
            BookDao.updateBook(paramBook);
            return MessageFormat.format(Constant.SUCCESSFULLY_UPDATE_BOOK,paramBook.getName(),paramBook.getInventory());
        }else{
            BookDao.addBook(paramBook);
            return MessageFormat.format(Constant.SUCCESSFULLY_ADD_BOOK,paramBook.getName(),paramBook.getAuthor(),paramBook.getInventory());
        }
    }

}
