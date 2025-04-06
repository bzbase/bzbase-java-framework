package org.bzbase.library.persistence.changetracking.mock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.bzbase.library.ddd.type.AbstractAggregateRoot;

import java.util.List;
import java.util.Map;

/**
 * 测试聚合类
 *
 * @author legendjw
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class TestAggregateRoot extends AbstractAggregateRoot<TestAggregateRootId> {
    private TestAggregateRootId id;
    private String name;
    private List<TestAggregateLinkObject> linkObjects;
    private Map<String, TestAggregateLinkObject> mapLinkObjects;
}
