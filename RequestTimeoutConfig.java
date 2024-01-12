package kr.co.kbs.news.api.config;

import org.apache.catalina.valves.StuckThreadDetectionValve;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RequestTimeoutConfig {
	final private int timeoutSeconds = 20;

	@Bean
    StuckThreadDetectionValve stuckThreadDetectionValve() {
		StuckThreadDetectionValve stuckThreadDetectionValve = new StuckThreadDetectionValve();

		stuckThreadDetectionValve.setThreshold(timeoutSeconds);
		stuckThreadDetectionValve.setInterruptThreadThreshold(timeoutSeconds);
		stuckThreadDetectionValve.setThrowOnFailure(true);

		return stuckThreadDetectionValve;
	}

	@Bean
    WebServerFactoryCustomizer<TomcatServletWebServerFactory> stuckThreadDetectionWebServerFactoryCustomizer(StuckThreadDetectionValve valve){
		WebServerFactoryCustomizer<TomcatServletWebServerFactory> webServerFactoryCustomizer = new WebServerFactoryCustomizer<TomcatServletWebServerFactory>() {
			@Override
			public void customize(TomcatServletWebServerFactory factory) {
				factory.addContextValves(valve);
			}
		};
		
		return webServerFactoryCustomizer;
	}
}
