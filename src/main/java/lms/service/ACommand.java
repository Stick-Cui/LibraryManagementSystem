package lms.service;

import lms.dao.UserDao;
import lms.entity.Book;
import lms.entity.User;
import lms.util.Command;
import lms.util.Constant;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

public abstract class ACommand {

    public Map<String,String> accessControlWithLogin(String command,String commandLine){
        Map<String,String> returnMap = new HashMap<String,String>();
        if (UserDao.currentLoginUser.getName() == null){
            returnMap.put(Constant.YES_OR_NO,Constant.NO);
            returnMap.put(Constant.MSG,Constant.PLEASE_LOGIN);
            return returnMap;
        }
        returnMap = accessControl(UserDao.currentLoginUser,command);
        if(returnMap.get(Constant.YES_OR_NO).equals(Constant.NO)){
            return returnMap;
        }
        returnMap = checkCommandLine(command,commandLine);
        if(returnMap.get(Constant.YES_OR_NO).equals(Constant.NO)){
            return returnMap;
        }
        return returnMap;
    }
    public Map<String,String> accessControlWithoutLogin(String command,String commandLine){
        Map<String,String> map = accessControl(command);
        if(map.get(Constant.YES_OR_NO).equals(Constant.NO)){
            return map;
        }
        map = checkCommandLine(command,commandLine);
        if(map.get(Constant.YES_OR_NO).equals(Constant.NO)){
            return map;
        }
        map.put(Constant.YES_OR_NO,Constant.YES);
        return map;
    }

    public Book getBookInfoFromCommandLine(String commandline){
        Book book = new Book();
        String nameAndAuthorStr = commandline.substring(commandline.indexOf("\"")+1,commandline.lastIndexOf("\""));
        String[] arr = nameAndAuthorStr.split("\" \"");
        book.setName(arr[0]);
        book.setAuthor(arr[1]);
        if(!commandline.endsWith("\"")){
            book.setInventory(commandline.substring(commandline.lastIndexOf("\"")+1).trim());
        }
        return book;
    }

    public User getUserInfoFromCommandLine(String commandline) {
        User user = new User();
        String[] arr = commandline.split(Constant.COMMAND_SPLIT);
        if (commandline.contains(Constant.ROLE_ADMIN.toLowerCase())) {
            user.setRole(Constant.ROLE_ADMIN);
            user.setName(arr[2]);
            user.setPassword(arr[3]);
        }else if(commandline.contains(Constant.ROLE_USER.toLowerCase())){
            user.setRole(Constant.ROLE_USER);
            user.setName(arr[2]);
            user.setPassword(arr[3]);
        }else{
            user.setName(arr[1]);
            user.setPassword(arr[2]);
        }
        return user;
    }


    private Map<String,String> accessControl(User user,String command){
        Map<String,String> returnMap = new HashMap<String,String>();
        if ((user.getRole().equals(Constant.ROLE_ADMIN)||user.getRole().equals(Constant.ROLE_USER))
            && Command.LIST.toString().toLowerCase().equals(command)){
            returnMap.put(Constant.YES_OR_NO,Constant.YES);
        }else if(user.getRole().equals(Constant.ROLE_ADMIN)){
            if (Command.ADD.toString().toLowerCase().equals(command)
            || Command.DELETE.toString().toLowerCase().equals(command)
            ){
                returnMap.put(Constant.YES_OR_NO,Constant.YES);
            }else{
                returnMap.put(Constant.YES_OR_NO,Constant.NO);
                returnMap.put(Constant.MSG,MessageFormat.format(Constant.COMMAND_RIGHT_CHECK,command,user.getRole()));
            }
        }else if(user.getRole().equals(Constant.ROLE_USER)){
            if (Command.BORROW.toString().toLowerCase().equals(command)
                || Command.SEARCH.toString().toLowerCase().equals(command)
                || Command.RETURN.toString().toLowerCase().equals(command)
            ){
                returnMap.put(Constant.YES_OR_NO,Constant.YES);
            }else{
                returnMap.put(Constant.YES_OR_NO,Constant.NO);
                returnMap.put(Constant.MSG,MessageFormat.format(Constant.COMMAND_RIGHT_CHECK,command,user.getRole()));
            }
        }
        return returnMap;
    }

    private Map<String,String> accessControl(String command){
        Map<String,String> returnMap = new HashMap<String,String>();
        if (Command.LOGIN.toString().toLowerCase().equals(command)
                || Command.REGISTER.toString().toLowerCase().equals(command)
        ){
            returnMap.put(Constant.YES_OR_NO,Constant.YES);
        }else{
            returnMap.put(Constant.YES_OR_NO,Constant.NO);
        }
        return returnMap;
    }

    public abstract Map<String,String> checkCommandLine(String command,String commandLine);

}
