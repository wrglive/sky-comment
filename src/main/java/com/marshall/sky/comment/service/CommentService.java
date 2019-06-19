package com.marshall.sky.comment.service;

import com.marshall.sky.comment.model.Comment;
import com.marshall.sky.comment.model.TypeEnum;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CommentService {

  boolean create(Comment comment);

  List<Comment> list(int page, int count);

  List<Comment> multiGet(Collection<Long> ids);

  Optional<Comment> getById(long id);

  List<Long> listAuthorComment(long userId, int page, int count);

  long buildCommentId(TypeEnum type);

}
