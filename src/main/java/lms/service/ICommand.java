package lms.service;

public interface ICommand {

    public String process(String command,String commandLine) throws Exception;

}
