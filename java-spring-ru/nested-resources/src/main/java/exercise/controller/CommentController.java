package exercise.controller;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;


@RestController
@RequestMapping("/posts")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    // BEGIN
    @GetMapping(path = "/{postId}/comments")
    public Iterable<Comment> getAllCommentsForPost(@PathVariable Long postId) {
        return commentRepository.findAllByPostId(postId);
    }

    @GetMapping(path = "/{postId}/comments/{commentId}")
    public Comment getCommentForPost(@PathVariable Long postId, @PathVariable Long commentId) {

        return commentRepository.findByIdAndPostId(commentId, postId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
//        Post post = postRepository.findById(postId).get();
//        return post.getComments().stream().filter(p -> p.getId().equals(commentId))
//                .findFirst().orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
    }

    @PostMapping(path = "/{postId}/comments")
    public Comment createNewCommentForPost(@PathVariable Long postId, @RequestBody Comment commentData) {
        Post post = postRepository.findById(postId).get();
        commentData.setPost(post);
        return commentRepository.save(commentData);
    }

    @PatchMapping(path = "/{postId}/comments/{commentId}")
    public Comment updateComment(@PathVariable Long postId, @PathVariable Long commentId, @RequestBody Comment commentData) {
        Comment comment = commentRepository.findByIdAndPostId(commentId, postId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
        comment.setContent(commentData.getContent());
        return commentRepository.save(comment);
    }

    @DeleteMapping(path = "/{postId}/comments/{commentId}")
    public void deleteComment(@PathVariable Long postId, @PathVariable Long commentId) {
        Post post = postRepository.findById(postId).get();
        Comment comment = post.getComments().stream().filter(i -> i.getId().equals(commentId))
                                                    .findFirst()
                                                    .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
        commentRepository.delete(comment);
    }
    // END
}
