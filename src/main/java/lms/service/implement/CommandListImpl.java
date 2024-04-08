package lms.service.implement;

import lms.dao.BookDao;
import lms.dao.UserDao;
import lms.entity.Book;
import lms.entity.User;
import lms.service.ACommand;
import lms.service.ICommand;
import lms.util.Constant;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandListImpl extends ACommand implements ICommand {
    @Override
    public String process(String command,String commandLine) throws Exception {
        Map<String,String> map = super.accessControlWithLogin(command,commandLine);
        if(map.get(Constant.YES_OR_NO).equals(Constant.NO)){
            return map.get(Constant.MSG);
        }else{
            return list();
        }
    }

    public String list() throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(Constant.BOOK_LIST).append("\r\n");
        for (Book book:BookDao.bookList){
            sb.append(MessageFormat.format(Constant.BOOK_INFO_ROW,book.getName(),book.getAuthor(),book.getInventory()));
            sb.append("\r\n");
        }
        sb.replace(sb.length()-1,sb.length(),"");
        return sb.toString();
    }

    @Override
    public Map<String,String> checkCommandLine(String command,String commandLine) {
        Pattern r = Pattern.compile(Constant.REGEX_LIST);
        Matcher m = r.matcher(commandLine);
        System.out.println("match:"+m.matches());
        Map<String,String> returnMap = new HashMap<String,String>();
        if (! m.matches()){
            returnMap.put(Constant.YES_OR_NO,Constant.NO);
            returnMap.put(Constant.MSG,
                    MessageFormat.format(Constant.COMMAND_LINE_CHECK,command,Constant.COMMAND_LIST_USAGE));
        }else{
            returnMap.put(Constant.YES_OR_NO,Constant.YES);
        }
        return returnMap;
    }

}
