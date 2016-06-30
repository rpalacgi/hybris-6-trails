package de.hybris.platform.cuppytrail.services.impl;

import de.hybris.platform.cuppytrail.daos.StadiumDAO;
import de.hybris.platform.cuppytrail.model.StadiumModel;
import de.hybris.platform.cuppytrail.services.StadiumService;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("stadiumService")
public class DefaultStadiumService implements StadiumService {

	@Autowired
	private StadiumDAO stadiumDAO;

	@Override
	public List<StadiumModel> getStadiums() {
		return stadiumDAO.findStadiums();
	}

	@Override
	public Optional<StadiumModel> getStadiumByCode(String code) {
		Optional<StadiumModel> result;
		try {
			result = Optional.of(stadiumDAO.getStadiumByCode(code));
		} catch (ModelNotFoundException e) {
			result = Optional.empty();
		}
		return result;
	}
}

