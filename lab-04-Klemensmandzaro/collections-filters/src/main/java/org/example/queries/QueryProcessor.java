package org.example.queries;

import org.example.model.Person;
import org.example.queries.calculations.GeneralCalculator;
import org.example.queries.calculations.ICalculate;
import org.example.queries.filters.GeneralFilter;
import org.example.queries.filters.IFilterPeople;
import org.example.queries.paging.ICutToPage;
import org.example.queries.paging.PageCutter;
import org.example.queries.results.FunctionResult;
import org.example.queries.results.Results;
import org.example.queries.search.FunctionsParameters;
import org.example.queries.search.SearchParameters;

import java.util.ArrayList;
import java.util.List;

public class QueryProcessor {
    private List<IFilterPeople> filter = new ArrayList<IFilterPeople>();
    private List<ICalculate> calculators = new ArrayList<ICalculate>();
    private ICutToPage pageCutter;
    private Results results = new Results();

    public QueryProcessor addFilter(IFilterPeople filter) {
        this.filter.add(filter);
        return this;
    }

    public QueryProcessor addCalculator(ICalculate calculator) {
        this.calculators.add(calculator);
        return this;
    }

    public QueryProcessor addPageCutter(ICutToPage pageCutter) {
        this.pageCutter = pageCutter;
        return this;
    }

    public Results GetResults(SearchParameters parameters, List<Person> data) {

        this.filter = new ArrayList<>();

        IFilterPeople byNameGenericFilter = new GeneralFilter(
                (searchParams) -> searchParams.getName() != null,
                (searchParams, person) -> searchParams.getName().equals(person.getName()));
        byNameGenericFilter.setSearchParameters(parameters);

        IFilterPeople byAgeFromGenericFilter = new GeneralFilter(
                (searchParams) -> searchParams.getAgeFrom() > 0,
                (searchParams, person) -> person.getAge() >= searchParams.getAgeFrom());
        byAgeFromGenericFilter.setSearchParameters(parameters);

        IFilterPeople byAgeToGenericFilter = new GeneralFilter(
                (searchParams) -> searchParams.getAgeTo() > 0,
                (searchParams, person) -> person.getAge() <= searchParams.getAgeTo());
        byAgeToGenericFilter.setSearchParameters(parameters);

        IFilterPeople byGenderGenericFilter = new GeneralFilter(
                (searchParams) -> searchParams.getSelectedGenders().size() > 0,
                (searchParams, person) -> searchParams.getSelectedGenders().contains(person.getGender()));
        byGenderGenericFilter.setSearchParameters(parameters);

        IFilterPeople byIncomeFromGenericFilter = new GeneralFilter(
                (searchParams) -> searchParams.getIncomeFrom() > 0,
                (searchParams, person) -> person.getIncome() >= searchParams.getIncomeFrom());
        byIncomeFromGenericFilter.setSearchParameters(parameters);

        IFilterPeople byIncomeToGenericFilter = new GeneralFilter(
                (searchParams) -> searchParams.getIncomeTo() > 0,
                (searchParams, person) -> person.getIncome() <= searchParams.getIncomeTo());
        byIncomeToGenericFilter.setSearchParameters(parameters);

        this.addFilter(byAgeFromGenericFilter)
                .addFilter(byAgeToGenericFilter)
                .addFilter(byNameGenericFilter)
                .addFilter(byGenderGenericFilter)
                .addFilter(byIncomeFromGenericFilter)
                .addFilter(byIncomeToGenericFilter);

        for (IFilterPeople fp : this.filter) {
            if (fp.canFilter()) data = fp.filter(data);
        }

        this.results.setPages(((int) data.size() / parameters.getPage().getSize()) + 1);

        this.results.setCurrentPage(parameters.getPage().getPageNumber());


        this.addPageCutter(new PageCutter());
        data = this.pageCutter.cut(parameters.getPage(), data);

        this.results.setItems(data);

        this.calculators = new ArrayList<>();
        ICalculate ageCalculator = new GeneralCalculator("age", p -> p.getAge());
        ICalculate incomeCalculator = new GeneralCalculator("income", p -> p.getIncome());
        this.addCalculator(ageCalculator)
                .addCalculator(incomeCalculator);

        List<FunctionResult> functionsResults = new ArrayList<>();
        for (FunctionsParameters func : parameters.getFunctions()) {
            if (func.getFieldName() == "income" || func.getFieldName() == "age") {
                FunctionResult funcResults = new FunctionResult();
                double value=0;
                if (func.getFieldName() == "income") {
                    value = incomeCalculator.calculate(new FunctionsParameters(func.getFieldName(), func.getFunction()), data);
                }
                if (func.getFieldName() == "age") {
                    value = ageCalculator.calculate(new FunctionsParameters(func.getFieldName(), func.getFunction()), data);
                }
                funcResults.setFieldName(func.getFieldName());
                funcResults.setFunction(func.getFunction());
                funcResults.setValue(value);
                functionsResults.add(funcResults);
            }
        }

        this.results.setFunctionResults(functionsResults);


        return this.results;
    }
}
