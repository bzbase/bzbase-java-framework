package org.bzbase.library.persistence.changetracking.mock;

import lombok.Data;
import lombok.experimental.FieldNameConstants;
import org.bzbase.library.ddd.type.Identifiable;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 测试追踪对象
 *
 * @author legendjw
 */
@Data
@FieldNameConstants
public class TestTrackObject implements Identifiable<TestTrackObjectId>, Serializable {
    private TestTrackObjectId id;
    private Integer integer;
    private List<Map<String, Object>> list;
    private Map<String, Object> map;
}
