package com.marshall.sky.comment.service;

import com.marshall.sky.comment.mapper.CommentMapper;
import com.marshall.sky.comment.model.Comment;
import com.marshall.sky.comment.model.TypeEnum;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

  @Autowired
  CommentMapper commentMapper;
  @Autowired
  CommentRedisManage commentRedisManage;

  @Override
  public boolean create(Comment comment) {
    boolean rst = commentMapper.create(comment);
    if (rst){
      commentRedisManage.create(comment);
    }
    return rst;
  }

  @Override
  public List<Comment> list(int page, int count) {
    return commentMapper.list(count, page * count);
  }

  @Override
  public List<Comment> multiGet(Collection<Long> ids) {
    return commentRedisManage.multiGet(ids);
  }

  @Override
  public Optional<Comment> getById(long id) {
    return commentRedisManage.getById(id);
  }

  @Override
  public List<Long> listAuthorComment(long userId, int page, int count) {
    return commentRedisManage.listAuthorComment(userId, page, count);
  }

  @Override
  public long buildCommentId(TypeEnum type) {
    return commentRedisManage.buildCommentId(type);
  }
}
