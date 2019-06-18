package com.marshall.sky.comment.service;

import com.marshall.sky.comment.model.Comment;
import com.marshall.sky.comment.model.TypeEnum;
import java.util.List;
import java.util.Optional;

public interface CommentService {

  boolean create(Comment comment);

  List<Comment> list(int page, int count);

  Optional<Comment> getById(long id);

  long buildCommentId(TypeEnum type);

}
