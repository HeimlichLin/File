package idv.heimlich.common.command.impl;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import idv.heimlich.common.command.Command;
import idv.heimlich.common.exeception.ApBusinessException;

/**
 * 移動檔案命令
 */
public class MoveFileCommand implements Command {

	private static Logger LOGGER = LoggerFactory.getLogger(MoveFileCommand.class);
	private boolean isAutocover = true;// 自動建立相關資料夾位置
	private File src;
	private File targe;
	
	public MoveFileCommand(final File src, final File targe) {
		super();
		this.src = src;
		this.targe = targe;
	}
	
	@Override
	public void execute() {
		if (!this.src.exists()) {
			LOGGER.error("檔案不存在因此不移動檔案:{}", this.src.getPath());
			return;
		}
		if (!this.targe.getParentFile().exists()) {
			LOGGER.error("目的地檔案位置不存在:{}建立ParentFile", this.targe.getParentFile());
			this.targe.getParentFile().mkdirs();
		}
		if (this.isAutocover) {
			this.autoDelteTargeFile();
		}

		LOGGER.info("檔案從{}=>{}", this.src.getAbsolutePath(),//
				this.targe.getAbsolutePath());
		if (!this.src.renameTo(this.targe)) {
			throw new ApBusinessException("檔案移動失敗");

		}
	}

	@Override
	public void unio() {
		LOGGER.info(String.format("檔案名稱[%s]，" //
				+ "開始移動檔案至%s",//
				this.targe.getAbsolutePath(), //
				this.src.getAbsolutePath())); //
		if (!this.targe.renameTo(this.src)) {
			throw new ApBusinessException("檔案移動失敗");
		}
	}
	
	private void autoDelteTargeFile() {
		if (this.targe.exists()) {
			this.targe.delete();
		}
	}

}
