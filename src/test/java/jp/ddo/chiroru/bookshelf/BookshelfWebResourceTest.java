package jp.ddo.chiroru.bookshelf;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


public class BookshelfWebResourceTest {
    private final static String ENDPOINT_ADDRESS = "http://localhost:8080/bookshelf";
    //private final static String WADL_ADDRESS = ENDPOINT_ADDRESS + "?_wadl";
    private static Server server;

    @BeforeClass
    public static void initialize() throws Exception {
        startServer();
        //waitForWADL();
    }

    private static void startServer() throws Exception {
        JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
        sf.setResourceClasses(BookshelfWebResource.class);

        List<Object> providers = new ArrayList<Object>();
        // add custom providers if any
        sf.setProviders(providers);

        sf.setResourceProvider(BookshelfWebResource.class,
                new SingletonResourceProvider(new BookshelfWebResource(), true));
        sf.setAddress(ENDPOINT_ADDRESS);

        server = sf.create();
    }

/*    // Optional step - may be needed to ensure that by the time individual
    // tests start running the endpoint has been fully initialized
    private static void waitForWADL() throws Exception {
        WebClient client = WebClient.create(WADL_ADDRESS);
        // wait for 20 secs or so
        for (int i = 0; i < 20; i++) {
            Thread.currentThread().sleep(1000);
            Response response = client.get();
            if (response.getStatus() == 200) {
                break;
            }
        }
        // no WADL is available yet - throw an exception or give tests a chance to run anyway
    }*/


    @AfterClass
    public static void destroy() throws Exception {
        server.stop();
        server.destroy();
    }

    @Test
    public void getBookshelves() {
        WebClient client = WebClient.create(ENDPOINT_ADDRESS);
        client.accept("application/xml");
        client.path("/bookshelfwebresource/boolshelves");
//        System.out.println(client.get(String.class));
        Collection<? extends Bookshelf> c = client.getCollection(Bookshelf.class);
        for (Bookshelf b : c) {
            System.out.println(b.getName());
        }
        //assertThat(l.size(), is(1));
    }

    @Test
    public void getBookshelfById() {
        WebClient client = WebClient.create(ENDPOINT_ADDRESS);
        client.accept("application/xml");
        client.path("/bookshelfwebresource/bookshelves/{id}", 1);
        System.out.println(client.get(String.class));
        Bookshelf b = client.get(Bookshelf.class);
        System.out.println("==========" + b.getId());
    }

    @Test
    public void addBookshelf() {
        WebClient client = WebClient.create(ENDPOINT_ADDRESS);
        client.accept("application/xml");
        client.path("/bookshelfwebresource/bookshelves");
        Bookshelf b = new Bookshelf();
        b.setName("addname");
        b.setDescription("adddesc");
        b.setVolume(1);
        b.setBookshelfId(1);
        b.setCreatedUser("addcreateduser");
        b.setCreatedAt(Timestamp.valueOf("2013-07-09 00:00:00.000000"));
        b.setUpdatedUser("addudpateduser1");
        b.setUpdatedAt(Timestamp.valueOf("2013-07-09 01:01:01.000000"));
        Response r = client.post(b);
    }
    
    @Test
    public void updateBookshelfById() {
        WebClient client = WebClient.create(ENDPOINT_ADDRESS);
        client.accept("application/xml");
        client.path("/bookshelfwebresource/bookshelves/{id}", 1);
        Bookshelf b = client.get(Bookshelf.class);
        System.out.println("==========" + b.getId());
        b.setName("update");
        client.put(b);
    }
    
    @Test
    public void deleteBookshelfById() {
        WebClient client = WebClient.create(ENDPOINT_ADDRESS);
        client.accept("application/xml");
        client.path("/bookshelfwebresource/bookshelves/{id}", 1);
        Bookshelf b = client.get(Bookshelf.class);
        System.out.println("★==========" + b.getId());
        client.delete();
    }
/*    @Test
    public void testGetBookJsonWithWebClient() {
        WebClient client = WebClient.create(ENDPOINT_ADDRESS);
        client.accept("application/json");
        client.path("/bookshelfwebresource/boolshelves");
        System.out.println(client.get(String.class));
        Collection<? extends Bookshelf> c = client.getCollection(Bookshelf.class);
        for (Bookshelf b : c) {
            System.out.println(b.getName());
        }
        //assertThat(l.size(), is(1));
    }*/
    
/*    @Test
    public void testGetBookWithProxy() {
        MyJaxrsResource client = JAXRSClientFactory.create(ENDPOINT_ADDRESS, MyJaxrsResource.class);
        Book book = client.getBook();
        assertEquals(123L, book.getId());
    }*/
}
