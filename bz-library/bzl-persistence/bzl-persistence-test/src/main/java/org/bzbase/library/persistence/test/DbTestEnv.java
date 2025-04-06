package org.bzbase.library.persistence.test;

/**
 * 数据库测试环境
 *
 * @author legendjw
 */
public enum DbTestEnv {
    // 使用 docker 容器
    CONTAINER,
    // 使用本地数据库
    LOCAL
}
