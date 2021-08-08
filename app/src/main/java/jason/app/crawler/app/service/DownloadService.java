package jason.app.crawler.app.service;

import java.io.IOException;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jason.app.crawler.app.model.Request;

@Service("downloader")
public class DownloadService {

	private static final Logger logger = LoggerFactory.getLogger(DownloadService.class);
	
	public void selenium(Exchange exchange) throws IOException{
        Request payload = exchange.getIn().getBody(Request.class);
        logger.info("selenium");
        exchange.getIn().setBody("selenium");
	}
	
	public void curl(Exchange exchange) throws IOException{
        Request payload = exchange.getIn().getBody(Request.class);
        logger.info("curl");
        exchange.getIn().setBody("curl");
	}
	
	public void httpclient(Exchange exchange) throws IOException{
        Request payload = exchange.getIn().getBody(Request.class);
        logger.info("httpclient");
//        throw new IOException("failed to download");
        exchange.getIn().setBody("httpclient");
	}
}
