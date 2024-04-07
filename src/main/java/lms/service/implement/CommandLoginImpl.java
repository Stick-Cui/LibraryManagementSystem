package lms.service.implement;

import lms.dao.UserDao;
import lms.entity.User;
import lms.service.ICommand;
import lms.util.Constant;

import java.text.MessageFormat;

public class CommandLoginImpl implements ICommand {
    @Override
    public String process(String[] arr) throws Exception {
        User paramUser = new User();
        paramUser.setName(arr[1]);
        paramUser.setPassword(arr[2]);
        User existingUser = UserDao.validateUser(paramUser)?UserDao.queryUser(paramUser):null;
        if (existingUser!=null){
            UserDao.currentLoginUser = existingUser;
            return MessageFormat.format(Constant.SUCCESSFULLY_LOGIN,existingUser.getRole(),existingUser.getName());
        }else {
            return Constant.FAIL_LOGIN;
        }
    }
}
