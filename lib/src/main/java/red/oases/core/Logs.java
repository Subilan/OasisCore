package red.oases.core;

import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <b>Logs</b> - 控制台和服务器内信息的发送
 */
public class Logs {
    public static Logger logger;

    public static void init(Logger logger) {
        Logs.logger = logger;
    }

    public static void severe(String msg) {
        logger.log(Level.SEVERE, msg);
    }

    public static void warn(String msg) {
        logger.log(Level.WARNING, msg);
    }

    public static void info(String msg) {
        logger.log(Level.INFO, msg);
    }

    public static Component withPrefix(Component comp) {
        return Oasis.displayname
                .append(comp);
    }

    /**
     * 向 sender 发送一个带前缀的文本组件
     *
     * @param sender 发送对象
     * @param comp   文本组件
     */
    public static void send(CommandSender sender, Component comp) {
        sender.sendMessage(
                withPrefix(comp)
        );
    }

    /**
     * 向 sender 发送一个带前缀的纯文本构建成的文本组件，支持迷你消息语法
     *
     * @param sender 发送对象
     * @param text 纯文本，支持迷你消息语法
     */
    public static void send(CommandSender sender, String text) {
        send(sender, withPrefix(Format.tt(text)));
    }
}
