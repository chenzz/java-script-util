package me.chenzz.java.script.util;

/**
 * Git 工具类
 *
 * @author chenzhongzheng
 * @since 2024/02/16
 */
public class GitUtil {

    /**
     * push到feature分支
     */
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

    /**
     * 把当前分支 merge到目标分支
     *
     * @param targetBranch 目标分支名字
     */
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
