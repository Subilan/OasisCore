package red.oases.core;

import net.kyori.adventure.text.Component;
import org.bukkit.plugin.Plugin;

/**
 * 类 <b>Oasis</b><br/>
 * <p>OasisCore 的外部包装类</p>
 */
public class Oasis {
    public static Component displayname;
    public static Plugin plugin;

    /**
     * 在插件内部注册 OasisCore
     * @param displayname 插件在控制台中的对外显示文本组件
     * @param plugin 插件实例
     */
    public static void register(Component displayname, Plugin plugin) {
        Oasis.displayname = displayname;
        Oasis.plugin = plugin;
    }
}
