package me.chenzz.java.script.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 断言工具类
 *
 * @author chenzhongzheng
 * @since 2024/02/16
 */
public class AssertUtil {

    /**
     * 确保不为空
     * @param str 字符串
     * @param varName 报错文字提示
     */
    public static void notEmpty(String str, String varName) {
        if (StringUtils.isEmpty(str)) {
            throw new RuntimeException(varName + " should not is empty");
        }
    }
}
