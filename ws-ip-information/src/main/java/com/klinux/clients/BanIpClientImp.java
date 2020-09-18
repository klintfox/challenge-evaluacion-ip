package com.klinux.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jayway.jsonpath.internal.Utils;
import com.klinux.constants.Constantes;
import com.klinux.exception.ResourceForbiddenException;
import com.klinux.exception.ResourceNotAvailableException;
import com.klinux.exception.ResourceNotFoundException;

@Service
public class BanIpClientImp implements BanIpClientRest {

	@Autowired
	private BanIpClientRest request;

	@Override
	public String isBanned(String ip)
			throws ResourceNotFoundException, ResourceNotAvailableException, ResourceForbiddenException {
		String typeIp = "";
		try {
			typeIp = request.isBanned(ip);
			if (Utils.isEmpty(typeIp)) {
				throw new ResourceNotFoundException(Constantes.WS_BAN_IP_NOT_FOUND);
			}
			if (typeIp.equals(Constantes.BANNED)) {
				throw new ResourceForbiddenException(Constantes.IP_IS_BAN);
			}
		} catch (ResourceForbiddenException e) {
			throw new ResourceForbiddenException(Constantes.IP_IS_BAN);
		} catch (ResourceNotAvailableException e) {
			throw new ResourceNotAvailableException("" + e.getMessage());
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException("" + e.getMessage());
		} catch (Exception e) {
			throw new ResourceNotFoundException("" + e.getMessage());
		}
		return typeIp;
	}

	public BanIpClientRest getRequest() {
		return request;
	}

	public void setRequest(BanIpClientRest request) {
		this.request = request;
	}
}