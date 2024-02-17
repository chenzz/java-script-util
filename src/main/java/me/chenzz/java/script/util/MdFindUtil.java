package me.chenzz.java.script.util;

import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * MdFind工具类
 * @author chenzhongzheng
 * @since 2024/02/17
 */
public class MdFindUtil {

    /**
     * 根据关键字查找文件
     * @param rootDir 跟路径
     * @param keyword 关键字
     * @return 文件列表
     */
    @SuppressWarnings("UnnecessaryLocalVariable")
    @NotNull
    public static List<String> findFileListContainsKeyword(String rootDir, String keyword) {
        String output = ShellUtil.execAndGetStdOutput("mdfind", "-onlyin", rootDir, keyword);

        if (StringUtils.isEmpty(output)) {
            return Collections.emptyList();
        }

        List<String> filePathList = Arrays.asList(output.split("\n"));
        return filePathList;
    }
}
