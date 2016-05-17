package com.cassandrawebtrader.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.messaging.MessageChannel;

@Configuration
@IntegrationComponentScan("com.cassandrawebtrader")
@EnableIntegration
public class IntegrationConfig {
	
	@Bean(name = "notifyChannel")
	public MessageChannel emailChannel() {
		return new DirectChannel();
	}

}
