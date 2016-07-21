package de.hybris.platform.cuppytrail.events;

import de.hybris.platform.cuppy.model.NewsModel;
import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;
import de.hybris.platform.servicelayer.model.ModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

import static de.hybris.platform.cuppytrail.utils.GStringUtils.of;
import static java.util.Collections.singletonMap;

public class StadiumEventListener extends AbstractEventListener<CapacityEvent> {

	private static final Logger log = LoggerFactory.getLogger(StadiumEventListener.class);

	@Resource
	private ModelService modelService;

	@Override
	public void onEvent(CapacityEvent event) {
		try {
			handleEvent(event);
		} catch (InterruptedException ignored) {}

	}

	private void handleEvent(CapacityEvent event) throws InterruptedException {
		if (log.isDebugEnabled()) {
			log.debug("### Entering event handler");
		}
		TimeUnit.SECONDS.sleep(2);
		NewsModel news = modelService.create(NewsModel.class);
		String content = of("New big stadium; Code: $e.code, capacity: $e.capacity", singletonMap("e", event));
		news.setContent(content);
		modelService.save(news);
		if (log.isDebugEnabled()) {
			log.debug("### New was created with content: {}", content);
			log.debug("### Event handling is finished");
		}
	}


}
