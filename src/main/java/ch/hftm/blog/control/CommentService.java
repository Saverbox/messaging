package ch.hftm.blog.control;

import org.jboss.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class CommentService {
    @Inject
    CommentRepository commentRepository;

    @Inject
    Logger logger;


    @Transactional
    public void deleteComment(Long commentId) {
        var comment = commentRepository.findByIdOptional(commentId);
        if (comment.isPresent()) {
            logger.info("Deleting comment " + commentId);
            commentRepository.delete(comment.get());
        } else {
            throw new NotFoundException("Comment not found");
        }
    }

    // Weitere Methoden für andere Operationen können hier implementiert werden
}
