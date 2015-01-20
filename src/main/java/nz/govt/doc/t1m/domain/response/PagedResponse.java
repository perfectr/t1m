package nz.govt.doc.t1m.domain.response;

import com.mysema.query.SearchResults;

import java.util.List;

/**
 */
public class PagedResponse<T> extends AbstractResponse<T> {

    protected int page;
    protected int pageSize;
    protected int total;
    protected int numberOfPages;
    protected List<T> results;


    public PagedResponse() {
    }

    public PagedResponse(SearchResults<T> searchResults) {
        this.page = (int)(searchResults.getOffset() / searchResults.getLimit()) + 1;
        this.pageSize = (int)searchResults.getLimit();
        this.total = (int) searchResults.getTotal();
        //this.numberOfPages = Math.floorDiv(this.total, this.pageSize) + 1;
        this.numberOfPages = (int)Math.floor((double)this.total / this.pageSize ) + 1;
        this.results = searchResults.getResults();
    }

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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}
