package de.hybris.platform.cuppytrailfrontend.controllers;

import de.hybris.platform.cuppytrail.data.StadiumData;
import de.hybris.platform.cuppytrail.facades.StadiumFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Optional;

@Controller
public class StadiumController {

	@Autowired
	private StadiumFacade stadiumFacade;

	@RequestMapping(value = "/stadiums")
	public String showsStadium(Model model) {
		List<StadiumData> stadiums = stadiumFacade.getStadiums();
		model.addAttribute("stadiums", stadiums);
		return "StadiumListing";
	}


	@RequestMapping(value = "/stadiums/{stadiumName}")
	public String showStadiumDetails(@PathVariable String stadiumName, Model model) throws UnsupportedEncodingException {
		String name = URLDecoder.decode(stadiumName, "UTF-8");
		Optional<StadiumData> result = stadiumFacade.getStadium(stadiumName);
		StadiumData stadium = result.orElseThrow(
				() -> new IllegalArgumentException("No stadium was found by " + name + " name"));
		stadium.setName(name);
		model.addAttribute("stadium", stadium);
		return "StadiumDetails";
	}


}

