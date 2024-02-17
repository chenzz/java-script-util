package me.chenzz.java.script.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @author chenzhongzheng
 * @date 2024/02/16
 */
public class AssertUtil {

    public static void notEmpty(String str, String varName) {
        if (StringUtils.isEmpty(str)) {
            throw new RuntimeException(varName + " should not is empty");
        }
    }
}
