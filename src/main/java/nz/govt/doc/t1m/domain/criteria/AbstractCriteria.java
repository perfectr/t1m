package nz.govt.doc.t1m.domain.criteria;

/**
 */
public abstract class AbstractCriteria implements PagedCriteria {

    private int pageNumber = 1;

    // This will get overridden by the web client code, but it's still useful to
    // set here, so that there's something returned for unit tests.
    private int pageSize = 10;

    public AbstractCriteria() {
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getOffset() {
        return (pageNumber - 1) * pageSize;
    }
}
