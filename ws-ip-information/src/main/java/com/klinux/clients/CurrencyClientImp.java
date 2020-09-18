package com.klinux.clients;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jayway.jsonpath.internal.Utils;
import com.klinux.constants.Constantes;
import com.klinux.exception.ResourceNotFoundException;

@Service
public class CurrencyClientImp implements CurrencyClientRest {

	private static Logger log = LoggerFactory.getLogger(CurrencyClientImp.class);

	@Autowired
	private CurrencyClientRest request;

	@Override
	public String getCurrencyByCountryName(String countryName) throws ResourceNotFoundException {
		String jsonCurrency = "";
		try {
			jsonCurrency = request.getCurrencyByCountryName(countryName);
			if (Utils.isEmpty(jsonCurrency)) {
				log.error(Constantes.WS_CONVERSION_NOT_FOUND);
				throw new ResourceNotFoundException(Constantes.WS_CONVERSION_NOT_FOUND);
			}
//		} catch (ResourceNotAvailableException e) {
//			throw new ResourceNotAvailableException("" + e.getMessage());
//		} catch (ResourceNotFoundException e) {
//			throw new ResourceNotFoundException("" + e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ResourceNotFoundException("" + e.getMessage());
		}
		return jsonCurrency;
	}
}