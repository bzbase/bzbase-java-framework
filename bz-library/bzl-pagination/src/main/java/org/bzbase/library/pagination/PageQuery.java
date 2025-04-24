package org.bzbase.library.pagination;

/**
 * 分页查询
 *
 * @author legendjw
 */
public class PageQuery {
    private int page;
    private int pageSize;

    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 是否设置了分页和分页数量
     */
    public boolean hasPage() {
        return page > 0 && pageSize > 0;
    }

    /**
     * 获取分页信息
     */
    public Pageable getPageable() {
        return new PageRequest(page, pageSize);
    }
}
