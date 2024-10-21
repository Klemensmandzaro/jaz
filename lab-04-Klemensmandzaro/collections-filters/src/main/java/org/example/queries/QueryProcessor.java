package org.example.queries;

import org.example.model.Person;
import org.example.queries.filters.IFilterPeople;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.ArrayList;
import java.util.List;

public class QueryProcessor {
    private List<IFilterPeople> filter = new ArrayList<IFilterPeople>();
//    private List<ICalculate> calculators;
//    private ICutPage pageCutter;

    public QueryProcessor addFilter(IFilterPeople filter) {
        this.filter.add(filter);
        return this;
    }

    public Results GetResults(SearchParameters parameters, List<Person> data){
        return null;
    }
}
