package com.klinux.clients;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.klinux.exception.ResourceNotAvailableException;
import com.klinux.exception.ResourceNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
class ConversionClientRestTest {

	@Autowired
	private ConversionClientRest conversionFeing;

	@Test
	void testGetCurrencyDetail() throws ResourceNotFoundException, ResourceNotAvailableException {		
		String jsonConversion = conversionFeing.getCurrencyDetail("COP");
		assertTrue(jsonConversion.length() > 0);
	}

}
