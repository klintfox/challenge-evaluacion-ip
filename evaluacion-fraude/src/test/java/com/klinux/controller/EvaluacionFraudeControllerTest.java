package com.klinux.controller;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.klinux.service.CountryService;

@RunWith(SpringRunner.class)
@SpringBootTest
class EvaluacionFraudeControllerTest {

	@Autowired
	@Qualifier("serviceFeign")
	private CountryService contryService;


}