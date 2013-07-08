package jp.ddo.chiroru.bookshelf;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/bookshelfwebresource/")
@Produces("application/xml")
public class BookshelfWebResource {

    private static List<Bookshelf> bookshelfList = new ArrayList<Bookshelf>();

    public BookshelfWebResource() {
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

    @GET
    @Path("/boolshelves")
    @Produces({"application/xml", "application/json"})
    @Consumes({"application/xml", "application/json"})
    public List<Bookshelf> getBookshelves() {
        return bookshelfList;
    }

    @GET
    @Path("/bookshelves/{id}")
    @Produces({"application/xml", "application/json"})
    @Consumes({"application/xml", "application/json"})
    public Bookshelf getBookshelf(@PathParam("id") String id) {
        return bookshelfList.get(Integer.valueOf(id) - 1);
    }

    @PUT
    @Path("/bookshelves/{id}")
    @Consumes("application/xml")
    public Response updateBookshelf(@PathParam("id") int id, Bookshelf bookshelf) {
        System.out.println("------------------>>>" + bookshelf.getName());
        bookshelfList.add(id, bookshelf);
        return Response.ok().build();
    }

    @POST
    @Path("/bookshelves")
    public Response addBookshelf(Bookshelf bookshelf) {
        System.out.println("------------------>" + bookshelf.getName());
        bookshelfList.add(bookshelf);
        return Response.ok().build();
    }

    @DELETE
    @Path("/bookshelves/{id}/")
    public Response deleteCustomer(@PathParam("id") String id) {
        System.out.println("------------------>>>>>>>>" + bookshelfList.size());
        bookshelfList.remove(Integer.valueOf(id) -1);
        System.out.println("------------------>>>>>>>>" + bookshelfList.size());
        return Response.ok().build();
    }
}
