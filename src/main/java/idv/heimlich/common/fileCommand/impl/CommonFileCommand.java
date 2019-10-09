package idv.heimlich.common.fileCommand.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import idv.heimlich.common.command.Command;
import idv.heimlich.common.command.impl.CreateFileCommand;
import idv.heimlich.common.command.impl.DeleteFileCommand;
import idv.heimlich.common.command.impl.MoveFileCommand;
import idv.heimlich.common.fileCommand.FileCommand;

/**
 * 一般檔案操作
 */
public class CommonFileCommand implements FileCommand {
	
	private List<Command> commands = new ArrayList<Command>();

	/**
	 * 檔案建立
	 */
	@Override
	public void createFile(File file, List<String> contents) {
		this.addCommandAndExecute(new CreateFileCommand(file, contents));
	}

	/**
	 * 檔案刪除
	 */
	@Override
	public void delete(File file) {
		this.addCommandAndExecute(new DeleteFileCommand(file));
	}

	/**
	 * 檔案搬移
	 */
	@Override
	public void renameTo(File src, File tartge) {
		this.addCommandAndExecute(new MoveFileCommand(src, tartge));
	}

	/**
	 * 檔案提交
	 */
	@Override
	public void commit() {
		this.cleanCommand();
	}

	/**
	 * 檔案還原
	 */
	@Override
	public void rollBack() {
		Collections.reverse(this.commands);
		for (final Command command : this.commands) {
			command.unio();
		}
		this.cleanCommand();
	}
	
	private void addCommandAndExecute(final Command command) {
		this.commands.add(command);
		command.execute();
	}

	private void cleanCommand() {
		this.commands.clear();
	}
	
}
