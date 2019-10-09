package idv.heimlich.common.fileCommand.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import idv.heimlich.common.command.Command;
import idv.heimlich.common.command.impl.CreateFileCommand;
import idv.heimlich.common.command.impl.DeleteFileCommand;
import idv.heimlich.common.command.impl.MoveFileCommand;
import idv.heimlich.common.exeception.ApBusinessException;
import idv.heimlich.common.fileCommand.FileCommand;

/**
 * 檔案延遲操作
 */
public class LazyFileCommand implements FileCommand {
	
	private static Logger LOGGER = LoggerFactory.getLogger(LazyFileCommand.class);
	private List<Command> commands = new ArrayList<Command>();
	final List<Command> sucessFullsCommands = new ArrayList<Command>();// 執行完commit清單
	private boolean isCommit;
	
	public LazyFileCommand() {
		this.isCommit = false;
	}

	@Override
	public void createFile(File file, List<String> content) {
		this.addCommand(new CreateFileCommand(file, content));
	}

	@Override
	public void delete(File file) {
		this.addCommand(new DeleteFileCommand(file));
	}

	@Override
	public void renameTo(File src, File tartge) {
		this.addCommand(new MoveFileCommand(src, tartge));
	}

	@Override
	public void commit() {
		if (this.isCommit) {
			throw new ApBusinessException("已經執行commit!");
		}
		try {

			for (final Command command : this.commands) {
				command.execute();
				this.sucessFullsCommands.add(command);
			}
		} catch (final Exception e) {
			LOGGER.debug("執行錯誤，呼叫rollback!", e);
		} finally {
			this.isCommit = true;
			this.cleanCommand();
		}
	}

	@Override
	public void rollBack() {
		Collections.reverse(this.sucessFullsCommands);
		for (final Command command : this.sucessFullsCommands) {
			command.unio();
		}
	}
	
	private void addCommand(final Command command) {
		this.commands.add(command);
	}
	
	private void cleanCommand() {
		this.commands.clear();
	}

}
