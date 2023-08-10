package ch.hftm.blog.boundry;

import ch.hftm.blog.entity.Comment;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/comments")
@Tag(name = "comments", description = "Alle Comment-bezogenen Operationen")
@ApplicationScoped
public class CommentResource {

    @GET
    @Operation(summary = "Liste aller Kommentare abrufen")
    public List<Comment> getAllComments() {
        return Comment.listAll();
    }

    @GET
    @Path("{id}")
    @Operation(summary = "Einen Kommentar anhand seiner ID abrufen")
    public Comment getComment(@PathParam("id") Long id) {
        Comment comment = Comment.findById(id);
        if (comment == null) {
            throw new NotFoundException("No comment found with id " + id);
        }
        return comment;
    }

    @PUT
    @Path("{id}")
    @Transactional
    @Operation(summary = "Einen Kommentar aktualisieren")
    public Response updateComment(@PathParam("id") Long id, Comment comment) {
        Comment existingComment = Comment.findById(id);
        if (existingComment == null) {
            throw new NotFoundException("No comment found with id " + id);
        }
        existingComment.setText(comment.getText());
        existingComment.persist();
        return Response.ok(existingComment).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    @Operation(summary = "Einen Kommentar löschen")
    public Response deleteComment(@PathParam("id") Long id) {
        Comment comment = Comment.findById(id);
        if (comment == null) {
            throw new NotFoundException("No comment found with id " + id);
        }
        comment.delete();
        return Response.noContent().build();
    }
}