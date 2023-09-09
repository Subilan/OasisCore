package red.oases.core;

import org.apache.commons.dbutils.DbUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Function;

/**
 * <b>SQL</b> - 数据库内语句的执行和数据获取
 */
public class SQL {

    /**
     * 执行 SQL 语句
     * @param sql 语句
     * @return 执行成功，返回 true
     */
    public static boolean execute(String sql) {
        var conn = DB.getConnection();
        if (conn == null) return false;
        try (var st = conn.createStatement()) {
            st.execute(sql);
            return true;
        } catch (SQLException e) {
            Logs.severe(e.getMessage());
            return false;
        } finally {
            DbUtils.closeQuietly(conn);
        }
    }

    /**
     * 执行 SQL 语句，并在回调中进行处理。
     * @param sql 语句
     * @param handle 处理函数
     * @return 当处理函数有返回值时，返回相关返回值。
     * @param <T> 处理函数的返回值类型
     */
    public static <T> T withResult(String sql, Function<ResultSet, T> handle) {
        var conn = DB.getConnection();
        if (conn == null) throw new RuntimeException("Cannot get connection in method `withResult`.");
        try (var st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            var result = st.executeQuery(sql);
            return handle.apply(result);
        } catch (SQLException e) {
            Logs.severe(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            DbUtils.closeQuietly(conn);
        }
    }

    /**
     * 对指定表中的指定列进行自增操作
     * @param table 指定表
     * @param column 指定列
     * @param whereCondition 对表中列的筛选条件
     * @return 执行成功，返回 true
     */
    public static boolean increment(String table, String column, String whereCondition) {
        return withResult("SELECT %s FROM %s WHERE %s".formatted(column, table, whereCondition), r -> {
            try {
                while (r.next()) {
                    var currentVal = r.getInt(column);
                    if (!execute("UPDATE %s SET %s=%s".formatted(table, column, currentVal + 1))) return false;
                }
                return true;
            } catch (SQLException e) {
                Logs.severe(e.getMessage());
                return false;
            }
        });
    }

    /**
     * 判断给出的 SQL 语句是否有执行结果
     * @param sql 语句
     * @return 如果有结果，返回 true
     */
    public static boolean hasResult(String sql) {
        return withResult(sql, s -> {
            try {
                return s.next();
            } catch (SQLException e) {
                Logs.severe("Cannot determine if the result is present.");
                Logs.severe(e.getMessage());
                return false;
            }
        });
    }
}
