package jp.ddo.chiroru.bookshelf.spring;

import javax.ws.rs.core.Response;

import org.springframework.security.access.annotation.Secured;

public interface BookshelfWebResource {

    @Secured({"ROLE_CUSTOMER", "ROLE_ADMIN" })
    Bookshelf getBookshelf(String id);

    @Secured("ROLE_ADMIN")
    Response updateBookshelf(String id, Bookshelf bookshelf);

    @Secured("ROLE_ADMIN")
    Response addBookshelf(Bookshelf bookshelf);

    @Secured("ROLE_ADMIN")
    Response deleteBookshelf(String id);
}
