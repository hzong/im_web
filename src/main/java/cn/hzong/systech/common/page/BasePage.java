package cn.hzong.systech.common.page;

import java.util.ArrayList;
import java.util.List;

public class BasePage<T> {
	public final static int DEFAULT_PAGE_SIZE = 10;
    protected int pageNo = 1;
 
    protected int pageSize = DEFAULT_PAGE_SIZE;
    protected boolean autoCount = true;
    protected boolean autoPaging = true;
 
    protected List<T> rows = new ArrayList<T>();
 
    protected long total = 0;
 
 
    public BasePage() {
    }
 
    public BasePage(int pageSize) {
        this.pageSize = pageSize;
    }
 
 
    public int getPageNo() {
        return pageNo;
    }
 
 
    public void setPageNo(final int pageNo) {
        this.pageNo = pageNo;
 
        if (pageNo < 1) {
            this.pageNo = 1;
        }
    }
 
 
    public int getPageSize() {
        return pageSize;
    }
 
 
    public void setPageSize(final int pageSize) {
        this.pageSize = pageSize;
    }
 
 
    /**
     * 每一页开始的下标
     *
     * @return
     */
    public int getFirst() {
        return ((pageNo - 1) * pageSize) + 1;
    }
 
 
    public boolean isAutoCount() {
        return autoCount;
    }
 
 
    public void setAutoCount(final boolean autoCount) {
        this.autoCount = autoCount;
    }
 
    /**
     * 获取
     *
     * @return
     */
    public List<T> getRows() {
        return rows;
    }
 
 
    public void setRows(final List<T> result) {
        this.rows = result;
    }
 
    /**
     * 获取总的记录数
     *
     * @return
     */
    public long getTotal() {
        return total;
    }
 
 
    public void setTotal(final long totalCount) {
        this.total = totalCount;
    }
 
 
    /**
     * 获取总的页数
     *
     * @return
     */
    public long getTotalPages() {
        long count = total / pageSize;
        if (total % pageSize > 0) {
            count++;
        }
        return count;
    }
 
 
    public boolean isHasNext() {
        return (pageNo + 1 <= getTotalPages());
    }
 
    /**
     * 获取下一页页码
     *
     * @return
     */
    public int getNextPage() {
        if (isHasNext()) {
            return pageNo + 1;
        } else {
            return pageNo;
        }
    }
 
 
    public boolean isHasPre() {
        return (pageNo - 1 >= 1);
    }
 
 
    public int getPrePage() {
        if (isHasPre()) {
            return pageNo - 1;
        } else {
            return pageNo;
        }
    }
 
 
    public boolean isAutoPaging() {
        return autoPaging;
    }
 
    public void setAutoPaging(boolean autoPaging) {
        this.autoPaging = autoPaging;
    }
 
    @Override
    public String toString() {
        return "Page{" +
                "total=" + total +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", autoCount=" + autoCount +
                '}';
    }
}
