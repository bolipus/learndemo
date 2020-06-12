package si.gemma.demo.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import si.gemma.demo.entities.Comment;
import si.gemma.demo.exceptions.CommentNotFoundException;
import si.gemma.demo.models.CommentDTO;
import si.gemma.demo.services.CommentService;

@RestController
@RequestMapping(value = "/api/v1")
public class CommentController {
  private CommentService commentService;

  private ModelMapper modelMapper;

  @Autowired
  public CommentController(CommentService commentService, ModelMapper modelMapper) {
    this.commentService = commentService;
    this.modelMapper = modelMapper;
  }

  @GetMapping("/comments")
  public ResponseEntity<List<CommentDTO>> findAllRoles() {
    List<CommentDTO> comments =
        commentService.findAll().stream().map(this::convertDTO).collect(Collectors.toList());
    return ResponseEntity.ok(comments);
  }

  @GetMapping("/comments/{id}")
  public ResponseEntity<CommentDTO> findRole(@PathVariable(name = "id", required = true) Long id)
      throws CommentNotFoundException {
    Comment comment = commentService.findById(id);
    return ResponseEntity.ok(convertDTO(comment));
  }

  @PostMapping("/comments")
  public ResponseEntity<CommentDTO> createRole(@RequestBody CommentDTO commentDTO) {
    Comment comment = commentService.save(convertEntity(commentDTO));
    return ResponseEntity.ok(convertDTO(comment));
  }

  @PutMapping("/comments/{id}")
  public ResponseEntity<CommentDTO> updateRole(@PathVariable(name = "id", required = true) Long id,
      @RequestBody CommentDTO commentDTO) throws CommentNotFoundException {

    if (!id.equals(commentDTO.getId())) {
      throw new CommentNotFoundException("Id is not identical to body id");
    }

    Comment comment = commentService.update(convertEntity(commentDTO));
    return ResponseEntity.ok(convertDTO(comment));
  }

  private CommentDTO convertDTO(Comment comment) {
    return modelMapper.map(comment, CommentDTO.class);
  }

  private Comment convertEntity(CommentDTO commentDTO) {
    return modelMapper.map(commentDTO, Comment.class);
  }
}
