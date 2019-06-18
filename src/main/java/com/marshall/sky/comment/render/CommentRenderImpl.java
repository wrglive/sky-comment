package com.marshall.sky.comment.render;

import com.google.protobuf.Api;
import com.marshall.sky.comment.client.UserClient;
import com.marshall.sky.comment.model.ApiComment;
import com.marshall.sky.comment.model.Comment;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentRenderImpl implements CommentRender {

  @Autowired
  UserClient userClient;

  @Override
  public List<ApiComment> batchRender(Collection<Comment> comments) {
    return comments.stream()
        .map(this::render)
        .filter(Objects::nonNull)
        .collect(Collectors.toList());
  }

  private ApiComment render(Comment comment) {
    ApiComment apiComment = ApiComment.transform(comment);
    apiComment.setAuthor(userClient.get(comment.getUserId()));
    return apiComment;
  }
}
