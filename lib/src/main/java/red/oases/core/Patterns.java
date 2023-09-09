package red.oases.core;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Pattern;

/**
 * <b>Patterns</b> - 字符串相关验证和提取
 */
public class Patterns {

    /**
     * 判断给定字符串是否符合正则表达式
     * @param regex 正则表达式
     * @param like 给定字符串
     * @return 符合返回 true
     */
    public static boolean matches(Pattern regex, String like) {
        return regex.matcher(like).matches();
    }

    /**
     * 从字符串获取正则表达式编组匹配的各数据，并转换为指定类型
     * @param regex 正则表达式
     * @param like 字符串
     * @param builder 转换函数
     * @return 经过转换后的值的列表
     * @param <T> 转换后的值的类型
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static <T> List<T> getGroups(Pattern regex, String like, Function<String, T> builder) {
        var matcher = regex.matcher(like);
        var result = new ArrayList<T>();
        matcher.matches();
        for (var i = 1; i < matcher.groupCount(); i++) {
            result.add(builder.apply(matcher.group(i)));
        }
        return result;
    }

    /**
     * 从字符串获取正则表达式编组匹配的各数据
     * @param regex 正则表达式
     * @param like 字符串
     * @return 匹配的各个字符串部分
     */
    public static List<String> getGroups(Pattern regex, String like) {
        return getGroups(regex, like, s -> s);
    }

    /**
     * 从字符串获取正则表达式指定编组数据，并转换为指定类型
     * @param regex 正则表达式
     * @param like 指定字符串
     * @param index 序号（从 0 开始）
     * @param builder 转换函数
     * @return 经过转换后的值
     * @param <T> 转换后的值的类型
     */
    public static <T> T getGroup(Pattern regex, String like, int index, Function<String, T> builder) {
        return getGroups(regex, like, builder).get(index);
    }

    /**
     * 从字符串获取正则表达式指定编组数据
     * @param regex 正则表达式
     * @param like 指定字符串
     * @param index 序号（从 0 开始）
     * @return 编组字符串
     */
    public static String getGroup(Pattern regex, String like, int index) {
        return getGroups(regex, like).get(index);
    }
}
