package com.marshall.sky.comment.mapper;

import com.marshall.sky.comment.model.Comment;
import com.marshall.sky.comment.model.TypeEnum;
import com.marshall.sky.core.enums.StatusEnum;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface CommentMapper {

  @Insert("insert into comment"
      + "(id, "
      + "user_id, "
      + "media_id, "
      + "content, "
      + "create_at, "
      + "`type`, "
      + "status) "
      + "values "
      + "(#{id},"
      + "#{userId}, "
      + "#{mediaId}, "
      + "#{content}, "
      + "#{createAt}, "
      //下面type 写的是class 这里也写得class 找bug找了半天，fuck
      + "#{type,typeHandler = com.marshall.sky.core.mybatis.EnumTransformHandler}, "
      + "#{status,typeHandler = com.marshall.sky.core.mybatis.EnumTransformHandler})")
  boolean create(Comment userInfo);

  @Select("select * from comment limit #{offset}, #{limit}")
  @Results({
      @Result(property = "type", column = "type", typeHandler = com.marshall.sky.core.mybatis.EnumTransformHandler.class, javaType = TypeEnum.class),
      @Result(property = "status", column = "status", typeHandler = com.marshall.sky.core.mybatis.EnumTransformHandler.class, javaType = StatusEnum.class)
  })
  List<Comment> list(int limit, int offset);

  @Select("select * from comment where id = #{id}")
  Comment getById(long id);

}
