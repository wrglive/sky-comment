package com.marshall.sky.comment.model;

import com.marshall.sky.core.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
  Long id;
  Long userId;
  Long mediaId;
  String content;
  Long createAt;
  TypeEnum type;
  StatusEnum status;



}
