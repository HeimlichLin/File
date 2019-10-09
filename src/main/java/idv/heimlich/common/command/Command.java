package idv.heimlich.common.command;


public interface Command {
	
	/**
	 * 執行
	 */
	void execute();

	/**
	 * 還原
	 */
	void unio();

}
