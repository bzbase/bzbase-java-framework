package org.bzbase.library.persistence.changetracking.snapshot;

import org.bzbase.library.persistence.changetracking.mock.TestTrackObject;
import org.bzbase.library.persistence.changetracking.mock.TestTrackObjectId;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 序列化对象快照
 *
 * @author legendjw
 */
class SerializableObjectSnapshotTest {
    private final SerializableObjectSnapshot serializableObjectSnapshot = new SerializableObjectSnapshot();

    @Test
    void snapshot() {
        TestTrackObject testTrackObject = new TestTrackObject();
        testTrackObject.setId(new TestTrackObjectId("1"));
        testTrackObject.setInteger(2);
        Map<String, Object> map = Collections.singletonMap("a", "apple");
        List<Map<String, Object>> list = Collections.singletonList(map);
        testTrackObject.setList(list);
        testTrackObject.setMap(map);
        TestTrackObject snapshot = serializableObjectSnapshot.snapshot(testTrackObject);
        assertThat(snapshot).isEqualTo(testTrackObject);
    }
}
