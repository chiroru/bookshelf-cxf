package jp.ddo.chiroru.bookshelf.spring;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/bookshelfwebreousrce/")
public abstract class AbstractBookshelfWebResource
        implements BookshelfWebResource {

    protected static List<Bookshelf> bookshelfList = new ArrayList<Bookshelf>();

    public AbstractBookshelfWebResource() {
        Bookshelf b1 = new Bookshelf();
        b1.setId(1);
        b1.setName("name1");
        b1.setDescription("desc1");
        b1.setVolume(1);
        b1.setBookshelfId(1);
        b1.setCreatedUser("createduser1");
        b1.setCreatedAt(Timestamp.valueOf("2013-07-07 00:00:00.000000"));
        b1.setUpdatedUser("udpateduser1");
        b1.setUpdatedAt(Timestamp.valueOf("2013-07-07 01:01:01.000000"));
        bookshelfList.add(b1);
        Bookshelf b2 = new Bookshelf();
        b2.setId(2);
        b2.setName("name2");
        b2.setDescription("desc2");
        b2.setVolume(2);
        b2.setBookshelfId(2);
        b2.setCreatedUser("createduser2");
        b2.setCreatedAt(Timestamp.valueOf("2013-07-08 00:00:00.000000"));
        b2.setUpdatedUser("udpateduser2");
        b2.setUpdatedAt(Timestamp.valueOf("2013-07-08 01:01:01.000000"));
        bookshelfList.add(b2);
    }

    @Override
    @GET
    @Path("/bookslelves/{id}")
    public abstract Bookshelf getBookshelf(@PathParam("id")String id);

    @Override
    @PUT
    @Path("/bookslelves/{id}")
    public abstract Response updateBookshelf(@PathParam("id")String id, Bookshelf customer);

    @Override
    @POST
    @Path("/bookshelves/")
    public abstract Response addBookshelf(Bookshelf bookshelf);

    @Override
    @DELETE
    @Path("/bookshelves/{ID}")
    public abstract Response deleteBookshelf(@PathParam("id") String id);
}
