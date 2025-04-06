package org.bzbase.library.persistence.changetracking.diff;

import de.danielbechler.diff.ObjectDifferBuilder;
import de.danielbechler.diff.node.DiffNode;
import de.danielbechler.diff.selector.ElementSelector;
import de.danielbechler.diff.selector.MapKeyElementSelector;

import java.util.*;

/**
 * 对象差异比对工具类
 *
 * @author legendjw
 */
public class ObjectDiffUtils {
    /**
     * 比对两个对象
     *
     * @param old 老对象
     * @param now 新对象
     * @return 两个对象之间的差异
     */
    public static ObjectDiff diff(Object old, Object now) {
        DiffNode root = ObjectDifferBuilder.buildDefault().compare(now, old);
        Set<ObjectChangedField> changeFields = new HashSet<>();
        Map<String, Set<CollectionChangedObject<Object>>> changedCollectionObjects = new HashMap<>(Collections.emptyMap());
        Map<String, Set<MapChangedObject<Object, Object>>> changedMapObjects = new HashMap<>(Collections.emptyMap());
        root.visit((node, visit) -> {
            // 只收集对象第一层属性的改变
            if (node.hasChanges() && !node.isRootNode() ) {
                visit.dontGoDeeper();
                // 原始值
                Object baseValue = node.canonicalGet(old);
                // 新的值
                Object workingValue = node.canonicalGet(now);
                // 属性名称
                String propertyName = node.getPropertyName();

                ObjectChangedField objectChangedField = new ObjectChangedField(propertyName, baseValue, workingValue);
                changeFields.add(objectChangedField);
                // 收集所有的列表字段的更改
                if (Collection.class.isAssignableFrom(node.getValueType())) {
                    node.visitChildren((childNode, childVisit) -> {
                        childVisit.dontGoDeeper();
                        if (!childNode.hasChanges()) {
                            return;
                        }
                        Object childBaseValue = childNode.canonicalGet(old);
                        Object childWorkingValue = childNode.canonicalGet(now);
                        if (!changedCollectionObjects.containsKey(propertyName)) {
                            changedCollectionObjects.put(propertyName, new HashSet<>());
                        }
                        if (childNode.isAdded()) {
                            changedCollectionObjects.get(propertyName).add(
                                    new CollectionChangedObject<>(ObjectChangedType.ADDED, null, childWorkingValue));
                        }
                        else if (childNode.isChanged()) {
                            changedCollectionObjects.get(propertyName).add(
                                    new CollectionChangedObject<>(ObjectChangedType.CHANGED, childBaseValue, childWorkingValue));
                        }
                        else if (childNode.isRemoved()) {
                            changedCollectionObjects.get(propertyName).add(
                                    new CollectionChangedObject<>(ObjectChangedType.REMOVED, childBaseValue, null));
                        }
                    });
                }
                // 收集所有的映射字段的更改
                else if (Map.class.isAssignableFrom(node.getValueType())) {
                    node.visitChildren((childNode, childVisit) -> {
                        childVisit.dontGoDeeper();
                        if (!childNode.hasChanges()) {
                            return;
                        }
                        Object childBaseValue = childNode.canonicalGet(old);
                        Object childWorkingValue = childNode.canonicalGet(now);
                        Object mapKey = null;
                        ElementSelector lastElementSelector = childNode.getPath().getLastElementSelector();
                        if (lastElementSelector instanceof MapKeyElementSelector) {
                            mapKey = ((MapKeyElementSelector) lastElementSelector).getKey();
                        }

                        if (!changedMapObjects.containsKey(propertyName)) {
                            changedMapObjects.put(propertyName, new HashSet<>());
                        }
                        if (childNode.isAdded()) {
                            changedMapObjects.get(propertyName).add(
                                    new MapChangedObject<>(ObjectChangedType.ADDED, mapKey, null, childWorkingValue));
                        }
                        else if (childNode.isChanged()) {
                            changedMapObjects.get(propertyName).add(
                                    new MapChangedObject<>(ObjectChangedType.CHANGED, mapKey, childBaseValue, childWorkingValue));
                        }
                        else if (childNode.isRemoved()) {
                            changedMapObjects.get(propertyName).add(
                                    new MapChangedObject<>(ObjectChangedType.REMOVED, mapKey, childBaseValue, null));
                        }
                    });
                }
            }

        });
        return new ObjectDiff(root.isChanged(), changeFields, changedCollectionObjects, changedMapObjects);
    }
}
