package com.feigong.baseball.dao;

import com.feigong.baseball.application.App;
import com.feigong.baseball.dto.TabTitleName;
import com.feigong.baseball.dto.TabTitleNameDao;

import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

/**
 * Created by ruler on 18/2/1.
 */

public class TabTitleNameService {


    /**
     * 添加数据
     *
     * @param dto
     */
    public static void insert(TabTitleName dto) {

        App.getDaoInstant().getTabTitleNameDao().insert(dto);
    }

    /**
     * 删除数据
     *
     * @param id
     */
    public static void delete(long id) {
        App.getDaoInstant().getUserDao().deleteByKey(id);
    }
    public static void delete(TabTitleName dto){
        App.getDaoInstant().delete(dto);
    }



    /**
     * 更新数据
     *
     * @param dto
     */
    public static void update(TabTitleName dto) {
        App.getDaoInstant().getTabTitleNameDao().update(dto);
    }

    /**
     * 查询条件为Type=TYPE_LOVE的数据
     *
     * @return
     */
    public static List<TabTitleName> query(String channel) {
        WhereCondition whereCondition =  TabTitleNameDao.Properties.TitleType.eq(channel);
        QueryBuilder<TabTitleName> query = App.getDaoInstant().getTabTitleNameDao().queryBuilder().where(whereCondition);
        return query.list();
    }

}
