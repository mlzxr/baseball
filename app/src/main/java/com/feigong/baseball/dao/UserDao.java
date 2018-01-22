package com.feigong.baseball.dao;

import com.feigong.baseball.application.App;
import com.feigong.baseball.dto.User;

import java.util.List;

/**
 * Created by ruler on 18/1/19.
 */

public class UserDao {

    /**
     * 添加数据
     *
     * @param dto
     */
    public static void insert(User dto) {

        App.getDaoInstant().getUserDao().insert(dto);
    }

    /**
     * 删除数据
     *
     * @param id
     */
    public static void delete(long id) {
        App.getDaoInstant().getUserDao().deleteByKey(id);
    }

    /**
     * 更新数据
     *
     * @param dto
     */
    public static void update(User dto) {
        App.getDaoInstant().getUserDao().update(dto);
    }

    /**
     * 查询条件为Type=TYPE_LOVE的数据
     *
     * @return
     */
    public static List<User> query() {
        return App.getDaoInstant().getUserDao().queryBuilder().list();
    }

}
