/*
 * Ownership belongs to Sijay Zheng
 */

package sijay.zheng.z.app;

import org.slf4j.helpers.MessageFormatter;

import java.util.regex.Pattern;

/**
 * TODO
 *
 * @author zhengshijie
 * @date 2023/7/13 9:49
 */
public class Main {
    static Pattern pattern = Pattern.compile("\\{\\}");

    public static void main(String[] args) {
        String str = "Closing prepared statement [HikariProxyPreparedStatement@832244762 wrapping com.mysql.cj.jdbc.ClientPreparedStatement: select s1_0.user_id,s1_0.password,s1_0.user_name from sys_user s1_0 where s1_0.user_name='root']";
        System.out.println("SQL: [" + str.split("ClientPreparedStatement:")[1]);

    }

    static void formatter(String str, Object... args) {
        System.out.println(MessageFormatter.arrayFormat(str, args).getMessage());
    }
}
