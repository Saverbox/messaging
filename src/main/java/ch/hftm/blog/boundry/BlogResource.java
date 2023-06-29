package ch.hftm.blog.boundry;

import java.util.List;

import ch.hftm.blog.control.BlogService;
import ch.hftm.blog.entity.Blog;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
// JAX-RS
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;


@Path("blogs")       // Unter welchem Web-Pfad ist die Ressource erreichbar ist. Diese Annotation darfst du zusätzlich auch direkt über der Methode anbringen
@ApplicationScoped
public class BlogResource {

    @Inject
    BlogService blogService;

    @GET             // Diese Methode ist über eine http-GET-Anfrage erreichbar.
    public List<Blog> getEntries() {
        return this.blogService.getBlogs();
    }

    @POST            // Diese Methode ist über eine http-POST-Anfrage erreichbar.
    public void addBlog(Blog blog) {
        this.blogService.addBlog(blog);
    }
    
}
