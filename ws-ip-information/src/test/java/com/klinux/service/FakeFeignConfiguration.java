package com.klinux.service;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@EnableFeignClients(clients = FakeBanIpClientRest.class)
@EnableAutoConfiguration
@RibbonClient(name = "restClient", configuration = FakeRibbonConfiguration.class)
public class FakeFeignConfiguration {
}
