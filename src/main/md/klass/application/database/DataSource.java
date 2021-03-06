package md.klass.application.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {
  private static HikariConfig config = new HikariConfig();
  private static HikariDataSource ds;

  static {
      config.setJdbcUrl("jdbc:mysql://localhost:3306/YourNotepad?allowPublicKeyRetrieval=true&useSSL=false");
      config.setUsername("root");
      config.setPassword("root");
      config.addDataSourceProperty("cachePrepStmts", "true");
      config.addDataSourceProperty("prepStmtCacheSize", "250");
      config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
      ds = new HikariDataSource(config);
      config.addDataSourceProperty("autoCommit", false);
  }

  private DataSource() {}

  public static Connection getConnection() throws SQLException {
    return ds.getConnection();
  }
}
