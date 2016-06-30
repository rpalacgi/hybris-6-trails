package de.hybris.platform.cuppytrail.services;

import de.hybris.platform.cuppytrail.model.StadiumModel;

import java.util.List;
import java.util.Optional;

public interface StadiumService {

	List<StadiumModel> getStadiums();

	Optional<StadiumModel> getStadiumByCode(String code);
}
