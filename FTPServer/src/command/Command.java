package command;

/**
 * ����ӿڣ����������Ҫʵ�ִ˽ӿڡ�
 * 
 * @author Encore
 *
 */
public interface Command {

	public void setCommand(String str);

	public boolean judgeCommand();

	public String excute();
}
