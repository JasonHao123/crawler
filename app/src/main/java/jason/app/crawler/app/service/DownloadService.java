package jason.app.crawler.app.service;

import java.io.IOException;

import org.apache.camel.Exchange;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jason.app.crawler.app.model.Request;

@Service("downloader")
public class DownloadService {

	private static final Logger logger = LoggerFactory.getLogger(DownloadService.class);

	public void selenium(Exchange exchange) throws IOException {
		Request payload = exchange.getIn().getBody(Request.class);
		logger.info("selenium");
		exchange.getIn().setBody("selenium");
	}

	public void curl(Exchange exchange) throws IOException {
		Request payload = exchange.getIn().getBody(Request.class);
		logger.info("curl");
		exchange.getIn().setBody("curl");
	}

	public void httpclient(Exchange exchange) throws IOException {
		Request payload = exchange.getIn().getBody(Request.class);
		logger.info("httpclient");
//        throw new IOException("failed to download");
		CloseableHttpClient httpclient = HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy()).build();
		try {
			HttpGet get = new HttpGet(payload.getUrl()); // we're using GET but it could be via POST as well
			HttpResponse response = httpclient.execute(get);
			if(response.getStatusLine().getStatusCode() < 200 && response.getStatusLine().getStatusCode() > 299) {
				throw new IOException("download failed");
			}
			exchange.getIn().setBody(EntityUtils.toString(response.getEntity()));
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			IOUtils.closeQuietly(httpclient);
		}		
	}
}
