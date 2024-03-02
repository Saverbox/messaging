package ch.hftm.blog.control;

import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;

import ch.hftm.blog.entity.Blog;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class BlogService {
    @Inject
    BlogRepository blogRepository;

    @Inject
    Logger logger;

    @Inject
    @Channel("validation-request")
    Emitter<ValidationRequest> validationRequestEmitter;

    public List<Blog> getBlogs() {
        var blogs = blogRepository.listAll();
        logger.info("Returning " + blogs.size() + " blogs");
        return blogs;
    }

    @Transactional
    public Blog addBlog(Blog blog) {
        logger.info("Adding blog " + blog.getTitle());
        blogRepository.persist(blog);
        return blog; // Rückgabe des gespeicherten Blogs
    }

    public void sendValidationRequest(Blog blog) {
        if (blog.getId() == null) {
            logger.error("Blog ID is null, cannot send validation request");
            return;
        }
        validationRequestEmitter.send(new ValidationRequest(blog.getId(), blog.getTitle() + " " + blog.getContent()));
    }
        
    @Incoming("validation-response")
    @Transactional
    public void sink(ValidationResponse validationResponse) {
        logger.debug("Validation Response: " + validationResponse);
        Optional<Blog> blogOptional = blogRepository.findByIdOptional(validationResponse.id());
        if (blogOptional.isEmpty()) {
            logger.warn("Entry not found");
            return;
        }
        Blog blog = blogOptional.get();
        blog.setValid(validationResponse.valid());
        blogRepository.persist(blog);
    }

    public void createAndValidateBlog(Blog blog) {
        Blog savedBlog = addBlog(blog); // Speichert den Blog
        sendValidationRequest(savedBlog); // Sendet die Validierungsanfrage
    }

    @Transactional
    public Blog getBlogById(Long blogId) {
        var blog = blogRepository.findByIdOptional(blogId);
        if (blog.isPresent()) {
            return blog.get();
        } else {
            throw new NotFoundException("Blog with ID " + blogId + " not found");
        }
    }

    @Transactional
    public void updateBlog(Long blogId, Blog newBlogData) {
        var existingBlog = blogRepository.findByIdOptional(blogId);
        if (existingBlog.isPresent()) {
            Blog blog = existingBlog.get();
            blog.setTitle(newBlogData.getTitle());
            blog.setContent(newBlogData.getContent());
            blogRepository.persist(blog);
        } else {
            throw new NotFoundException("Blog not found");
        }
    }

    @Transactional
    public void deleteBlog(Long blogId) {
        if (!blogRepository.deleteById(blogId)) {
            throw new NotFoundException("Blog with ID " + blogId + " not found");
        }
    }


}
