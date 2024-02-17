package me.chenzz.java.script.util.test;

import me.chenzz.java.script.util.ShellUtil;
import org.junit.Test;

/**
 * @author chenzhongzheng
 * @since 2024/02/16
 */
public class ShellUtilTest {

    @Test
    public void testExec() {
        ShellUtil.exec("ls");
    }

    @Test
    public void testExec2() {
        ShellUtil.exec("ls", "/Users/bytedance/Library/Containers/com.coderforart.MWeb3/Data/Library/Application Support/MWebLibrary/docs");
    }

    @Test
    public void testExecAndGetStdOutput() {
        String result = ShellUtil.execAndGetStdOutput("ls", "/Users/bytedance/Library/Containers/com.coderforart.MWeb3/Data/Library/Application Support/MWebLibrary/docs");
        System.out.println("result:");
        System.out.println(result);
    }
}
