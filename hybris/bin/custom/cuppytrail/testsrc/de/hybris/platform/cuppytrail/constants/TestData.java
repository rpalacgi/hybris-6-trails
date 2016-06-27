package de.hybris.platform.cuppytrail.constants;

import de.hybris.platform.cuppytrail.model.StadiumModel;

public final class TestData {

	public static final class StadiumData {

		public static final String STADIUM_NAME = "webley";

		public static final int STADIUM_CAPACITY = 12345;

		public static StadiumModel createTestStadiumModel() {
			StadiumModel newStadium = new StadiumModel();
			newStadium.setCode(STADIUM_NAME);
			newStadium.setCapacity(STADIUM_CAPACITY);
			return newStadium;
		}

		private StadiumData() {}
	}


	private TestData() {}
}

