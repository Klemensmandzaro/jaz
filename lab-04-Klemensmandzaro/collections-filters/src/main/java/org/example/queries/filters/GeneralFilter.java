package org.example.queries.filters;

import org.example.model.Person;
import org.example.queries.search.SearchParameters;

import java.util.List;
import java.util.function.Predicate;

public class GeneralFilter implements IFilterPeople{
    SearchParameters searchParams;
    Predicate<SearchParameters> canFilterPredicate;
    DualPredicate filterDualPredicate;

    public GeneralFilter(Predicate<SearchParameters> canFilterPredicate, DualPredicate filterDualPredicate) {
        this.canFilterPredicate = canFilterPredicate;
        this.filterDualPredicate = filterDualPredicate;
    }

    @Override
    public void setSearchParameters(SearchParameters searchParams) {
        this.searchParams = searchParams;
    }

    @Override
    public boolean canFilter() {
        return canFilterPredicate.test(searchParams);
    }

    @Override
    public List<Person> filter(List<Person> list) {
        return list.stream().filter(p->filterDualPredicate.check(searchParams, p)).toList();
    }
}
