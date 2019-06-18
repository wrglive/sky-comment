package com.marshall.sky.comment.service;

import com.marshall.sky.comment.mapper.CommentMapper;
import com.marshall.sky.comment.model.Comment;
import com.marshall.sky.comment.model.TypeEnum;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

  @Autowired
  CommentMapper commentMapper;

  @Override
  public boolean create(Comment comment) {
    return commentMapper.create(comment);
  }

  @Override
  public List<Comment> list(int page, int count) {
    return commentMapper.list(count, page * count);
  }

  @Override
  public Optional<Comment> getById(long id) {
    return Optional.ofNullable(commentMapper.getById(id));
  }

  @Override
  public long buildCommentId(TypeEnum type) {
    return System.currentTimeMillis() * 10000 + type.getIndex();
  }
}
