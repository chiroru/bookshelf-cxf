package jp.ddo.chiroru.bookshelf.spring;

import javax.ws.rs.core.Response;

public class BookshelfWebResourceImpl
        extends AbstractBookshelfWebResource {

    @Override
    public Bookshelf getBookshelf(String id) {
        System.out.println("getCustomer : [" + id + "]");
        return bookshelfList.get(Integer.valueOf(id) - 1);
    }

    @Override
    public Response updateBookshelf(String id, Bookshelf bookshelf) {
        System.out.println("updateCustomer : [" + bookshelf.getName() + "]");
        bookshelfList.set(Integer.valueOf(id) - 1, bookshelf);
        return Response.ok(bookshelf).build();
    }

    @Override
    public Response addBookshelf(Bookshelf bookshelf) {
        System.out.println("addCustomer : [" + bookshelf.getName() + "]");
        bookshelfList.add(bookshelf);
        return Response.ok(bookshelf).build();
    }

    @Override
    public Response deleteBookshelf(String id) {
        System.out.println("addCustomer : [" + id + "]");
        bookshelfList.remove(Integer.valueOf(id) - 1);
        return Response.ok(bookshelfList.get(Integer.valueOf(id) - 1)).build();
    }
}
