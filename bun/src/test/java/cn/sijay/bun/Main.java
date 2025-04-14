package cn.sijay.bun;

/**
 * <strong>Main</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-11-06
 */
public class Main {

    public static void main(String[] args) {
        String s = """
            t('menu.path') aaa
            t('menu.path') bbb
            t('menu.path') ccc
            t('menu.path') ddd
            """;
        s = s.replace("t('menu.path')", "路径");
        System.out.println(s);
    }
}

