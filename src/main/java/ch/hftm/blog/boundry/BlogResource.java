package ch.hftm.blog.boundry;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import ch.hftm.blog.control.BlogService;
import ch.hftm.blog.entity.Blog;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
// JAX-RS
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("blogs") // Unter welchem Web-Pfad ist die Ressource erreichbar ist. Diese Annotation
               // darfst du zusätzlich auch direkt über der Methode anbringen
@Tag(name = "blogs", description = "Alle Blog-bezogenen Operationen")
@ApplicationScoped
public class BlogResource {

    @Inject
    BlogService blogService;

    @GET
    @Operation(summary = "Ruft alle Blog-Einträge ab")
    public List<Blog> getEntries() {
        return this.blogService.getBlogs();
    }

    @POST
    @Operation(summary = "Fügt einen neuen Blog-Eintrag hinzu")
    public Response addBlog(Blog blog) {
        try {
            blogService.createAndValidateBlog(blog);
            return Response.status(Response.Status.CREATED).entity(blog).build();
        } catch (Exception e) {
            // Fehlerbehandlung
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Fehler beim Hinzufügen des Blogs").build();
        }
    }

    @Path("{id}")
    @GET
    @Operation(summary = "Zeigt einen spezifischen Blog-Eintrag basierend auf seiner ID")
    public Blog getBlogById(@PathParam("id") Long blogId) {
        return blogService.getBlogById(blogId);
    }

    @Path("{id}")
    @PUT
    @Operation(summary = "Aktualisiert einen Blog-Eintrag")
    public void updateBlog(@PathParam("id") Long blogId, Blog blog) {
        blogService.updateBlog(blogId, blog);
    }

    @Path("{id}")
    @DELETE
    @Operation(summary = "Löscht einen Blog-Eintrag nach ID")
    public void deleteBlog(@PathParam("id") Long blogId) {
        blogService.deleteBlog(blogId);
    }

}
