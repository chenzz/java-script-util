package me.chenzz.java.script.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author chenzhongzheng
 * @date 2024/02/16
 */
public class RegexUtil {

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
