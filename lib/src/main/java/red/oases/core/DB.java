package red.oases.core;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.jetbrains.annotations.Nullable;
import red.oases.core.Extra.Records.DatabaseConfig;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * <b>DB</b> - 数据库连接的相关基础设计。
 */
public class DB {
    // Hikari 提供的 DataSource，使用 <code>HikariConfig</code> 构建，用于获取新的链接
    public static HikariDataSource dataSource;

    /**
     * 初始化数据库设置
     * @param cfg 数据库配置对象
     */
    public static void init(DatabaseConfig cfg) {
        var config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://%s:%s/%s".formatted(
                cfg.host(),
                cfg.port(),
                cfg.database()
        ));
        config.setUsername(cfg.username());
        config.setPassword(cfg.password());
        config.setMaximumPoolSize(cfg.maxPoolSize());
        dataSource = new HikariDataSource(config);
    }

    /**
     * 获取数据库连接。请务必记得关闭数据库连接。
     * @return 新的数据库连接
     */
    public static @Nullable Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            Logs.severe(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * 关闭数据源。数据源关闭后，相应的连接池以及池内的连接会立即关闭并释放。
     * <b>此方法必须在 <code>onDisabled</code> 或其它类似上下文中调用，
     * 以避免资源未释放和未重复利用造成的数据库连接溢出。</b>
     */
    public static void close() {
        dataSource.close();
    }

    /**
     * 判断数据源是否被关闭。
     * @return 是否被关闭
     */
    public static boolean isClosed() {
        return dataSource.isClosed();
    }
}
