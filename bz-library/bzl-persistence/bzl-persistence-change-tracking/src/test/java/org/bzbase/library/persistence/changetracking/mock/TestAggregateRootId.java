package org.bzbase.library.persistence.changetracking.mock;

import lombok.Value;

import java.io.Serializable;

/**
 * 测试聚合对象ID
 *
 * @author legendjw
 */
@Value
public class TestAggregateRootId implements Serializable {
    String value;
}
