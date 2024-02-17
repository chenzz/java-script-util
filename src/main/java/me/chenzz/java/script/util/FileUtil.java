package me.chenzz.java.script.util;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * 文件工具类
 *
 * @author chenzhongzheng
 * @since 2024/02/16
 */
public class FileUtil {

    /**
     * 读取文件内容
     * @param filePath 文件路径
     * @return 文件内容
     */
    @SuppressWarnings({"UnnecessaryLocalVariable", "deprecation"})
    @SneakyThrows
    public static String readContent(String filePath) {
        String content = FileUtils.readFileToString(new File(filePath));
        return content;
    }

    /**
     * 读取文件第一行文字内容
     * @param filePath 文件路径
     * @return 第一行数据内容
     */
    @SuppressWarnings("UnnecessaryLocalVariable")
    @SneakyThrows
    public static String readFirstLine(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String firstLine = br.readLine(); // 读取第一行数据
            return firstLine;
        }
    }

    /**
     * 找到需要的一行内容
     * @param filePath 文件路径
     * @param keyword 关键字
     * @return 找到的数据行
     */
    @SneakyThrows
    public static String findOneLine(String filePath, String keyword) {
        AssertUtil.notEmpty(keyword, "keyword");

        String content = readContent(filePath);
        String[] lineArr = content.split("\\n");

        for (String line : lineArr) {
            if (null != line && line.contains(keyword)) {
                return line;
            }
        }

        return null;
    }

    /**
     * 替换文件内容
     * @param filePath 文件路径
     * @param originRegex 原始数据
     * @param replacedStr 替换的数据
     */
    @SuppressWarnings("deprecation")
    @SneakyThrows
    public static void replaceText(String filePath, String originRegex, String replacedStr) {
        String content = readContent(filePath);
        content = content.replaceAll(originRegex, replacedStr);
        FileUtils.writeStringToFile(new File(filePath), content);
    }


    /**
     * 根据通配符查找文件
     * @param rootDirPath 根路径
     * @param wildcardPattern 通配符
     * @return 文件路径列表
     */
    @SneakyThrows
    public static List<Path> findFilesRecursivelyByWildcard(String rootDirPath, String wildcardPattern) {
        Path startDir = Paths.get(rootDirPath);

        List<Path> resultList = new ArrayList<>();
        Files.walkFileTree(startDir, EnumSet.noneOf(FileVisitOption.class), Integer.MAX_VALUE, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:" + wildcardPattern);
                if (matcher.matches(file.getFileName())) {
                    resultList.add(file);
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) {
                // Handle file access errors here
                return FileVisitResult.CONTINUE;
            }
        });
        return resultList;
    }

}
