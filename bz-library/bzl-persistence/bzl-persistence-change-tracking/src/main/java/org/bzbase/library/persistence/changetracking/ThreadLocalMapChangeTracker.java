package org.bzbase.library.persistence.changetracking;

import lombok.Getter;
import org.bzbase.library.ddd.type.Identifiable;
import org.bzbase.library.persistence.changetracking.diff.ObjectDiff;
import org.bzbase.library.persistence.changetracking.diff.ObjectDiffUtils;
import org.bzbase.library.persistence.changetracking.snapshot.ObjectSnapshot;
import org.bzbase.library.persistence.changetracking.snapshot.SerializableObjectSnapshot;

import java.util.*;

/**
 * 基于ThreadLocal以及WeakHashMap实现的变更追踪器
 *
 * @author legendjw
 */
public class ThreadLocalMapChangeTracker<T extends Identifiable<ID>, ID> implements ChangeTracker<T, ID> {
    @Getter
    private final Class<? extends T> objectClass;

    private final ThreadLocal<WeakHashMap<ID, T>> objects;

    private final ObjectSnapshot objectSnapshot;

    public ThreadLocalMapChangeTracker(Class<? extends T> objectClass) {
        this.objectClass = objectClass;
        this.objects = ThreadLocal.withInitial(WeakHashMap::new);
        this.objectSnapshot = new SerializableObjectSnapshot();
    }

    public ThreadLocalMapChangeTracker(Class<? extends T> objectClass, ObjectSnapshot objectSnapshot) {
        this.objectClass = objectClass;
        this.objects = ThreadLocal.withInitial(WeakHashMap::new);
        this.objectSnapshot = objectSnapshot;
    }

    @Override
    public void attach(T object) {
        ID id = Objects.requireNonNull(object.getId());
        objects.get().put(id, objectSnapshot.snapshot(object));
    }

    @Override
    public void detach(T object) {
        ID id = Objects.requireNonNull(object.getId());
        objects.get().remove(id);
    }

    @Override
    public void clear() {
        objects.get().clear();
    }

    @Override
    public ObjectDiff detectChanges(T object) {
        ID id = Objects.requireNonNull(object.getId());
        T snapshot = objects.get().get(id);
        if (snapshot == null) {
            throw new IllegalArgumentException("Object is not tracked to get changes");
        }
        return ObjectDiffUtils.diff(snapshot, object);
    }

    @Override
    public Optional<T> find(ID id) {
        Objects.requireNonNull(id);
        return Optional.ofNullable(objects.get().get(id));
    }

    @Override
    public List<T> find() {
        return new ArrayList<>(objects.get().values());
    }
}
