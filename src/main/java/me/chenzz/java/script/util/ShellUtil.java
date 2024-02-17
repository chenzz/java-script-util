package me.chenzz.java.script.util;

import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Shell 工具类
 *
 * @author chenzhongzheng
 * @since 2024/02/16
 */
public class ShellUtil {

    private static String path = null;
    private static String[] envArr = null;

    private static final Map<String, String> envMap = new HashMap<>();


    /**
     * 配置环境变量
     * @param envArrOfExplicitSpecify 环境变量
     */
    public static void configEnvp(String[] envArrOfExplicitSpecify) {
        String output = execAndGetStdOutput("env");
        String[] envArrOfOrigin = output.split("\n");

        List<String> mergedEnvList = new ArrayList<>(Arrays.asList(envArrOfOrigin));
        mergedEnvList.addAll(Arrays.asList(envArrOfExplicitSpecify));

        ShellUtil.envArr = mergedEnvList.toArray(new String[0]);

        for (String env : envArr) {
            String[] kvArr = env.split("=");
            envMap.put(kvArr[0], kvArr[1]);
        }
    }

    /**
     * 配置执行路径
     * @param path 路径
     */
    public static void configPath(String path) {
        ShellUtil.path = path;
    }

    /**
     * 执行命令
     * @param cmd 命令
     */
    public static void exec(String cmd) {
        exec(cmd, StdOutputStrategyEnum.PRINT);
    }

    /**
     * 执行命令
     * @param cmdArr 命令Array
     */
    public static void exec(String... cmdArr) {
        exec(cmdArr, StdOutputStrategyEnum.PRINT);
    }


    /**
     * 执行命令，并且返回执行结果
     * @param cmd 命令
     * @return 执行结果
     */
    public static String execAndGetStdOutput(String cmd) {
        return exec(cmd, StdOutputStrategyEnum.RETURN);
    }

    /**
     * 执行命令，并且返回执行结果
     * @param cmdArr 命令
     * @return 执行结果
     */
    public static String execAndGetStdOutput(String... cmdArr) {
        return exec(cmdArr, StdOutputStrategyEnum.RETURN);
    }

    @SneakyThrows
    private static String exec(String cmd, StdOutputStrategyEnum stdOutputStrategyEnum) {
        return exec(new String[] {cmd}, stdOutputStrategyEnum);
    }

    @SneakyThrows
    private static String exec(String[] cmdArr, StdOutputStrategyEnum stdOutputStrategyEnum) {
        if (null == cmdArr || 0 == cmdArr.length) {
            throw new RuntimeException("cmdArr should not null");
        }

        System.out.println("============= 执行命令 开始： " + Arrays.toString(cmdArr));

        // 1. 预处理
        if (null == stdOutputStrategyEnum) {
            stdOutputStrategyEnum = StdOutputStrategyEnum.PRINT;
        }

        // 2. path处理
        File file = null;
        if (StringUtils.isNotEmpty(path)) {
            file = new File(path);
        }

        // 3. 执行
        Process process = null;
        if (1 == cmdArr.length) {
            process = Runtime.getRuntime().exec(cmdArr[0], envArr, file);
        } else {
            process = Runtime.getRuntime().exec(cmdArr, envArr, file);
        }


        // 4. 结果处理
        // 标准输出

        String normalOutput = null;
        if (StdOutputStrategyEnum.PRINT == stdOutputStrategyEnum) {
            // 打印标准输出
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } else {
            // 返回
            normalOutput = IOUtils.toString(process.getInputStream());
        }

        // 错误输出
        // 退出码
        int exitCode = process.waitFor();

        if (0 != exitCode) {
            System.out.println("=== 程序退出码");
            System.out.println("exitCode=" + exitCode);
        }

        String errorOutput = IOUtils.toString(process.getErrorStream());
        if (StringUtils.isNotEmpty(errorOutput)) {
            System.out.println("=== 注意：错误输出");
            System.out.println(errorOutput);
        }

        System.out.println("============= 执行命令 结束");
        System.out.println();

        return normalOutput;
    }

    enum StdOutputStrategyEnum {
        PRINT, RETURN
    }
}
