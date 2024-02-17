package me.chenzz.java.script.util.test;

import me.chenzz.java.script.util.FileUtil;
import org.junit.Test;

import java.nio.file.Path;
import java.util.List;

/**
 * @author chenzhongzheng
 * @date 2024/02/16
 */
public class FileUtilTest {

    @Test
    public void testFindFilesRecursivelyByWildcard() {
        String path = "/Users/bytedance/IdeaProjects/work/rocket-boot";
        String pattern = "pom.xml";
        List<Path> filesRecursivelyByWildcard = FileUtil.findFilesRecursivelyByWildcard(path, pattern);

        for (Path file : filesRecursivelyByWildcard) {
            System.out.println(file.toString());
        }
    }


}
