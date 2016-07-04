package de.hybris.platform.cuppytrail.facades;

import de.hybris.platform.cuppytrail.constants.TestData;
import de.hybris.platform.cuppytrail.data.StadiumData;
import de.hybris.platform.cuppytrail.model.StadiumModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class DefaultStadiumFacadeIntegrationTest extends ServicelayerTransactionalTest {

	@Resource
	private StadiumFacade stadiumFacade;

	@Resource
	private ModelService modelService;

	private StadiumModel stadiumModel;

	@Before
	public void setUp() {
		stadiumModel = TestData.StadiumData.createTestStadiumModel();
	}

	@Test
	public void testGetInexistentStadium() {
		Optional<StadiumData> result = stadiumFacade.getStadium(TestData.StadiumData.STADIUM_NAME);
		assertFalse("Data models should not be found", result.isPresent());
	}

	@Test
	public void testGetStadium() {
		modelService.save(stadiumModel);
		Optional<StadiumData> result = stadiumFacade.getStadium(TestData.StadiumData.STADIUM_NAME);
		assertTrue("Should be non empty result", result.isPresent());
		StadiumData stadiumData = result.get();
		assertEquals(stadiumData.getName(), TestData.StadiumData.STADIUM_NAME);
		assertEquals(stadiumData.getCapacity(), String.valueOf(TestData.StadiumData.STADIUM_CAPACITY));
	}

	@Test
	public void testGetStadiums() {
		modelService.save(stadiumModel);
		List<StadiumData> stadiums = stadiumFacade.getStadiums();
		assertTrue("Should be result with single element", stadiums.size() == 1);
		StadiumData stadiumData = stadiums.get(0);
		assertEquals(stadiumData.getName(), TestData.StadiumData.STADIUM_NAME);
		assertEquals(stadiumData.getCapacity(), String.valueOf(TestData.StadiumData.STADIUM_CAPACITY));
	}
	}

}