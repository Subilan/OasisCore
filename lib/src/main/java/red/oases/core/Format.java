package red.oases.core;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.Plugin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * <b>Format</b> - 字符串处理、类型转换和文本组件构建，以及相关判断
 */
public class Format {

    /**
     * 转换对应字符串为正整数。转换失败则抛出 <code>NumberFormatException</code>
     *
     * @param target 待转换的字符串
     * @return 对应正整数。
     */
    public static int mustPositiveInt(String target) throws NumberFormatException {
        return Integer.parseUnsignedInt(target);
    }

    /**
     * 转换对应字符串为整数。转换失败则抛出 <code>NumberFormatException</code>。
     *
     * @param target 待转换的字符串
     * @return 对应整数。
     */
    public static int mustInt(String target) throws NumberFormatException {
        return Integer.parseInt(target);
    }

    public static String timestamp(long epoch) {
        return date(new Date(epoch));
    }

    public static String timestamp(int epoch) {
        return date(new Date(epoch));
    }

    /**
     * 将指定日期转换为标准化字符串，默认格式为 <code>yyyy-MM-dd HH:mm:ss</code>。
     *
     * @param date 指定日期
     * @return 标准化字符串
     */
    public static String date(Date date) {
        return date(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 使用指定的格式将指定日期转换为标准化字符串。
     * @param date 指定日期
     * @param pattern 指定的格斯
     * @return 标准化字符串
     */
    public static String date(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 创建一个空的文本组件，相当于 <code>Component.empty()</code>
     * @return 空的文本组件
     */
    public static Component t() {
        return Component.empty();
    }

    /**
     * 生成一个文本组件
     * @param text 纯文本
     * @param color 颜色，一般使用 <code>NamedTextColor.*</code>
     * @param dec 装饰，一般使用 <code>TextDecoration.*</code>
     * @return 修饰后的文本组件
     */
    public static Component t(String text, TextColor color, TextDecoration dec) {
        return t(text, color).decorate(dec);
    }

    /**
     * 生成一个文本组件
     * @param text 纯文本
     * @param color 颜色，一般使用 <code>NamedTextColor.*</code>
     * @return 修饰后的文本组件
     */
    public static Component t(String text, TextColor color) {
        return t(text).color(color);
    }

    /**
     * 生成一个文本组件。此方法返回的组件默认去除了斜体，并强制为白色。
     * @param text 纯文本
     * @return 处于默认样式的文本组件
     */
    public static Component t(String text) {
        return Component.text(text).decorations(Map.of(
                TextDecoration.ITALIC,
                TextDecoration.State.FALSE
        )).color(NamedTextColor.WHITE);
    }

    /**
     * 将对应迷你消息翻译为文本组件
     * @param mini 遵从迷你消息语法的字符串
     * @return 翻译后的文本组件
     */
    public static Component tt(String mini) {
        return MiniMessage.miniMessage().deserialize(mini);
    }

    /**
     * 去除对应迷你消息中的语法标签，仅保留纯文本
     * @param minied 遵从迷你消息语法的字符串
     * @return 去除标签后的纯文本字符串
     */
    public static String stripTags(String minied) {
        return MiniMessage.miniMessage().stripTags(minied);
    }

    /**
     * 返回由插件实例构建的命名空间独特键。一般用于 ItemStack、Entity 等的持久数据存储的相关标识。
     * @param key 键名
     * @param plugin 插件实例
     */
    public static NamespacedKey key(String key, Plugin plugin) {
        return NamespacedKey.fromString(key, plugin);
    }

    /**
     * 返回由注册的插件实例构建的命名空间独特键。一般用于 ItemStack、Entity 等的持久数据存储的相关标识。
     * @param key 键名
     */
    public static NamespacedKey key(String key) {
        return key(key, Oasis.plugin);
    }
}
