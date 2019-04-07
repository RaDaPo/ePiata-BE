package ro.hacktm.oradea.epiata.model.builder;

import org.springframework.stereotype.Component;

@Component
class FilteredOffersQueryBuilderImpl implements FilteredOffersQueryBuilder {

	@Override
	public FilteredOffersQueryBuilderImpl filterBy() {
		return this;
	}
}
