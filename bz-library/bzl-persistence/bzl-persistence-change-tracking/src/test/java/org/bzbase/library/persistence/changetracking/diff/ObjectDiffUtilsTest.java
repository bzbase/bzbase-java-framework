package org.bzbase.library.persistence.changetracking.diff;

import org.bzbase.library.persistence.changetracking.mock.TestAggregateLinkObject;
import org.bzbase.library.persistence.changetracking.mock.TestAggregateRoot;
import org.bzbase.library.persistence.changetracking.mock.TestAggregateRootId;
import org.bzbase.library.persistence.changetracking.snapshot.JsonObjectSnapshot;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 对象差异比对工具类测试
 *
 * @author legendjw
 */
class ObjectDiffUtilsTest {
    @Test
    public void test_object_diff() {
        List<TestAggregateLinkObject> linkObjects = new ArrayList<>();
        TestAggregateLinkObject testAggregateLinkObject0 = new TestAggregateLinkObject("1", "link1");
        TestAggregateLinkObject testAggregateLinkObject1 = new TestAggregateLinkObject("2", "link2");
        linkObjects.add(testAggregateLinkObject0);
        linkObjects.add(testAggregateLinkObject1);

        Map<String, TestAggregateLinkObject> mapLinkObjects = new HashMap<>();
        TestAggregateLinkObject testMapAggregateLinkObject0 = new TestAggregateLinkObject("1", "link1");
        TestAggregateLinkObject testMapAggregateLinkObject1 = new TestAggregateLinkObject("2", "link2");
        mapLinkObjects.put("map0", testMapAggregateLinkObject0);
        mapLinkObjects.put("map1", testMapAggregateLinkObject1);

        TestAggregateRoot testAggregateRoot = new TestAggregateRoot(new TestAggregateRootId("1"), "root0", linkObjects, mapLinkObjects);

        ObjectDiff objectDiff = null;
        TestAggregateRoot snapshot = new JsonObjectSnapshot().snapshot(testAggregateRoot);
        objectDiff = ObjectDiffUtils.diff(testAggregateRoot, snapshot);
        assertThat(objectDiff.hasChanges()).isFalse();
        assertThat(objectDiff.hasChanges(TestAggregateRoot.Fields.name)).isFalse();
        assertThat(objectDiff.getChangedFields()).isEmpty();

        snapshot.setName("changed");
        List<TestAggregateLinkObject> newLinkObjects = new ArrayList<>();
        TestAggregateLinkObject testNewAggregateLinkObject0 = new TestAggregateLinkObject("1", "changed");
        TestAggregateLinkObject testNewAggregateLinkObject1 = new TestAggregateLinkObject("3", "added");
        newLinkObjects.add(testNewAggregateLinkObject0);
        newLinkObjects.add(testNewAggregateLinkObject1);
        snapshot.setLinkObjects(newLinkObjects);

        Map<String, TestAggregateLinkObject> newMapLinkObjects = new HashMap<>();
        TestAggregateLinkObject testNewMapAggregateLinkObject0 = new TestAggregateLinkObject("1", "changed");
        TestAggregateLinkObject testNewMapAggregateLinkObject1 = new TestAggregateLinkObject("2", "link2");
        newMapLinkObjects.put("map0", testNewMapAggregateLinkObject0);
        newMapLinkObjects.put("map2", testNewMapAggregateLinkObject1);
        snapshot.setMapLinkObjects(newMapLinkObjects);

        objectDiff = ObjectDiffUtils.diff(testAggregateRoot, snapshot);
        assertThat(objectDiff.hasChanges()).isTrue();
        assertThat(objectDiff.hasChanges(TestAggregateRoot.Fields.name)).isTrue();
        assertThat(objectDiff.hasChanges(TestAggregateRoot.Fields.id)).isFalse();
        assertThat(objectDiff.getChangedFields()).isNotEmpty();
        assertThat(objectDiff.getCollectionAddedObjects(TestAggregateRoot.Fields.linkObjects, TestAggregateLinkObject.class))
                .hasSize(1).contains(testNewAggregateLinkObject1);
        assertThat(objectDiff.getCollectionChangedObjects(TestAggregateRoot.Fields.linkObjects, TestAggregateLinkObject.class))
                .hasSize(1).anyMatch(o -> o.getNewValue().equals(testNewAggregateLinkObject0));
        assertThat(objectDiff.getCollectionRemovedObjects(TestAggregateRoot.Fields.linkObjects, TestAggregateLinkObject.class))
                .hasSize(1).contains(testAggregateLinkObject1);
        assertThat(objectDiff.getMapAddedObjects(TestAggregateRoot.Fields.mapLinkObjects, String.class, TestAggregateLinkObject.class))
                .hasSize(1).containsEntry("map2", testNewMapAggregateLinkObject1);
        assertThat(objectDiff.getMapChangedObjects(TestAggregateRoot.Fields.mapLinkObjects, String.class, TestAggregateLinkObject.class))
                .hasSize(1).anyMatch(o -> o.getKey().equals("map0") && o.getNewValue().equals(testNewMapAggregateLinkObject0));
        assertThat(objectDiff.getMapRemovedObjects(TestAggregateRoot.Fields.mapLinkObjects, String.class, TestAggregateLinkObject.class))
                .hasSize(1).containsEntry("map1", testMapAggregateLinkObject1);

        testAggregateRoot.equals(snapshot);
    }
}
