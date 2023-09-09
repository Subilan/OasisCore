package red.oases.core.Extra.Records;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public record DatabaseConfig(
        @NotNull  String host,
        @NotNull String port,
        @NotNull String username,
        @NotNull String password,
        @Nullable String database,
        @Nullable Integer maxPoolSize) {

    public Integer maxPoolSize() {
        return maxPoolSize == null ? 20 : maxPoolSize;
    }
}
