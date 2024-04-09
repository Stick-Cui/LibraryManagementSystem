package lms.service.implement;

import lms.dao.BookDao;
import lms.dao.UserDao;
import lms.entity.Book;
import lms.service.ACommand;
import lms.service.ICommand;
import lms.util.Constant;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandBorrowImpl extends ACommand implements ICommand {

    @Override
    public String process(String command,String commandLine) throws Exception {
        Map<String,String> map = super.accessControlWithLogin(command,commandLine);
        if(map.get(Constant.YES_OR_NO).equals(Constant.NO)){
            return map.get(Constant.MSG);
        }else{
            return borrow(commandLine);
        }
    }

    public String borrow(String commandLine) throws Exception {
        Book paramBook = getBookInfoFromCommandLine(commandLine);
        Book existingBook = BookDao.queryBook(paramBook);
        if (existingBook.getName()==null){
            return MessageFormat.format(Constant.BOOK_NOT_EXISTED,paramBook.getName());
        }else if(existingBook.isIfBorrowed()){
            return MessageFormat.format(Constant.BOOK_ALREADY_BORROWED,paramBook.getName());
        }else {
            BookDao.borrowBook(paramBook);
            return MessageFormat.format(Constant.SUCCESSFULLY_BORROW_BOOK,paramBook.getName());
        }
    }


    @Override
    public Map<String,String> checkCommandLine(String command,String commandLine) {
        Pattern r = Pattern.compile(Constant.REGEX_BORROW);
        Matcher m = r.matcher(commandLine);
        //System.out.println(m.matches());
        Map<String,String> returnMap = new HashMap<String,String>();
        if (! m.matches()){
            returnMap.put(Constant.YES_OR_NO,Constant.NO);
            returnMap.put(Constant.MSG,
                    MessageFormat.format(Constant.COMMAND_LINE_CHECK,command,Constant.COMMAND_BORROW_USAGE));
        }else{
            returnMap.put(Constant.YES_OR_NO,Constant.YES);
        }
        return returnMap;
    }

}
