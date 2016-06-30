package de.hybris.platform.cuppytrail.daos;

import de.hybris.platform.cuppytrail.model.StadiumModel;

import java.util.List;

public interface StadiumDAO {


	List<StadiumModel> findStadiums();

	StadiumModel getStadiumByCode(String code);

}
