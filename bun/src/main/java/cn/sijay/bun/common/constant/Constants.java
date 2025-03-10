package cn.sijay.bun.common.constant;

/**
 * <strong>Constants</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
public interface Constants {

    String BLANK = "";
    String DOT = ".";
    String DOT_CN = "点";
    String SPACE = " ";
    String UNDERLINE = "_";

    String OR = "|";
    String AND = "&";

    String STAR = "*";
    String SLASH = "/";
    String POUND = "#";
    String BACKSLASH = "\\";
    String TILDE = "~";
    String COMMA = ",";
    String COLON = ":";
    String QUESTION = "?";
    String SEMICOLON = ";";
    String EXCLAMATION = "!";
    String HYPHEN = "-";

    String MINUS = "-";
    String PLUS = "+";
    String MULTIPLIED = "×";
    String DIVIDED = "÷";
    String EQUAL = "＝";
    String NOT_EQUAL = "≠";
    String LT = "＜";
    String GT = "＞";
    String LE = "≤";
    String GE = "≥";
    String PER_CENT = "%";
    String PER_MILL = "‰";
    String SQUARE = "√";
    String SINCE = "∵";
    String HENCE = "∴";
    String PI = "π";
    String DEGREE = "°";

    String CELSIUS = "℃";

    String SUCCESS = "SUCCESS";
    String ERROR = "ERROR";
    String WARNING = "WARNING";

    String CONTENT_TYPE = "Content-Type";

    String WWW = "www.";
    String HTTP = "http://";
    String HTTPS = "https://";

    String UTF8 = "UTF-8";

    String SUPER_ADMIN_NAME = "su";
    Long SUPER_ADMIN_ID = 1L;

    String LOGIN_USER_KEY = "loginUser";
    String USER_ID = "userId";
    String DEPT_ID = "deptId";
    String MODULE_ID = "moduleId";

    String WEB_SOCKET_TOPIC = "global:websocket";
    String PING = "ping";
    String PONG = "pong";

    /**
     * redis key前缀
     */
    String REDIS_PREFIX_TRANS = "trans:";
}
