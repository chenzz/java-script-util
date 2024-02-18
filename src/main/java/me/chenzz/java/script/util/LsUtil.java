package me.chenzz.java.script.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 文件列表的工具类
 * @author chenzhongzheng
 * @since 2024/02/17
 */
public class LsUtil {

    /**
     * 某个文件夹下的所有文件按照倒序排列之后展示
     * @param folderPath 文件夹路径
     * @return 文件的绝对路径
     */
    public static List<String> listFilesByModificationTime(String folderPath) {
        folderPath = folderPath.replaceFirst("^~", System.getProperty("user.home"));

        List<String> result = new ArrayList<>();
        File folder = new File(folderPath);

        if (!folder.isDirectory()) {
            throw new IllegalArgumentException("Input path is not a directory.");
        }

        File[] files = folder.listFiles();
        if (files != null) {
            // 将文件按修改时间排序
            Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());

            // 将排序后的文件的绝对路径添加到结果列表中
            for (File file : files) {
                result.add(file.getAbsolutePath());
            }
        }

        return result;
    }
}
