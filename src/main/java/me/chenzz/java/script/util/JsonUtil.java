package me.chenzz.java.script.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

/**
 * JSON工具类
 *
 * @author chenzhongzheng
 * @since 2024/02/17
 */
public class JsonUtil {

    private static final ObjectMapper mapper = new ObjectMapper();       // create once, reuse

    /**
     * 序列化
     * @param object object
     * @return 序列化的String
     */
    @SneakyThrows
    public static String toString(Object object) {
        return mapper.writeValueAsString(object);
    }
}
