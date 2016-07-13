package de.hybris.platform.cuppytrail.interceptors;

import com.google.common.base.Preconditions;
import de.hybris.platform.cuppytrail.events.CapacityEvent;
import de.hybris.platform.cuppytrail.model.StadiumModel;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;
import de.hybris.platform.servicelayer.interceptor.ValidateInterceptor;
import org.springframework.beans.factory.annotation.Required;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Objects;
import java.util.function.Predicate;

import static de.hybris.platform.servicelayer.model.ModelContextUtils.getItemModelContext;
import static java.util.Objects.nonNull;
import static org.bouncycastle.asn1.x500.style.RFC4519Style.st;


public class CapacityInterceptor implements ValidateInterceptor<StadiumModel>, PrepareInterceptor<StadiumModel> {

	private int stadiumBigCapacity;

	private int stadiumMaxCapacity;

	private Predicate<StadiumModel> maxCapacityCheck;

	@Resource
	private EventService eventService;

	@PostConstruct
	private void init() {
		maxCapacityCheck =  stadium -> nonNull(stadium.getCapacity()) &&stadium.getCapacity() > stadiumMaxCapacity;
	}



	@Override
	public void onPrepare(StadiumModel stadium, InterceptorContext ctx) throws InterceptorException {
		if (hasBecomeBig(stadium, ctx)) {
			eventService.publishEvent(new CapacityEvent(stadium.getCapacity(), stadium.getCode()));
		}
	}


	private boolean hasBecomeBig(final StadiumModel stadium, final InterceptorContext ctx) {
		Predicate<Integer> checkCapacity = capacity ->
				Objects.nonNull((capacity)) && capacity >= stadiumBigCapacity;

		if (checkCapacity.test(stadium.getCapacity())) {
			if (ctx.isNew(stadium)) {
				return true;
			} else {
				if (checkCapacity.test(getItemModelContext(stadium).getOriginalValue(StadiumModel.CAPACITY))) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void onValidate(StadiumModel stadium, InterceptorContext ctx) throws InterceptorException {
		if (maxCapacityCheck.test(stadium)) {
			throw new InterceptorException(
					String.format("Capacity on the stadium '%s' is to large[Actual: %d, Max: %d]",
								  stadium.getCode(), stadium.getCapacity(), stadiumMaxCapacity));
		}
	}

	@Required
	private void setStadiumBigCapacity(int stadiumBigCapacity) {
		Preconditions.checkArgument(stadiumBigCapacity > 0, "Stadium capacity must be positive");
		this.stadiumBigCapacity = stadiumBigCapacity;
	}

	@Required
	private void setStadiumMaxCapacity(int stadiumMaxCapacity) {
		Preconditions.checkArgument(stadiumMaxCapacity > 0, "Stadium capacity must be positive");
		this.stadiumMaxCapacity = stadiumMaxCapacity;
	}


}
