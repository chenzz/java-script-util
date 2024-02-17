package me.chenzz.java.script.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则工具类
 *
 * @author chenzhongzheng
 * @since 2024/02/16
 */
public class RegexUtil {

    /**
     * 根据正则解析变量
     * @param str 字符串
     * @param regex 正则
     * @return 结果
     */
    public static String extractStr(String str, String regex) {
        AssertUtil.notEmpty(str, "str");

        AssertUtil.notEmpty(regex, "regex");

        // 创建 Pattern 对象
        Pattern r = Pattern.compile(regex);

        // 现在创建 matcher 对象
        Matcher m = r.matcher(str);
        if (m.find()) {
            return m.group(1);
        } else {
            return null;
        }
    }

    /**
     * 根据正则进行替换
     * @param str 字符串
     * @param regex 正则
     * @param replacement 替换正则
     * @return 替换之后的结果
     */
    public static String replace(String str, String regex, String replacement) {
        AssertUtil.notEmpty(str, "str");

        AssertUtil.notEmpty(regex, "regex");

        // 创建 Pattern 对象
        Pattern r = Pattern.compile(regex);

        // 现在创建 matcher 对象
        Matcher m = r.matcher(str);
        if (m.find()) {
            return m.replaceFirst(replacement);
        } else {
            return null;
        }
    }
}
