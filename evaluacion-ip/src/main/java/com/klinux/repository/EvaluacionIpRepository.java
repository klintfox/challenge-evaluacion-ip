package com.klinux.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.klinux.model.BlackList;

@Repository
public interface EvaluacionIpRepository extends CrudRepository<BlackList, Long>{

	BlackList findByIp(String ip);

}