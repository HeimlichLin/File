package idv.heimlich.common.file;

import idv.heimlich.common.fileCommand.FileCommand;
import idv.heimlich.common.fileCommand.FileCommandFactory;

import java.io.File;
import java.util.Arrays;

public class FileCommandTest {
	
	public static void main(final String[] args) {
		FileCommandTest.TestAuto();
		FileCommandTest.TestLazy();
	}
	
	public static void TestAuto() {
		final FileCommandFactory fileCommandFactory = new FileCommandFactory();
		final FileCommand fileCommand = fileCommandFactory.getCommonFileCommand();
		try {
			fileCommand.createFile(new File("/TestFile/xxx.txt.flg"), Arrays.asList("flg"));
			fileCommand.createFile(new File("/TestFile/test1.txt"), Arrays.asList("content"));
			fileCommand.delete(new File("/TestFile/xxx.txt.flg"));
			fileCommand.renameTo(new File("/TestFile/test1.txt"), new File("/TestFile/test2.txt"));
		} catch (final Exception e) {
			fileCommand.rollBack();
		}

	}

	public static void TestLazy() {
		final FileCommandFactory fileCommandFactory = new FileCommandFactory();
		final FileCommand fileCommand = fileCommandFactory.getLazyFileCommand();
		try {
			fileCommand.createFile(new File("/TestFile/xxx.txt.flg"), Arrays.asList("flg"));
			fileCommand.createFile(new File("/TestFile/test1.txt"), Arrays.asList("content"));
			fileCommand.delete(new File("/TestFile/xxx.txt.flg"));
			fileCommand.renameTo(new File("/TestFile/test1.txt"), new File("/TestFile/test2.txt"));
			fileCommand.commit();
		} catch (final Exception e) {
			fileCommand.rollBack();
		}

	}

}
