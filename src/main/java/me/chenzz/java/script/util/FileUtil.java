package me.chenzz.java.script.util;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * @author chenzhongzheng
 * @date 2024/02/16
 */
public class FileUtil {

    @SuppressWarnings({"UnnecessaryLocalVariable", "deprecation"})
    @SneakyThrows
    public static String readContent(String filePath) {
        String content = FileUtils.readFileToString(new File(filePath));
        return content;
    }

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

    @SuppressWarnings("deprecation")
    @SneakyThrows
    public static void replaceText(String filePath, String originRegex, String replacedStr) {
        String content = readContent(filePath);
        content = content.replaceAll(originRegex, replacedStr);
        FileUtils.writeStringToFile(new File(filePath), content);
    }


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
