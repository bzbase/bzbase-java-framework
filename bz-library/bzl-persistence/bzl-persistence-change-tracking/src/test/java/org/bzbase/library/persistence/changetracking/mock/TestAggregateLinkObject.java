package org.bzbase.library.persistence.changetracking.mock;

import lombok.Value;

import java.util.Objects;

/**
 * 测试聚合关联对象
 *
 * @author legendjw
 */
@Value
public class TestAggregateLinkObject {
    String linkObjectId;
    String linkName;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        TestAggregateLinkObject that = (TestAggregateLinkObject) object;

        return Objects.equals(linkObjectId, that.linkObjectId);
    }

    @Override
    public int hashCode() {
        return linkObjectId != null ? linkObjectId.hashCode() : 0;
    }
}
