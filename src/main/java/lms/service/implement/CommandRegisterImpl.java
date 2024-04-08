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

public class CommandRegisterImpl extends ACommand implements ICommand {
    @Override
    public String process(String command,String commandLine) throws Exception {
        Map<String,String> map = super.accessControlWithoutLogin(command,commandLine);
        if(map.get(Constant.YES_OR_NO).equals(Constant.NO)){
            return map.get(Constant.MSG);
        }else{
            return register(commandLine);
        }
    }

    public String register(String commandLine) throws Exception {
        User user = getUserInfoFromCommandLine(commandLine);
        UserDao.addUser(user);
        return MessageFormat.format(Constant.SUCCESSFULLY_REGISTER,user.getRole(),user.getName());
    }

    @Override
    public Map<String,String> checkCommandLine(String command,String commandLine) {
        Pattern r = Pattern.compile(Constant.REGEX_REGISTER);
        Matcher m = r.matcher(commandLine);
        System.out.println("match:"+m.matches());
        Map<String,String> returnMap = new HashMap<String,String>();
        if (! m.matches()){
            returnMap.put(Constant.YES_OR_NO,Constant.NO);
            returnMap.put(Constant.MSG,
                    MessageFormat.format(Constant.COMMAND_LINE_CHECK,command,Constant.COMMAND_REGISTER_USAGE));
        }else{
            returnMap.put(Constant.YES_OR_NO,Constant.YES);
        }
        return returnMap;
    }
}
