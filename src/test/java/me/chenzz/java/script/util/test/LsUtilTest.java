package me.chenzz.java.script.util.test;

import me.chenzz.java.script.util.LsUtil;
import org.junit.Test;

import java.util.List;

/**
 * @author chenzhongzheng
 * @since 2024/02/16
 */
public class LsUtilTest {

    @Test
    public void testFindFilesRecursivelyByWildcard() {
        List<String> pathList = LsUtil.listFilesByModificationTime("~/Library/Containers/com.coderforart.MWeb3/Data/Library/Application Support/MWebLibrary/docs");

        for (String path : pathList) {
            System.out.println(path);
        }
    }



}
