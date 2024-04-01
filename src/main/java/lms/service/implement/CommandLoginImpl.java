package lms.service.implement;

import lms.LibraryManagementSystemApplication;
import lms.dao.UserDao;
import lms.entity.User;
import lms.service.ICommand;
import lms.util.Constant;

import java.text.MessageFormat;

public class CommandLoginImpl implements ICommand {
    @Override
    public String process(String[] Arr) throws Exception {
        User paramUser = new User();
        paramUser.setName(Arr[1]);
        paramUser.setPassword(Arr[2]);
        User existingUser = UserDao.validateUser(paramUser)?UserDao.queryUser(paramUser):null;
        if (existingUser!=null){
            LibraryManagementSystemApplication.currentLoginUser = existingUser;
            return MessageFormat.format(Constant.SUCCESSFULLY_REGISTER,existingUser.getRole(),existingUser.getName());
        }else {
            return Constant.FAIL_LOGIN;
        }
    }
}
