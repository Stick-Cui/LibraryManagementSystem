package lms.service.implement;

import lms.dao.UserDao;
import lms.entity.User;
import lms.service.ACommand;
import lms.service.ICommand;
import lms.util.Constant;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandLoginImpl extends ACommand implements ICommand {
    @Override
    public String process(String command,String commandLine) throws Exception {
        Map<String,String> map = super.accessControlWithoutLogin(command,commandLine);
        if(map.get(Constant.YES_OR_NO).equals(Constant.NO)){
            return map.get(Constant.MSG);
        }else{
            return login(commandLine);
        }
    }

    public String login(String commandLine) throws Exception {
        User paramUser = getUserInfoFromCommandLine(commandLine);
        User existingUser = UserDao.validateUser(paramUser)?UserDao.queryUser(paramUser):null;
        if (existingUser!=null){
            UserDao.currentLoginUser = existingUser;
            return MessageFormat.format(Constant.SUCCESSFULLY_LOGIN,existingUser.getRole(),existingUser.getName());
        }else {
            return Constant.FAIL_LOGIN;
        }
    }

    @Override
    public Map<String,String> checkCommandLine(String command,String commandLine) {
        Pattern r = Pattern.compile(Constant.REGEX_LOGIN);
        Matcher m = r.matcher(commandLine);
        //System.out.println("match:"+m.matches());
        Map<String,String> returnMap = new HashMap<String,String>();
        if (! m.matches()){
            returnMap.put(Constant.YES_OR_NO,Constant.NO);
            returnMap.put(Constant.MSG,
                    MessageFormat.format(Constant.COMMAND_LINE_CHECK,command,Constant.COMMAND_LOGIN_USAGE));
        }else{
            returnMap.put(Constant.YES_OR_NO,Constant.YES);
        }
        return returnMap;
    }

}
