package com.howbuy.uaa.dao;

import com.howbuy.rdb.database.dao.BaseDao;
import com.howbuy.uaa.dto.PvStatForm;
import com.howbuy.uaa.persistence.PvStat;

import java.util.List;

/**
 * <pre>
 *
 * </pre>
 *
 * @author ji.ma
 * @create 13-3-7 上午9:46
 * @modify
 * @since JDK1.6
 */
public interface PvStatDao {

    /**
     * 取得最大的日期
     *
     * @param type 类型
     * @return .
     */
    String getMaxDate(Long type);

    /**
     * 取得子频道的pv
     *
     * @param statDate   统计日期
     * @param subChannel 子频道
     * @return .
     */
    Long getSubChannelPv(String statDate, String subChannel);

    /**
     * 取得子频道列表
     *
     * @return .
     */
    List<String> getSubChannels();

    /**
     * 取得首访pv统计
     *
     * @param statDate 统计日期
     * @return .
     */
    List<PvStatForm> getFirstPvStatForms(String statDate);
}
