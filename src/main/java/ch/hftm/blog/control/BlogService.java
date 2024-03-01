package ch.hftm.blog.control;

import java.util.List;

import org.jboss.logging.Logger;

import ch.hftm.blog.entity.Blog;
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

    @Transactional
    public void addBlog(Blog blog) {
        logger.info("Adding blog " + blog.getTitle());
        blogRepository.persist(blog);
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
