package lms.service.implement;

import lms.dao.BookDao;
import lms.dao.UserDao;
import lms.entity.Book;
import lms.service.ICommand;
import lms.util.Constant;

import java.text.MessageFormat;

public class CommandBorrowImpl implements ICommand {

    @Override
    public String process(String[] arr) throws Exception {
        Book paramBook = new Book();
        paramBook.setName(arr[1]);
        paramBook.setAuthor(arr[2]);
        Book existingBook = BookDao.queryBook(paramBook);
        if (existingBook.getName().isEmpty()){
            return MessageFormat.format(Constant.BOOK_NOT_EXISTED,paramBook.getName());
        }else if(existingBook.isIfBorrowed()){
            return MessageFormat.format(Constant.BOOK_ALREADY_BORROWED,paramBook.getName());
        }else if(UserDao.currentLoginUser.getName() != null) {
            return Constant.PLEASE_LOGIN;
        }else {
            BookDao.borrowBook(paramBook);
            return MessageFormat.format(Constant.SUCCESSFULLY_BORROW_BOOK,paramBook.getName());
        }
    }

}
