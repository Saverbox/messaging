package ch.hftm.blog.boundry;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import ch.hftm.blog.control.BlogService;
import ch.hftm.blog.entity.Blog;
import ch.hftm.blog.entity.Comment;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
// JAX-RS
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;

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
    public void addBlog(Blog blog) {
        this.blogService.addBlog(blog);
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

    @Path("{id}/comments")
    @POST
    @Operation(summary = "Fügt einen Kommentar zu einem Blog-Eintrag hinzu")
    public void addCommentToBlog(@PathParam("id") Long blogId, Comment comment) {
        blogService.addCommentToBlog(blogId, comment);
    }

    @Path("{id}/comments")
    @GET
    @Operation(summary = "Zeigt alle Komentare eines Blogs nach ID")
    public List<Comment> getCommentsForBlog(@PathParam("id") Long blogId) {
        return blogService.getCommentsForBlog(blogId);
    }

    @Path("{blogId}/comments/{commentId}")
    @DELETE
    @Operation(summary = "Löscht einen Kommentar von einem bestimmten Blog-Eintrag")
    public void deleteComment(@PathParam("blogId") Long blogId, @PathParam("commentId") Long commentId) {
        blogService.deleteComment(blogId, commentId);
    }

}
