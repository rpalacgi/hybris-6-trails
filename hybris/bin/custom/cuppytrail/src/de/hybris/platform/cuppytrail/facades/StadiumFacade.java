package de.hybris.platform.cuppytrail.facades;

import de.hybris.platform.cuppytrail.data.StadiumData;

import java.util.List;
import java.util.Optional;

public interface StadiumFacade {

	Optional<StadiumData> getStadium(String name);

	List<StadiumData> getStadiums();
}
