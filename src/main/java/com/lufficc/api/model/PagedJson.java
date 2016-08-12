package com.lufficc.api.model;

import org.springframework.data.domain.Page;

/**
 * Created by lcc_luffy on 2016/8/8.
 */
public class PagedJson<T> extends JsonWrap<T> {
    private int totalPages;
    private boolean last;
    private int number;
    private int size;
    private boolean first;
    private int numberOfElements;

    public PagedJson(int code, String msg, T content) {
        super(code, msg, content);
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public void fillData(Page page) {
        totalPages = page.getTotalPages();
        last = page.isLast();
        number = page.getNumber();
        size = page.getSize();
        first = page.isFirst();
        numberOfElements = page.getNumberOfElements();
    }
}
