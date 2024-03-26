package lms;


import lms.service.ICommand;
import lms.service.implement.CommandRegisterImpl;
import lms.util.Command;
import lms.util.Constant;

import java.util.*;

public class LibraryManagementSystemApplication {

    private static final Map<String, ICommand> commandHandler = new HashMap<>();
    private static final Set<String> command = new HashSet<>();
    static {
        for (Command commandSingle:Command.values()){
            command.add(commandSingle.toString().toLowerCase());
        }
        commandHandler.put("register",new CommandRegisterImpl());
    }


    public static void main(String[] args) {

        boolean flag = true;
        Scanner scan = new Scanner(System.in);
        System.out.println("================welcome to library management system================");
        while(flag){
            System.out.println("please input your command below：");
            // 判断是否还有输入
            if (scan.hasNextLine()) {
                // nextLine方式接收字符串
                String str = scan.nextLine();
                System.out.println("input command：" + str);
                String[] arr = str.split(Constant.COMMAND_SPLIT);
                if (Constant.COMMAND_EXIT.equals(arr[0])) {
                    flag = false;
                    continue;
                }
                if (!command.contains(arr[0])){
                    System.out.println("so far, do not support this command：" + arr[0]);
                    continue;
                }else{
                    ICommand handler = commandHandler.get(arr[0]);
                    String msg = handler.process(arr);
                    System.out.println(msg);
                }
            }
        }
        scan.close();


    }



}
