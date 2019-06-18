package com.marshall.sky.comment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TypeEnum {

  TEXT(1),
  PICTURE(2),
  ;

  int index;

}
