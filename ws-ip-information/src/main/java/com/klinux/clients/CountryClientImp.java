package com.klinux.clients;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klinux.constants.Constantes;
import com.klinux.dto.CountryDto;
import com.klinux.exception.ResourceNotFoundException;

@Service
public class CountryClientImp implements CountryClientRest {

	private static Logger log = LoggerFactory.getLogger(CountryClientImp.class);

	@Autowired
	private CountryClientRest request;

	@Override
	public CountryDto getCountryDetail(String ip) throws ResourceNotFoundException {
		CountryDto country = null;
		try {
			country = request.getCountryDetail(ip);
			if (country == null) {
				log.error(Constantes.WS_CURRENCY_NOT_FOUND);
				throw new ResourceNotFoundException(Constantes.WS_CURRENCY_NOT_FOUND);
			}
//		} catch (ResourceNotAvailableException e) {
//			throw new ResourceNotAvailableException("" + e.getMessage());
//		} catch (ResourceNotFoundException e) {
//			throw new ResourceNotFoundException("" + e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ResourceNotFoundException("" + e.getMessage());
		}
		return country;
	}
}