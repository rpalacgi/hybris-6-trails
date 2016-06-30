package de.hybris.platform.cuppytrail.daos;

import de.hybris.platform.cuppytrail.constants.TestData.StadiumData;
import de.hybris.platform.cuppytrail.model.StadiumModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.model.ModelService;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class StadiumDAOIntegrationTest extends ServicelayerTransactionalTest {

	@Resource
	private StadiumDAO stadiumDAO;

	@Resource
	private ModelService modelService;

	private void createTestStadium() {
		modelService.save(StadiumData.createTestStadiumModel());
	}

	@Test
	public void testFindStadiums() {
		List<StadiumModel> stadiums = stadiumDAO.findStadiums();
		stadiums.forEach(stadium -> System.out.println("stadium = " + stadium.getCode()));
		assertTrue("No statdiun should be returned", stadiums.isEmpty());
		createTestStadium();
		stadiums = stadiumDAO.findStadiums();
		assertThat("Should be result with single stadium", stadiums.size(), is(1));
		checkStadiumModel(stadiums.get(0));
	}

	@Test(expected = ModelNotFoundException.class)
	public void testGetNonExistStadiumByCode() {
		stadiumDAO.getStadiumByCode(StadiumData.STADIUM_NAME);
	}

	@Test(expected = ModelNotFoundException.class)
	public void testGetStadiumWithEmptyCode() {
		stadiumDAO.getStadiumByCode(StringUtils.EMPTY);
	}

	@Test(expected = ModelNotFoundException.class)
	public void testGetStadiumWithNullCode() {
		stadiumDAO.getStadiumByCode(null);
	}

	@Test
	public void testGetStadiumByCode() {
		createTestStadium();
		StadiumModel stadium = stadiumDAO.getStadiumByCode(StadiumData.STADIUM_NAME);
		checkStadiumModel(stadium);
	}

	private void checkStadiumModel(StadiumModel stadium) {
		assertEquals(stadium.getCode(), StadiumData.STADIUM_NAME);
		assertEquals(stadium.getCapacity(), Integer.valueOf(StadiumData.STADIUM_CAPACITY));
	}
}