package com.klinux.clients;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.klinux.dto.Country;

@RunWith(SpringRunner.class)
@SpringBootTest
class CountryClientRestTest {

	@Autowired
	private CountryClientRest countryFeign;

	@Test
	void testGetCountryDetail() {
		String ip = "186.84.91.60";
		Country country = countryFeign.getCountryDetail(ip);
		assertTrue(country.toString().length() > 0);
	}

}