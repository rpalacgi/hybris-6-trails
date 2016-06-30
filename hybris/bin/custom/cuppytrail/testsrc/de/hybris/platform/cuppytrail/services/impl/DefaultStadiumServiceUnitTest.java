package de.hybris.platform.cuppytrail.services.impl;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.cuppytrail.constants.TestData.StadiumData;
import de.hybris.platform.cuppytrail.daos.StadiumDAO;
import de.hybris.platform.cuppytrail.model.StadiumModel;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class DefaultStadiumServiceUnitTest {


	@Mock
	private StadiumDAO stadiumDAO;

	@InjectMocks
	private DefaultStadiumService stadiumService = new DefaultStadiumService();

	private StadiumModel testStadium;

	@Before
	public void setUp() {
		testStadium = StadiumData.createTestStadiumModel();
	}

	@Test
	public void testGetStadiums() {
		when(stadiumDAO.findStadiums()).thenReturn(Collections.singletonList(testStadium));
		List<StadiumModel> stadiums = stadiumService.getStadiums();
		assertTrue("Must be one stadium in result", stadiums.size() == 1);
		assertEquals(testStadium, stadiums.get(0));
	}

	@Test
	public void testGetStadiumByCode() {
		when(stadiumDAO.getStadiumByCode(StadiumData.STADIUM_NAME)).thenReturn(testStadium);
		Optional<StadiumModel> stadiumOpt = stadiumService.getStadiumByCode(StadiumData.STADIUM_NAME);
		assertTrue("Result of stadium service must be present", stadiumOpt.isPresent());
		assertEquals(stadiumOpt.get(), testStadium);
	}

	@Test
	public void testGetNonExistStadiumByCode() {
		when(stadiumDAO.getStadiumByCode(StadiumData.STADIUM_NAME)).thenThrow(new ModelNotFoundException(StringUtils.EMPTY));
		Optional<StadiumModel> stadiumOpt = stadiumService.getStadiumByCode(StadiumData.STADIUM_NAME);
		assertFalse("Result of stadium service must be absent", stadiumOpt.isPresent());
	}

}