package org.bzbase.library.persistence.changetracking.snapshot;

import org.bzbase.library.persistence.changetracking.mock.TestTrackObject;
import org.bzbase.library.persistence.changetracking.mock.TestTrackObjectId;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * json转换来实现对象快照测试
 *
 * @author legendjw
 */
class JsonObjectSnapshotTest {
    private final JsonObjectSnapshot jsonObjectSnapshot = new JsonObjectSnapshot();

    @Test
    void snapshot() {
        TestTrackObject testTrackObject = new TestTrackObject();
        testTrackObject.setId(new TestTrackObjectId("1"));
        testTrackObject.setInteger(2);
        Map<String, Object> map = Collections.singletonMap("a", "apple");
        List<Map<String, Object>> list = Collections.singletonList(map);
        testTrackObject.setList(list);
        testTrackObject.setMap(map);
        TestTrackObject snapshot = jsonObjectSnapshot.snapshot(testTrackObject);
        assertThat(snapshot).isEqualTo(testTrackObject);
    }
}
