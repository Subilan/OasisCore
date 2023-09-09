package red.oases.core;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.Map;

/**
 * <b>Files</b> - 本地普通文件、配置文件的读写和访问
 */
public class Files {
    /**
     * 插件数据文件夹，一般通过 <code>getDataFolder</code> 方法获得
     */
    public static File datafolder;
    /**
     * Files 所管理的所有文件对象的键值对
     */
    public static Map<String, File> f;
    /**
     * Files 所管理的所有配置对象的键值对
     */
    public static Map<String, FileConfiguration> c;

    /**
     * 在开始使用本类之前必须调用此函数进行初始化。
     * @param datafolder 数据文件夹对象
     */
    public static void init(File datafolder) {
        Files.datafolder = datafolder;
        loadFromFile();
    }

    /**
     * 添加并加载一个新的配置文件（YML 文件）。重复添加为覆盖。
     * @param key 配置文件名称
     */
    public static void addConfig(String key) {
        f.put(key, new File(datafolder.getAbsolutePath() + "/" + key + ".yml"));
    }

    /**
     * 从文件刷新 c 中所有项目的内容
     */
    public static void loadFromFile() {
        for (var file : f.keySet()) {
            loadFromFile(file);
        }
    }

    /**
     * 从文件刷新 c 中指定项目的内容
     * @param key 配置文件名称
     */
    public static void loadFromFile(String key) {
        c.put(key, YamlConfiguration.loadConfiguration(f.get(key)));
    }
}
