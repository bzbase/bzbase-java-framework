package org.bzbase.library.persistence.changetracking.mock;

import lombok.Value;

import java.io.Serializable;

/**
 * 测试追踪对象ID
 *
 * @author legendjw
 */
@Value
public class TestTrackObjectId implements Serializable {
    String value;
}
