package org.bzbase.library.persistence.mybatisplus;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisSqlSessionFactoryBuilder;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

/**
 * mybatis plus 工具类
 *
 * @author legendjw
 */
public class MybatisPlusUtils {
    /**
     * 获取测试 SqlSessionFactory
     *
     * @param dbDriver 数据库驱动
     * @param dbUrl 数据库连接地址
     * @param dbUsername 数据库用户名
     * @param dbPassword 数据库密码
     * @param mapperPackageName 扫描的 mapper 包名
     * @return SqlSessionFactory
     */
    public static SqlSessionFactory getTestSqlSessionFactory(
            String dbDriver, String dbUrl, String dbUsername, String dbPassword, String mapperPackageName) {
        UnpooledDataSource dataSource = new UnpooledDataSource(dbDriver, dbUrl, dbUsername, dbPassword);
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("test", transactionFactory, dataSource);
        MybatisConfiguration configuration = new MybatisConfiguration(environment);
        configuration.addMappers(mapperPackageName);
        return new MybatisSqlSessionFactoryBuilder().build(configuration);
    }
}
