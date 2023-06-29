package ch.hftm.blog.control;

import java.util.List;

import org.jboss.logging.Logger;

import ch.hftm.blog.entity.Blog;
import ch.hftm.blog.entity.Comment;
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


    public List<Blog> getBlogs() {
        var blogs = blogRepository.listAll();
        logger.info("Returning " + blogs.size() + " blogs");
        return blogs;
    }
    public List<Blog> findBlogs(String searchString) { 
        var blogs = blogRepository.find("title like ?1 or contend like 1?", "%" + searchString + "%").list();
        logger.info("Found " + blogs.size() + "blogs");
        return blogs;
    }

    @Transactional
    public void addBlog(Blog blog) {
        logger.info("Adding blog " + blog.getTitle());
        blogRepository.persist(blog);
    }

    public List<Comment> getCommentsForBlog(Long blogId) {
        var blog = blogRepository.findByIdOptional(blogId);
        if (blog.isPresent()) {
            var comments = blog.get().getComments();
            logger.info("Returning " + comments.size() + " comments for blog " + blogId);
            return comments;
        } else {
            throw new NotFoundException("Blog not found");
        }
    }

    @Transactional
    public void addCommentToBlog(Long blogId, Comment comment) {
        var blog = blogRepository.findByIdOptional(blogId);
        if (blog.isPresent()) {
            logger.info("Adding comment to blog " + blogId);
            comment.setBlog(blog.get());
            blog.get().getComments().add(comment);
            blogRepository.persist(blog.get());
        } else {
            throw new NotFoundException("Blog not found");
        }
    }

}
