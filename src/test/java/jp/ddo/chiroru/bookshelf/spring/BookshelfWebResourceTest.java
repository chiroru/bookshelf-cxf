package jp.ddo.chiroru.bookshelf.spring;

import jp.ddo.chiroru.bookshelf.Bookshelf;
import jp.ddo.chiroru.bookshelf.util.ServletContainerResource;

import org.apache.cxf.common.util.Base64Utility;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.ClassRule;
import org.junit.Test;

public class BookshelfWebResourceTest {

    private final static String ENDPOINT_ADDRESS = "http://127.0.0.1:8080/bookshelf";

    @ClassRule
    public static ServletContainerResource server = new ServletContainerResource();
    
    @Test
    public void getBookshelfById() {
        WebClient client = WebClient.create(ENDPOINT_ADDRESS);
        client.accept("application/xml");
        client.path("/bookshelfwebreousrce/bookslelves/{id}", 1);
        client.header("Authorization", "Basic " + Base64Utility.encode(("fred" + ":" + "fredspassword").getBytes()));
        System.out.println(client.get(String.class));
        Bookshelf b = client.get(Bookshelf.class);
        System.out.println("==========" + b.getId());
    }
}
