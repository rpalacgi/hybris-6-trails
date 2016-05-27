package de.hybris.platform.cuppytrail.daos;

import de.hybris.platform.cuppytrail.model.StadiumModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

public class StadiumDAOIntegrationTest extends ServicelayerTransactionalTest {

	@Resource
	private StadiumDAO stadiumDAO;

	@Resource
	private StadiumDAO stadiumDAO;

	private static final String STADIUM_NAME = "webley";

	private static final Integer STADIUM_CAPACITY = 12345;

	@Test
	public void findStadiums() {
		List<StadiumModel> stadiums = stadiumDAO.findStadiums();
		assertTrue("No statdiun should be returned", CollectionUtils.isEmpty(stadiums));
	}

	@Test
	public void fundStadiumByCode() throws Exception {

	}

}