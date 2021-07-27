package com.ncs.xmlprocessor.processor;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import com.ncs.tellerapplication.model.Account;


@Component
public class XmlProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		

		
		
		JAXBContext jc = JAXBContext.newInstance(Account.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        StreamSource streamSource = new StreamSource(new StringReader(exchange.getIn().getBody().toString()));
        JAXBElement<Account> jaxbElement = unmarshaller.unmarshal(streamSource, Account.class);

        Account account = jaxbElement.getValue();
        System.out.println("");
        
        
        // Save account along with transactions !!
        
        
		
		
  
		
		
	}

}
