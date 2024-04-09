package lms.service.implement;

import lms.dao.BookDao;
import lms.entity.Book;
import lms.service.ACommand;
import lms.service.ICommand;
import lms.util.Constant;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandDeleteImpl extends ACommand implements ICommand {
    @Override
    public String process(String command,String commandLine) throws Exception {
        Map<String,String> map = super.accessControlWithLogin(command,commandLine);
        if(map.get(Constant.YES_OR_NO).equals(Constant.NO)){
            return map.get(Constant.MSG);
        }else{
            return delete(commandLine);
        }
    }

    public String delete(String commandLine) throws Exception {
        Book paramBook = getBookInfoFromCommandLine(commandLine);
        Book existingBook = BookDao.queryBook(paramBook);
        if (existingBook.getName()==null){
            return MessageFormat.format(Constant.UNSUCCESSFULLY_DELETE_BOOK,paramBook.getName(),MessageFormat.format(Constant.BOOK_NOT_EXISTED,paramBook.getName()));
        }else if(existingBook.isIfBorrowed()){
            return MessageFormat.format(Constant.UNSUCCESSFULLY_DELETE_BOOK,paramBook.getName(),Constant.BOOK_ALREADY_BORROWED);
        }else {
            BookDao.deleteBook(paramBook);
            return MessageFormat.format(Constant.SUCCESSFULLY_DELETE_BOOK,paramBook.getName(),paramBook.getAuthor(),paramBook.getInventory());
        }
    }


    @Override
    public Map<String,String> checkCommandLine(String command,String commandLine) {
        Pattern r = Pattern.compile(Constant.REGEX_DELETE);
        Matcher m = r.matcher(commandLine);
        //System.out.println("match:"+m.matches());
        Map<String,String> returnMap = new HashMap<String,String>();
        if (! m.matches()){
            returnMap.put(Constant.YES_OR_NO,Constant.NO);
            returnMap.put(Constant.MSG,
                    MessageFormat.format(Constant.COMMAND_LINE_CHECK,commandLine.split(Constant.COMMAND_SPLIT)[0],Constant.COMMAND_DELETE_USAGE));
        }else{
            returnMap.put(Constant.YES_OR_NO,Constant.YES);
        }
        return returnMap;
    }

}
