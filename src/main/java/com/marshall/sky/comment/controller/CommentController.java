package com.marshall.sky.comment.controller;

import com.google.protobuf.Api;
import com.marshall.sky.comment.model.ApiComment;
import com.marshall.sky.comment.model.Comment;
import com.marshall.sky.comment.model.TypeEnum;
import com.marshall.sky.comment.render.CommentRender;
import com.marshall.sky.comment.service.CommentService;
import com.marshall.sky.core.enums.StatusEnum;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController {

  @Autowired
  private CommentService commentService;
  @Autowired
  private CommentRender commentRender;

  @PostMapping("/create.json")
  public boolean create(@RequestParam(name = "user_id") long userId,
      @RequestParam(name = "media_id", defaultValue = "0") long mediaId,
      String content,
      @RequestParam(name = "type", defaultValue = "TEXT") TypeEnum type) {
    Comment comment = Comment.builder()
        .id(commentService.buildCommentId(type))
        .userId(userId)
        .mediaId(mediaId)
        .content(content)
        .createAt(System.currentTimeMillis())
        .type(type)
        .status(StatusEnum.ONLINE)
        .build();

    return commentService.create(comment);
  }

  @GetMapping("/list.json")
  public List<ApiComment> list(int page, int count) {
    List<Comment> comments = commentService.list(page, count);
    return commentRender.batchRender(comments);
  }

}
