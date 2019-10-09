package idv.heimlich.common.fileCommand;

import idv.heimlich.common.fileCommand.impl.CommonFileCommand;
import idv.heimlich.common.fileCommand.impl.LazyFileCommand;

/**
 * 檔案處理工廠
 */
public class FileCommandFactory {
	
	private static ThreadLocal<FileCommand> FILECOMMANDLOCAL = new ThreadLocal<FileCommand>();
	
	/**
	 * 一般檔案操作
	 */
	public FileCommand getCommonFileCommand() {
		FileCommand fileCommand = FILECOMMANDLOCAL.get();
		if (fileCommand == null) {
			fileCommand = new CommonFileCommand();
			FILECOMMANDLOCAL.set(fileCommand);
		}
		return fileCommand;
	}
	
	/**
	 * 檔案延遲操作
	 */
	public FileCommand getLazyFileCommand() {
		FileCommand fileCommand = FILECOMMANDLOCAL.get();
		if (fileCommand == null) {
			fileCommand = new LazyFileCommand();
			FILECOMMANDLOCAL.set(fileCommand);
		}
		return fileCommand;
	}


}
