package jp.ddo.chiroru.bookshelf.spring;

import java.util.Arrays;

import javax.ws.rs.core.MediaType;

import jp.ddo.chiroru.bookshelf.util.ServletContainerResource;

import org.apache.cxf.common.util.Base64Utility;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.junit.ClassRule;
import org.junit.Test;

public class BookshelfWebResourceTest {

    private final static String ENDPOINT_ADDRESS = "http://127.0.0.1:8080/bookshelf";

    @ClassRule
    public static ServletContainerResource server = new ServletContainerResource();
    
    @Test
    public void getBookshelfById() {
        JacksonJsonProvider p = new JacksonJsonProvider();
        WebClient client = WebClient.create(ENDPOINT_ADDRESS, Arrays.asList(p));
        //client.accept(MediaType.APPLICATION_XML);
        client.accept(MediaType.APPLICATION_JSON);
        client.path("/bookshelfwebreousrce/bookslelves/{id}", 1);
        client.header("Authorization", "Basic " + Base64Utility.encode(("fred" + ":" + "fredspassword").getBytes()));
        String response = client.get(String.class);
        System.out.println("  -----> " + response);
        Bookshelf b = client.get(Bookshelf.class);
    }

    @Test
    public void proxyClientTest() {
        BookshelfWebResource c = JAXRSClientFactory.create(ENDPOINT_ADDRESS, BookshelfWebResource.class);
        Bookshelf b = c.getBookshelf("1");
        System.out.println(b.getName());
        System.out.println(b.getDescription());
    }
}
