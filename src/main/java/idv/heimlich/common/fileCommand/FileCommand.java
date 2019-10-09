package idv.heimlich.common.fileCommand;

import java.io.File;
import java.util.List;

/**
 * 定義檔案的操作行為
 */
public interface FileCommand {
	
	/**
	 * 產生檔案
	 * @param f
	 * @param content
	 */
	void createFile(File f, List<String> contents);

	/**
	 * 刪除
	 * @param f
	 */
	void delete(File f);

	/**
	 * 移動
	 * @param src
	 * @param tartge
	 */
	void renameTo(File src, File tartge);
	
	/**
	 * 提交
	 */
	void commit();

	/**
	 * 退回
	 */
	void rollBack();
	

	
}
