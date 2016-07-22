package de.hybris.promotrail.converters.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.customerreview.model.CustomerReviewModel;
import de.hybris.platform.ruleengineservices.rao.UserRAO;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.promotrail.rao.CustomerReviewRAO;
import org.springframework.beans.factory.annotation.Required;

import java.util.stream.Collectors;

public class PromoTrialUserRAOPopulator implements Populator<UserModel, UserRAO> {

	private Converter<CustomerReviewModel, CustomerReviewRAO> customerReviewRAOConverter;

	@Override
	public void populate(UserModel source, UserRAO target) throws ConversionException {
		target.setCustomerReviews(source.getCustomerReviews()
										  .stream()
										  .map(customerReviewRAOConverter::convert)
										  .collect(Collectors.toList()));
	}

	public Converter<CustomerReviewModel, CustomerReviewRAO> getCustomerReviewRAOConverter() {
		return customerReviewRAOConverter;
	}

	@Required
	public void setCustomerReviewRAOConverter(Converter<CustomerReviewModel, CustomerReviewRAO> reviewRAOConverter) {
		this.customerReviewRAOConverter = reviewRAOConverter;
	}
}
