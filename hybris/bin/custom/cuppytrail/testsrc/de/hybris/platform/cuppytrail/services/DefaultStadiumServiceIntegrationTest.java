package de.hybris.platform.cuppytrail.services;

import de.hybris.platform.cuppytrail.constants.TestData.StadiumData;
import de.hybris.platform.cuppytrail.model.StadiumModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class DefaultStadiumServiceIntegrationTest extends ServicelayerTransactionalTest {


	@Resource
	private StadiumService stadiumService;

	@Resource
	private ModelService modelService;

	private StadiumModel testStadiumModel;

	@Before
	public void setUp() {
		testStadiumModel = StadiumData.createTestStadiumModel();
	}


	@Test
	public void testGetNonExistStadiumByCode() {
		assertFalse("Stadium should not be present", stadiumService.getStadiumByCode(StadiumData.STADIUM_NAME).isPresent());
	}

	@Test
	public void testGetStadiums() {
		List<StadiumModel> stadiums = stadiumService.getStadiums();
		assertTrue("Unexpected stadium found", stadiums.isEmpty());

		modelService.save(testStadiumModel);

		stadiums = stadiumService.getStadiums();
		assertTrue("Unexpected number of result; Expected: 1 Stadium", stadiums.size() == 1);

		StadiumModel stadium = stadiums.get(0);
		assertEquals(StadiumData.STADIUM_NAME, stadium.getCode());
		assertEquals(Integer.valueOf(StadiumData.STADIUM_CAPACITY), stadium.getCapacity());
	}

	@Test
	public void testGetStadiumByCode() {
		modelService.save(testStadiumModel);
		Optional<StadiumModel> stadiumOpt = stadiumService.getStadiumByCode(StadiumData.STADIUM_NAME);
		assertTrue("Stadium model must be present in result", stadiumOpt.isPresent());
		StadiumModel stadium = stadiumOpt.get();
		assertEquals(StadiumData.STADIUM_NAME, stadium.getCode());
		assertEquals(Integer.valueOf(StadiumData.STADIUM_CAPACITY), stadium.getCapacity());
	}

}