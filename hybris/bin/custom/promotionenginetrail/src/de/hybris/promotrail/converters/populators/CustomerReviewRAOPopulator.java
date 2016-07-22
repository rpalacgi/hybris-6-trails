package de.hybris.promotrail.converters.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.customerreview.model.CustomerReviewModel;
import de.hybris.platform.ruleengineservices.rao.ProductRAO;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.promotrail.rao.CustomerReviewRAO;
import org.springframework.beans.factory.annotation.Required;

public class CustomerReviewRAOPopulator implements Populator<CustomerReviewModel, CustomerReviewRAO> {


	private Converter<ProductModel, ProductRAO> productRAOConverter;

	@Override
	public void populate(CustomerReviewModel source, CustomerReviewRAO target) throws ConversionException {
		target.setProduct(productRAOConverter.convert(source.getProduct()));
	}

	public Converter<ProductModel, ProductRAO> getProductRAOConverter() {
		return productRAOConverter;
	}

	@Required
	public void setProductRAOConverter(Converter<ProductModel, ProductRAO> productRAOConverter) {
		this.productRAOConverter = productRAOConverter;
	}
}
