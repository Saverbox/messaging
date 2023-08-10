package ch.hftm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import ch.hftm.blog.control.BlogService;
import ch.hftm.blog.entity.Blog;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
public class BlogServiceTest {
    @Inject
    BlogService blogService;

    @Test
    void listingAndAddingBlogs() {
        // Arrange
        Blog blog = new Blog("Testing Blog", "This is my testing blog");
        int blogsBefore;
        List<Blog> blogs;

        // Act
        blogsBefore = blogService.getBlogs().size();
        blogService.addBlog(blog);
        blogs = blogService.getBlogs();

        // Assert
        assertEquals(blogsBefore + 1, blogs.size());

        
    }
     @Test
    public void testAddAndDeleteBlog() {
        Blog blog = new Blog("Test Title", "Test Content");
        blogService.addBlog(blog);

        // Überprüfen, ob der Blog hinzugefügt wurde
        assertFalse(blogService.getBlogs().isEmpty());

        // Löschen und überprüfen
        blogService.deleteBlog(blog.getId());
        assertTrue(blogService.getBlogs().isEmpty());
    }

}

