package com.marshall.sky.comment.model;

import com.marshall.sky.core.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.zookeeper.data.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiComment {

  Long id;
  UserInfo author;
  Long mediaId;
  String content;
  Long createAt;
  TypeEnum type;
  StatusEnum status;

  public static ApiComment transform(Comment comment) {
    return ApiComment.builder()
        .id(comment.getId())
        .mediaId(comment.getMediaId())
        .content(comment.getContent())
        .createAt(comment.getCreateAt())
        .type(comment.getType())
        .status(comment.getStatus())
        .build();
  }

}
