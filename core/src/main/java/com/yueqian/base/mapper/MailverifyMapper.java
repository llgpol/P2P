package com.yueqian.base.mapper;

import com.yueqian.base.domain.MailVerify;
import java.util.List;

public interface MailverifyMapper {
   
    int insert(MailVerify record);

    MailVerify selectByUUID(String uuid);

}