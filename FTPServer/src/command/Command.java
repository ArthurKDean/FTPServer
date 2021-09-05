package command;

/**
 * 命令接口，所有命令都需要实现此接口。
 * 
 * @author Encore
 *
 */
public interface Command {

	public void setCommand(String str);

	public boolean judgeCommand();

	public String excute();
}
