package com.lufficc.util;

import com.lufficc.api.exception.NotFoundException;

/**
 * Created by lcc_luffy on 2016/8/13.
 */
public class Utils {
    public static void checkExists(Object o) throws NotFoundException {
        if (o == null)
            throw new NotFoundException("未找到资源");
    }
}
