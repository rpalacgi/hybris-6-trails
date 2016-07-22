package de.hybris.promotrail.constants.providers;

import de.hybris.platform.ruleengineservices.rao.CartRAO;
import de.hybris.platform.ruleengineservices.rao.providers.impl.DefaultCartRAOProvider;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

public class TrailCartRAOProvider extends DefaultCartRAOProvider {

	public static final String EXPAND_CUSTOMER_REVIEWS = "EXPAND_CUSTOMER_REVIEWS";


	public Collection<String> getOptions() {
		return Collections.singletonList(EXPAND_CUSTOMER_REVIEWS)
	}

	@Override
	public Collection<String> getValidOptions() {
		return getOptions();
	}

	@Override
	public Set<?> expandFactModel(Object modelFact) {
		return expandFactModel(modelFact, getOptions());
	}

	@Override
	protected Set<Object> expandRAO(CartRAO cart, Collection<String> options) {
		Set<Object> facts = super.expandRAO(cart, options);
		if (options.stream().anyMatch(EXPAND_CUSTOMER_REVIEWS::equals)) {
			facts.addAll(cart.getUser().getCustomerReviews());
		}
		return facts;
	}
}
