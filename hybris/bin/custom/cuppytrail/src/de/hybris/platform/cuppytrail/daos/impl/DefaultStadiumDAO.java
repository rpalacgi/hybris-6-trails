package de.hybris.platform.cuppytrail.daos.impl;

import de.hybris.platform.cuppytrail.daos.StadiumDAO;
import de.hybris.platform.cuppytrail.model.StadiumModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static de.hybris.platform.cuppytrail.utils.GStringUtils.of;

@Repository("stadiumDAO")
public class DefaultStadiumDAO implements StadiumDAO {

	private static final Map<String, ?> STADIUM_TYPE =
			Collections.unmodifiableMap(Collections.singletonMap("stadium", StadiumModel.class));

	private static final String ALL_STADIUMS_QUERY =
			of("SELECT {p:$stadium.PK} FROM {$stadium._TYPECODE AS p}", STADIUM_TYPE);

	private static final String STADIUMS_BY_CODE_QUERY = of(
			"SELECT {p:$stadium.PK} FROM {$stadium._TYPECODE AS p} WHERE {p:$stadium.CODE}=?code", STADIUM_TYPE);

	@Autowired
	private FlexibleSearchService flexibleSearchService;

	@Override
	public List<StadiumModel> findStadiums() {
		return search(new FlexibleSearchQuery(ALL_STADIUMS_QUERY));
	}

	@Override
	public StadiumModel getStadiumByCode(String code) {
		FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery(STADIUMS_BY_CODE_QUERY);
		flexibleSearchQuery.addQueryParameter("code", code);
		return peek(flexibleSearchQuery);
	}

	private List<StadiumModel> search(FlexibleSearchQuery query) {
		return flexibleSearchService.<StadiumModel>search(query).getResult();
	}

	private StadiumModel peek(FlexibleSearchQuery query) {
		return flexibleSearchService.searchUnique(query);
	}
}
