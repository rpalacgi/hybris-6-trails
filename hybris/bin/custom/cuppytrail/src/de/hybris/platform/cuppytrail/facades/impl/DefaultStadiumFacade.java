package de.hybris.platform.cuppytrail.facades.impl;

import com.google.common.collect.ImmutableMap;
import de.hybris.platform.cuppy.model.MatchModel;
import de.hybris.platform.cuppytrail.data.MatchSummaryData;
import de.hybris.platform.cuppytrail.data.StadiumData;
import de.hybris.platform.cuppytrail.facades.StadiumFacade;
import de.hybris.platform.cuppytrail.model.StadiumModel;
import de.hybris.platform.cuppytrail.services.StadiumService;
import de.hybris.platform.cuppytrail.utils.GStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DefaultStadiumFacade implements StadiumFacade {

	@Resource
	private StadiumService stadiumService;

	private final DateTimeFormatter formatter =
			DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT);

	@Override
	public Optional<StadiumData> getStadium(String name) {
		return stadiumService.getStadiumByCode(name).map(this::convert);
	}

	@Override
	public List<StadiumData> getStadiums() {
		return stadiumService.getStadiums().stream().map(this::convert).collect(Collectors.toList());
	}


	private StadiumData convert(StadiumModel model) {
		if (model == null) return null;
		StadiumData result = new StadiumData();
		result.setName(model.getCode());
		result.setCapacity(model.getCapacity() == null ? null : model.getCapacity().toString());
		result.setMatches(model.getMatches().stream().map(this::convert).collect(Collectors.toList()));
		return result;
	}

	private MatchSummaryData convert(MatchModel model) {
		if (model == null) return null;
		MatchSummaryData data = new MatchSummaryData();

		if (model.getDate() != null) {
			data.setDate(LocalDateTime.ofInstant(model.getDate().toInstant(), ZoneId.systemDefault()));
		}

		String guestGoals = model.getGuestGoals() == null ? "-" : model.getGuestGoals().toString();
		data.setGuestGoals(guestGoals);

		String homeGoals = model.getHomeGoals() == null ? "-" : model.getHomeGoals().toString();
		data.setHomeGoals(homeGoals);

		data.setHomeTeam(model.getHomeTeam().getName());
		data.setGuestTeam(model.getGuestTeam().getName());
		data.setMatchSummaryFormatted(formatMatchSummary(data));
		return data;
	}

	private String formatMatchSummary(MatchSummaryData data) {
		String formattedDate = data.getDate() == null ? StringUtils.EMPTY : formatter.format(data.getDate());
		return GStringUtils.of("$data.homeTeam:( $data.homeGoals ) $data.guestTeam ( $data.guestGoals ) $formattedDate",
							   ImmutableMap.of("data", data, "formattedDate", formattedDate));
	}

}
