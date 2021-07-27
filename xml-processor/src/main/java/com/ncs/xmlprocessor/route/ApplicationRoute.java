package com.ncs.xmlprocessor.route;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ncs.xmlprocessor.processor.XmlProcessor;

@Component
public class ApplicationRoute extends RouteBuilder{

	@Autowired
	XmlProcessor xmlProcessor;
	
	static final Logger log = LoggerFactory.getLogger(ApplicationRoute.class);
	
	
	@Override
	public void configure() throws Exception {
		
		from("jms:testQueue")
		 .transacted()
		 .log(LoggingLevel.INFO, log,"Message recieved !")
		 .process(xmlProcessor);
		
		
		
	}
	
	

}
