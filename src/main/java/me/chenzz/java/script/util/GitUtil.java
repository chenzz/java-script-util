package me.chenzz.java.script.util;

/**
 * @author chenzhongzheng
 * @date 2024/02/16
 */
public class GitUtil {

    public static void push2Feature() {
        String branchName = ShellUtil.execAndGetStdOutput("git rev-parse --abbrev-ref HEAD");
        if (!branchName.startsWith("feature")) {
            throw new RuntimeException("当前分支不是feature分支");
        }

        ShellUtil.exec("git pull");
        ShellUtil.exec("git add .");
        ShellUtil.exec("git commit -m \"升级版本\"");
        ShellUtil.exec("git push");
    }

    public static void mergeFeature2TargetBranch(String targetBranch) {
        String branchName = ShellUtil.execAndGetStdOutput("git rev-parse --abbrev-ref HEAD");
        if (!branchName.startsWith("feature")) {
            throw new RuntimeException("当前分支不是feature分支");
        }

        ShellUtil.exec("git pull");

        ShellUtil.exec("git checkout " + targetBranch);
        ShellUtil.exec("git pull");
        ShellUtil.exec("git merge " + branchName);
        ShellUtil.exec("git push");
        ShellUtil.exec("git checkout " + branchName);
    }

}
