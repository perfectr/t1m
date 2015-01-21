package nz.govt.doc.t1m.domain.criteria;

/**
 */
public interface PagedCriteria {

    public int getPageNumber();
    public void setPageNumber(int pageNumber);
    public int getPageSize();
    public void setPageSize(int pageSize);
    public long getOffset();
}
