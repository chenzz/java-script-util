package me.chenzz.java.script.util.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chenzz.java.script.util.JsonUtil;
import org.junit.Test;

/**
 * @author chenzhongzheng
 * @since 2024/02/16
 */
public class JsonUtilTest {

    @Test
    public void testToString() {
        User user = new User("Jameson", "123", 18);

        String jsonString = JsonUtil.toString(user);

        System.out.println(jsonString);
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static
    class User {
        private String username;
        private String password;
        private Integer age;
    }


}
