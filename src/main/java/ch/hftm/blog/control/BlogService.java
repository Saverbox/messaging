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

    @Transactional
    public void deleteComment(Long blogId, Long commentId) {
        Blog blog = getBlogById(blogId);
        Comment comment = Comment.findById(commentId);
        if (comment == null || comment.getBlog().getId() != blogId) {
            throw new NotFoundException("Comment with ID " + commentId + " not found for Blog " + blogId);
        }
        comment.delete();
    }

}
