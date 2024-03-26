package lms.service.implement;

import lms.dao.UserDao;
import lms.entity.User;
import lms.service.ICommand;
import lms.util.Constant;
import sun.misc.MessageUtils;

import java.text.MessageFormat;

public class CommandRegisterImpl implements ICommand {
    @Override
    public String process(String[] Arr) {
        User user = new User();
        user.setRole(Arr[1]);
        user.setName(Arr[2]);
        user.setPassword(Arr[3]);
        UserDao.addUser(user);
        return MessageFormat.format(Constant.SUCCESSFULLY_REGISTER,user.getRole(),user.getName());
    }
}
