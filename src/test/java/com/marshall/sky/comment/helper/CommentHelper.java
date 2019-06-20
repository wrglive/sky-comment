package com.marshall.sky.comment.helper;

import com.google.common.collect.Lists;
import com.marshall.sky.comment.model.Comment;
import com.marshall.sky.comment.model.TypeEnum;
import java.util.List;
import java.util.stream.IntStream;

public class CommentHelper {

  public static Comment mockComment(long id){
      return Comment
          .builder()
          .id(id)
          .userId(id)
          .type(TypeEnum.TEXT)
          .build();
  }

  public static List<Comment> mockComments(int mockCount){
    int count = 1;
    List<Comment> comments = Lists.newArrayList();
    IntStream.range(1, mockCount + 1)
        .forEach(i -> comments.add(mockComment(System.currentTimeMillis() * 1000 + i)));
    return comments;
  }

}
