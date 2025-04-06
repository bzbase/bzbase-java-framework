package org.bzbase.library.persistence.changetracking.diff;

import lombok.Value;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 对象差异
 *
 * @author legendjw
 */
@Value
public class ObjectDiff {
    boolean hasChanges;
    Set<ObjectChangedField> changedFields;
    Map<String, Set<CollectionChangedObject<Object>>> changedCollectionObjects;
    Map<String, Set<MapChangedObject<Object, Object>>> changedMapObjects;

    /**
     * 比对对象是否有改变
     *
     * @return 有改变返回 true，否则返回 false
     */
    public boolean hasChanges() {
        return hasChanges;
    }

    /**
     * 指定字段值是否有改变
     *
     * @param field 字段名称
     * @return 有改变返回 true，否则返回 false
     */
    public boolean hasChanges(String field) {
        return changedFields.stream().anyMatch(c -> c.getField().equals(field));
    }

    /**
     * 获取指定集合字段新增的对象
     *
     * @param field 集合字段
     * @param objectClass 对象类，用于安全转换
     * @return 集合中新增的对象
     * @param <T> 对象类型
     */
    public <T> Set<T> getCollectionAddedObjects(String field, Class<T> objectClass) {
        if (!changedCollectionObjects.containsKey(field)) {
            return Collections.emptySet();
        }
        return changedCollectionObjects.get(field).stream()
                .filter(c -> c.getChangedType().equals(ObjectChangedType.ADDED)
                        && objectClass.isInstance(c.getNewValue()))
                .map(c -> objectClass.cast(c.getNewValue()))
                .collect(Collectors.toSet());
    }

    /**
     * 获取指定集合字段改变的对象
     *
     * @param field 集合字段
     * @param objectClass 对象类，用于安全转换
     * @return 集合中改变的对象
     * @param <T> 对象类型
     */
    public <T> Set<CollectionChangedObject<T>> getCollectionChangedObjects(String field, Class<T> objectClass) {
        if (!changedCollectionObjects.containsKey(field)) {
            return Collections.emptySet();
        }
        return changedCollectionObjects.get(field).stream()
                .filter(c -> c.getChangedType().equals(ObjectChangedType.CHANGED)
                        && objectClass.isInstance(c.getOldValue()) && objectClass.isInstance(c.getNewValue())
                )
                .map(c -> new CollectionChangedObject<>(
                        c.getChangedType(), objectClass.cast(c.getOldValue()), objectClass.cast(c.getNewValue())))
                .collect(Collectors.toSet());
    }

    /**
     * 获取指定集合字段改变的对象
     *
     * @param field 集合字段
     * @param objectClass 对象类，用于安全转换
     * @return 集合中删除的对象
     * @param <T> 对象类型
     */
    public <T> Set<T> getCollectionRemovedObjects(String field, Class<T> objectClass) {
        if (!changedCollectionObjects.containsKey(field)) {
            return Collections.emptySet();
        }
        return changedCollectionObjects.get(field).stream()
                .filter(c -> c.getChangedType().equals(ObjectChangedType.REMOVED)
                        && objectClass.isInstance(c.getOldValue()))
                .map(c -> objectClass.cast(c.getOldValue()))
                .collect(Collectors.toSet());
    }

    /**
     * 获取指定映射字段新增的对象
     *
     * @param field 集合字段
     * @param keyClass 映射键类，用于安全转换
     * @param objectClass 对象类，用于安全转换
     * @return 映射中新增的对象
     * @param <K> 映射键类型
     * @param <T> 对象类型
     */
    public <K, T> Map<K, T> getMapAddedObjects(String field, Class<K> keyClass, Class<T> objectClass) {
        if (!changedMapObjects.containsKey(field)) {
            return Collections.emptyMap();
        }
        return changedMapObjects.get(field).stream()
                .filter(c -> c.getChangedType().equals(ObjectChangedType.ADDED)
                        && keyClass.isInstance(c.getKey()) && objectClass.isInstance(c.getNewValue()))
                .collect(Collectors.toMap(c -> keyClass.cast(c.getKey()), c -> objectClass.cast(c.getNewValue())));
    }

    /**
     * 获取指定映射字段改变的对象
     *
     * @param field 映射字段
     * @param keyClass 映射键类，用于安全转换
     * @param objectClass 对象类，用于安全转换
     * @return 映射中改变的对象
     * @param <K> 映射键类型
     * @param <T> 对象类型
     */
    public <K, T> Set<MapChangedObject<K, T>> getMapChangedObjects(String field, Class<K> keyClass, Class<T> objectClass) {
        if (!changedMapObjects.containsKey(field)) {
            return Collections.emptySet();
        }
        return changedMapObjects.get(field).stream()
                .filter(c -> c.getChangedType().equals(ObjectChangedType.CHANGED)
                        && keyClass.isInstance(c.getKey())
                        && objectClass.isInstance(c.getOldValue()) && objectClass.isInstance(c.getNewValue())
                )
                .map(c -> new MapChangedObject<>(c.getChangedType(),
                        keyClass.cast(c.getKey()), objectClass.cast(c.getOldValue()), objectClass.cast(c.getNewValue())))
                .collect(Collectors.toSet());
    }

    /**
     * 获取指定映射字段删除的对象
     *
     * @param field 映射字段
     * @param keyClass 映射键类，用于安全转换
     * @param objectClass 对象类，用于安全转换
     * @return 映射中删除的对象
     * @param <K> 映射键类型
     * @param <T> 对象类型
     */
    public <K, T> Map<K, T> getMapRemovedObjects(String field, Class<K> keyClass, Class<T> objectClass) {
        if (!changedMapObjects.containsKey(field)) {
            return Collections.emptyMap();
        }
        return changedMapObjects.get(field).stream()
                .filter(c -> c.getChangedType().equals(ObjectChangedType.REMOVED)
                        && keyClass.isInstance(c.getKey()) && objectClass.isInstance(c.getOldValue()))
                .collect(Collectors.toMap(c -> keyClass.cast(c.getKey()), c -> objectClass.cast(c.getOldValue())));
    }
}
