package com.klinux.clients;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jayway.jsonpath.internal.Utils;
import com.klinux.constants.Constantes;
import com.klinux.exception.ResourceNotFoundException;

@Service
public class ConversionClientImp implements ConversionClientRest {

	private static Logger log = LoggerFactory.getLogger(ConversionClientImp.class);

	@Autowired
	private ConversionClientRest request;

	@Override
	public String getCurrencyDetail(String currencyCode) throws ResourceNotFoundException {
		String jsonConversion = "";
		try {
			jsonConversion = request.getCurrencyDetail(currencyCode);
			if (Utils.isEmpty(jsonConversion)) {
				log.error(Constantes.WS_COUNTRY_NOT_FOUND);
				throw new ResourceNotFoundException(Constantes.WS_COUNTRY_NOT_FOUND);
			}
//		} catch (ResourceNotAvailableException e) {
//			throw new ResourceNotAvailableException("" + e.getMessage());
//		} catch (ResourceNotFoundException e) {
//			throw new ResourceNotFoundException("" + e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ResourceNotFoundException("" + e.getMessage());
		}
		return jsonConversion;
	}
}