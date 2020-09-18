package com.klinux.clients;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import com.klinux.exception.ResourceNotAvailableException;
import com.klinux.exception.ResourceNotFoundException;

@RunWith(SpringRunner.class)
public class CurrencyClientRestTest {

	@Autowired
	private CurrencyClientRest currencyFeing;
		

	@Test
	void testGetCurrencyByCountryName() throws ResourceNotFoundException, ResourceNotAvailableException {
		
//		String json = {"name":"Peru","topLevelDomain":[".pe"],"alpha2Code":"PE","alpha3Code":"PER","callingCodes":["51"],"capital":"Lima","altSpellings":["PE","Republic of Peru"," República del Perú"],"region":"Americas","subregion":"South America","population":31488700,"latlng":[-10,-76],"demonym":"Peruvian","area":1285216,"gini":48.1,"timezones":["UTC-05:00"],"borders":["BOL","BRA","CHL","COL","ECU"],"nativeName":"Perú","numericCode":"604","currencies":[{"code":"PEN","name":"Peruvian sol","symbol":"S/."}],"languages":[{"iso639_1":"es","iso639_2":"spa","name":"Spanish","nativeName":"Español"}],"translations":{"de":"Peru","es":"Perú","fr":"Pérou","ja":"ペルー","it":"Perù","br":"Peru","pt":"Peru","nl":"Peru","hr":"Peru","fa":"پرو"},"flag":"https://restcountries.eu/data/per.svg","regionalBlocs":[{"acronym":"PA","name":"Pacific Alliance","otherAcronyms":[],"otherNames":["Alianza del Pacífico"]},{"acronym":"USAN","name":"Union of South American Nations","otherAcronyms":["UNASUR","UNASUL","UZAN"],"otherNames":["Unión de Naciones Suramericanas","União de Nações Sul-Americanas","Unie van Zuid-Amerikaanse Naties","South American Union"]}],"cioc":"PER"};

		String countryName = "Colombia";
		String jsonCurrency = currencyFeing.getCurrencyByCountryName(countryName);
		assertTrue(jsonCurrency.toString().length() > 0);
	}

}
