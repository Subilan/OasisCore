package red.oases.core.Extra.Records;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <b>DatabaseConfig</b> - 数据库相关配置
 *
 * @param host        必填，数据库地址
 * @param port        选填，数据库端口，默认为 3306
 * @param username    必填，数据库用户名
 * @param password    必填，数据库密码
 * @param database    必填，数据库名称
 * @param maxPoolSize 选填，连接池的最大连接数，默认为 20
 */
public record DatabaseConfig(
        @NotNull String host,
        @Nullable Integer port,
        @NotNull String username,
        @NotNull String password,
        @NotNull String database,
        @Nullable Integer maxPoolSize) {

    public Integer port() {
        return port == null ? 3306 : port;
    }

    public Integer maxPoolSize() {
        return maxPoolSize == null ? 20 : maxPoolSize;
    }
}
