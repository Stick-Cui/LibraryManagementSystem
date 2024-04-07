package lms.service.implement;

import lms.dao.UserDao;
import lms.entity.User;
import lms.service.ICommand;
import lms.util.Constant;

import java.text.MessageFormat;

public class CommandRegisterImpl implements ICommand {
    @Override
    public String process(String[] arr) throws Exception {
        User user = new User();
        user.setRole(arr[1]);
        user.setName(arr[2]);
        user.setPassword(arr[3]);
        UserDao.addUser(user);
        return MessageFormat.format(Constant.SUCCESSFULLY_REGISTER,user.getRole(),user.getName());
    }
}
