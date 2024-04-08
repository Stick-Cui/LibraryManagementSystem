package lms.service.implement;

import com.sun.xml.internal.ws.util.StringUtils;
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

public class CommandReturnImpl extends ACommand implements ICommand {


    @Override
    public String process(String command,String commandLine) throws Exception {
        Map<String,String> map = super.accessControlWithLogin(command,commandLine);
        if(map.get(Constant.YES_OR_NO).equals(Constant.NO)){
            return map.get(Constant.MSG);
        }else{
            return returnBook(commandLine);
        }
    }

    public String returnBook(String commandLine) throws Exception {
        Book paramBook = getBookInfoFromCommandLine(commandLine);
        Book existingBook = BookDao.queryBook(paramBook);
        if (existingBook.getName()==null){
            return MessageFormat.format(Constant.BOOK_NOT_EXISTED,paramBook.getName());
        }else {
            BookDao.returnBook(paramBook);
            return MessageFormat.format(Constant.SUCCESSFULLY_RETURN_BOOK,paramBook.getName());
        }
    }

    @Override
    public Map<String,String> checkCommandLine(String command,String commandLine) {
        Pattern r = Pattern.compile(Constant.REGEX_RETURN);
        Matcher m = r.matcher(commandLine);
        System.out.println("match:"+m.matches());
        Map<String,String> returnMap = new HashMap<String,String>();
        if (! m.matches()){
            returnMap.put(Constant.YES_OR_NO,Constant.NO);
            returnMap.put(Constant.MSG,
                    MessageFormat.format(Constant.COMMAND_LINE_CHECK,command,Constant.COMMAND_RETURN_USAGE));
        }else{
            returnMap.put(Constant.YES_OR_NO,Constant.YES);
        }
        return returnMap;
    }

}
