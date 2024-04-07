package lms.service.implement;

import lms.dao.BookDao;
import lms.entity.Book;
import lms.service.ICommand;
import lms.util.Constant;

import java.text.MessageFormat;

public class CommandDeleteImpl implements ICommand {
    @Override
    public String process(String[] arr) throws Exception {
        Book paramBook = new Book();
        paramBook.setName(arr[1]);
        paramBook.setAuthor(arr[2]);
        Book existingBook = BookDao.queryBook(paramBook);
        if (existingBook.getName().isEmpty()){
            return MessageFormat.format(Constant.UNSUCCESSFULLY_DELETE_BOOK,paramBook.getName(),MessageFormat.format(Constant.BOOK_NOT_EXISTED,paramBook.getName()));
        }else if(existingBook.isIfBorrowed()){
            return MessageFormat.format(Constant.UNSUCCESSFULLY_DELETE_BOOK,paramBook.getName(),Constant.BOOK_ALREADY_BORROWED);
        }else {
            BookDao.deleteBook(paramBook);
            return MessageFormat.format(Constant.SUCCESSFULLY_DELETE_BOOK,paramBook.getName(),paramBook.getAuthor(),paramBook.getInventory());
        }
    }

}
