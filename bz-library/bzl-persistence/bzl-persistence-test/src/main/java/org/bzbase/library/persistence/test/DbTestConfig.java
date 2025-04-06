package org.bzbase.library.persistence.test;

import lombok.Data;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 数据库测试配置
 *
 * @author legendjw
 */
@Data
public class DbTestConfig {
    /**
     * 数据库测试环境
     */
    private DbTestEnv testEnv;

    /**
     * 数据库厂商名称
     */
    private DbVendor testVendor;

    /**
     * 数据库版本
     */
    private String testVersion;

    /**
     * 数据库驱动类名
     */
    private String driverClassName;

    /**
     * 数据库连接 jdbc url
     */
    private String url;

    /**
     * 数据库用户名
     */
    private String username;

    /**
     * 数据库密码
     */
    private String password;

    /**
     * 根据资源属性文件加载数据库测试配置
     *
     * @param classLoader 类加载器
     * @param fileName 属性文件名
     * @return 数据库测试配置
     */
    public static DbTestConfig ofResourceProperties(ClassLoader classLoader, String fileName) {
        Properties properties = new Properties();
        try (InputStream input = classLoader.getResourceAsStream(fileName)) {
            properties.load(input);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        DbTestConfig dbTestConfig = new DbTestConfig();
        dbTestConfig.setTestEnv(DbTestEnv.valueOf(properties.getProperty("db.test.env").toUpperCase()));
        dbTestConfig.setTestVendor(DbVendor.valueOf(properties.getProperty("db.test.vendor").toUpperCase()));
        dbTestConfig.setTestVersion(properties.getProperty("db.test.version"));
        dbTestConfig.setDriverClassName(properties.getProperty("db.driverClassName"));
        dbTestConfig.setUrl(properties.getProperty("db.url"));
        dbTestConfig.setUsername(properties.getProperty("db.username"));
        dbTestConfig.setPassword(properties.getProperty("db.password"));
        return dbTestConfig;
    }

    /**
     * 是否使用容器作为数据库测试环境
     *
     * @return 使用容器返回 true，反之 false
     */
    public boolean isUsingContainerAsTestEnv() {
        return testEnv.equals(DbTestEnv.CONTAINER);
    }

    /**
     * 是否使用本地数据库作为数据库测试环境
     *
     * @return 使用本地数据库返回 true，反之 false
     */
    public boolean isUsingLocalAsTestEnv() {
        return testEnv.equals(DbTestEnv.LOCAL);
    }
}
