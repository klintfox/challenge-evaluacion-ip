package com.klinux.clients;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jayway.jsonpath.internal.Utils;
import com.klinux.constants.Constantes;
import com.klinux.exception.ResourceNotFoundException;

@Service
public class BanIpClientImp implements BanIpClientRest {

	private static Logger log = LoggerFactory.getLogger(BanIpClientImp.class);

	@Autowired
	private BanIpClientRest request;

	@Override
	public String isBanned(String ip) throws ResourceNotFoundException {
		String typeIp = "";
		try {
			typeIp = request.isBanned(ip);
			if (Utils.isEmpty(typeIp)) {
				log.error(Constantes.WS_BAN_IP_NOT_FOUND);
				throw new ResourceNotFoundException(Constantes.WS_BAN_IP_NOT_FOUND);
			}
//		} catch (ResourceNotAvailableException e) {
//			throw new ResourceNotAvailableException("" + e.getMessage());
//		} catch (ResourceNotFoundException e) {
//			throw new ResourceNotFoundException("" + e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ResourceNotFoundException("" + e.getMessage());
		}
		return typeIp;
	}
}