package me.chenzz.java.script.util;

import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author chenzhongzheng
 * @date 2024/02/16
 */
public class ShellUtil {

    private static String path = null;
    private static String[] envArr = null;

    private static final Map<String, String> envMap = new HashMap<>();


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

    public static void configPath(String path) {
        ShellUtil.path = path;
    }

    public static void exec(String cmd) {
        exec(cmd, StdOutputStrategyEnum.PRINT);
    }

    public static String execAndGetStdOutput(String cmd) {
        return exec(cmd, StdOutputStrategyEnum.RETURN);
    }

    @SuppressWarnings("deprecation")
    @SneakyThrows
    private static String exec(String cmd, StdOutputStrategyEnum stdOutputStrategyEnum) {

        // 0. 预处理
        if (null == stdOutputStrategyEnum) {
            stdOutputStrategyEnum = StdOutputStrategyEnum.PRINT;
        }


        System.out.println("============= 执行命令 开始： " + cmd );

        // 1. cmd处理
        String javaHome;
        if (null != envMap.get("JAVA_HOME")) {
            javaHome = envMap.get("JAVA_HOME");
            if (cmd.startsWith("java ")) {
                cmd = javaHome + "/bin/" + cmd;
            }
        }

        // 2. path处理
        File file = null;
        if (StringUtils.isNotEmpty(path)) {
            file = new File(path);
        }

        // 3. 执行
        Process process = Runtime.getRuntime().exec(cmd, envArr, file);

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
