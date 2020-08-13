package com.klinux.clients;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.klinux.dto.Country;
import com.klinux.dto.FraudeResponseDto;
import com.klinux.service.CountryService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CountryClientRestTest {
	
	@Autowired
	@Qualifier("serviceFeign")
	private CountryService contryService;

	@Autowired
	private CountryClientRest countryFeign;

	@Before
	@DisplayName("testController")
	public void testEvaluacionFraude() throws Exception {
		String ip = "186.84.91.60";
		FraudeResponseDto response = contryService.getInfo(ip);
		assertTrue(response.toString().length() > 0);
	}

	@After
	@DisplayName("testCountryDetail")
	public void testGetCountryDetail() {
		String ip = "186.84.91.60";
		Country country = countryFeign.getCountryDetail(ip);
		assertTrue(country.toString().length() > 0);
	}
}
