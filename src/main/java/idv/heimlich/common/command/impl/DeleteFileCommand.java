package idv.heimlich.common.command.impl;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import idv.heimlich.common.command.Command;
import idv.heimlich.common.exeception.ApBusinessException;

/**
 * 刪除檔案命令
 */
public class DeleteFileCommand implements Command {

	private static Logger LOGGER = LoggerFactory.getLogger(DeleteFileCommand.class);
	private File file;
	
	public DeleteFileCommand(final File file) {
		super();
		this.file = file;
	}
	
	@Override
	public void execute() {
		if (!this.file.exists()) {
			LOGGER.error("檔案不存在，無法刪除檔案");
		} else {
			this.file.delete();
			LOGGER.debug("刪除完成{}", this.file.getPath());
		}
	}

	@Override
	public void unio() {
		throw new ApBusinessException("尚未實作檔案還原機制");
	}

}
