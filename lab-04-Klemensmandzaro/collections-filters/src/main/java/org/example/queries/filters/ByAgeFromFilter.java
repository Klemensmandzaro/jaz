package org.example.queries.filters;

import org.example.model.Person;
import org.example.queries.search.SearchParameters;

import java.util.List;

public class ByAgeFromFilter implements IFilterPeople{
    SearchParameters searchParams;
    @Override
    public void setSearchParameters(SearchParameters searchParams) {
        this.searchParams = searchParams;
    }

    @Override
    public boolean canFilter() {
        return searchParams.getAgeFrom()>0;
    }

    @Override
    public List<Person> filter(List<Person> items) {
        return items.stream().filter(p->p.getAge() >= searchParams.getAgeFrom()).toList();
    }
}
