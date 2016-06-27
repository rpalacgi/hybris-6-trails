package de.hybris.platform.cuppytrail.daos.impl;

import de.hybris.platform.cuppytrail.daos.StadiumDAO;
import de.hybris.platform.cuppytrail.model.StadiumModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static de.hybris.platform.cuppytrail.utils.GStringUtils.of;

@Repository("stadiumDAO")
public class DefaultStadiumDAO implements StadiumDAO {

	private static final Map<String, ?> STADIUM_TYPE = Collections.singletonMap("stadium", StadiumModel.class);

	private static final String ALL_STADIUMS_QUERY = of("SELECT {p:$stadium.PK} FROM {$stadium._TYPECODE AS p}", STADIUM_TYPE);

	private static final String STADIUMS_BY_CODE_QUERY = of(
			"SELECT {p:$stadium.PK} FROM {$stadium._TYPECODE AS p} WHERE {p:$stadium.CODE}=?code", STADIUM_TYPE);

	@Resource
	private FlexibleSearchService flexibleSearchService;

	@Override
	public List<StadiumModel> findStadiums() {
		System.out.println(ALL_STADIUMS_QUERY);
		System.out.println(of("SELECT {p:$stadium.PK} FROM {$stadium._TYPECODE AS p}", STADIUM_TYPE));
		return search(new FlexibleSearchQuery("SELECT {p:"+ StadiumModel.PK+"} FROM {"+StadiumModel._TYPECODE+" AS p}"));
	}

	@Override
	public List<StadiumModel> fundStadiumByCode(String code) {
		System.out.println(STADIUMS_BY_CODE_QUERY);
		FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery(STADIUMS_BY_CODE_QUERY);
		flexibleSearchQuery.addQueryParameter("code", code);
		return search(flexibleSearchQuery);
	}

	private List<StadiumModel> search(FlexibleSearchQuery query) {
		return flexibleSearchService.<StadiumModel>search(query).getResult();
	}
}
