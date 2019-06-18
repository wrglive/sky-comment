package com.marshall.sky.comment.exception;

import com.alibaba.fastjson.JSONObject;
import com.marshall.sky.core.exception.ExFactor;
import com.marshall.sky.core.exception.SkyExceptionMsgModel;

public enum SkyCommentExceptionEnum implements ExFactor {
  DEFAULT(1, "default error", "默认异常");

  int errorIndex;
  String errorCode;
  String errorMsg;

  SkyCommentExceptionEnum(int errorIndex, String errorCode, String errorMsg) {
    this.errorIndex = errorIndex;
    this.errorCode = errorCode;
    this.errorMsg = errorMsg;
  }

  public int getErrorIndex() {
    return errorIndex;
  }

  public String getErrorCode() {
    return errorCode;
  }

  public String getErrorMsg() {
    return errorMsg;
  }


  @Override
  public SkyExceptionMsgModel getModel() {
    return new SkyExceptionMsgModel(errorIndex, errorCode, errorMsg, "", "");
  }


}
