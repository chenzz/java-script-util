package me.chenzz.java.script.util.test;

import me.chenzz.java.script.util.MdFindUtil;
import org.junit.Test;

import java.util.List;

/**
 * @author chenzhongzheng
 * @since 2024/02/16
 */
public class MdFindUtilTest {

    @Test
    public void testFindFileListContainsKeyword() {
        String rootDir = "/Users/bytedance/Library/Containers/com.coderforart.MWeb3/Data/Library/Application Support/MWebLibrary/docs";
        List<String> filePathList = MdFindUtil.findFileListContainsKeyword(rootDir, "计划笔记");

        System.out.println(filePathList);
    }


}
