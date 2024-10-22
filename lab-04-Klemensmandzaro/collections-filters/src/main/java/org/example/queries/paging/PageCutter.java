package org.example.queries.paging;

import org.example.model.Person;
import org.example.queries.search.Page;

import java.util.List;

public class PageCutter implements ICutToPage{
    @Override
    public List<Person> cut(Page page, List<Person> data) {
        int indexMin = page.getSize()* (page.getPageNumber()-1);
        int indexMax = page.getSize()* page.getPageNumber() - 1;
        if (data.size() >= indexMax) {
            List<Person> pageData = data.subList(indexMin, indexMax+1);
            return pageData;
        } else return data;
    }
}
