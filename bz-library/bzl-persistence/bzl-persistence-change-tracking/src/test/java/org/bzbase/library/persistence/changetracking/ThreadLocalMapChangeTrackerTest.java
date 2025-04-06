package org.bzbase.library.persistence.changetracking;

import org.bzbase.library.persistence.changetracking.diff.ObjectDiff;
import org.bzbase.library.persistence.changetracking.mock.TestTrackObject;
import org.bzbase.library.persistence.changetracking.mock.TestTrackObjectId;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 基于ThreadLocal以及WeakHashMap实现的变更追踪器测试
 *
 * @author legendjw
 */
class ThreadLocalMapChangeTrackerTest {
    private final ChangeTracker<TestTrackObject, TestTrackObjectId> changeTracker =
            new ThreadLocalMapChangeTracker<>(TestTrackObject.class);

    @Test
    public void test_change_tracker() {
        TestTrackObject testTrackObject = new TestTrackObject();
        testTrackObject.setId(new TestTrackObjectId("1"));
        testTrackObject.setInteger(2);
        Map<String, Object> map = Collections.singletonMap("a", "apple");
        List<Map<String, Object>> list = Collections.singletonList(map);
        testTrackObject.setList(list);
        testTrackObject.setMap(map);

        changeTracker.attach(testTrackObject);
        assertThat(changeTracker.find(testTrackObject.getId())).hasValue(testTrackObject);
        assertThat(changeTracker.find(new TestTrackObjectId("5"))).isEmpty();
        assertThat(changeTracker.find()).hasSize(1);

        changeTracker.detach(testTrackObject);
        assertThat(changeTracker.find(testTrackObject.getId())).isEmpty();
        assertThat(changeTracker.find()).hasSize(0);

        changeTracker.attach(testTrackObject);
        changeTracker.clear();
        assertThat(changeTracker.find()).hasSize(0);

        changeTracker.attach(testTrackObject);
        testTrackObject.setInteger(3);
        ObjectDiff objectDiff = changeTracker.detectChanges(testTrackObject);
        assertThat(objectDiff.hasChanges()).isTrue();
        assertThat(objectDiff.hasChanges(TestTrackObject.Fields.integer)).isTrue();
    }
}
