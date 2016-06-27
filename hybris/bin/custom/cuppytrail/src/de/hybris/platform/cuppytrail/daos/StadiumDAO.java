package de.hybris.platform.cuppytrail.daos;

import de.hybris.platform.cuppytrail.model.StadiumModel;

import java.util.List;

public interface StadiumDAO {


	List<StadiumModel> findStadiums();

	List<StadiumModel> fundStadiumByCode(String code);

}
