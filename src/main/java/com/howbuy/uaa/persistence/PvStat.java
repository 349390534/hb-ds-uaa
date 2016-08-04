package com.howbuy.uaa.persistence;

import com.howbuy.rdb.annotation.AddParentClass;
import com.howbuy.rdb.annotation.EntityPK;
import com.howbuy.rdb.database.dto.impl.BaseDtoAdapter;
import com.howbuy.rdb.database.util.Constant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 *  用户pv统计
 * </pre>
 *
 * @author ji.ma
 * @create 13-3-6 上午10:43
 * @modify
 * @since JDK1.6
 */
@EntityPK(Pk = "id", defaultColumn = false, tableName = "pv_stat")
//@AddParentClass(AddParentClass = true, DefaultValueClass = Constant.DEFAULT_COLUMN_COMMON_CLASSNAME)
public class PvStat extends BaseDtoAdapter{
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private static final long serialVersionUID = -7224036186001195120L;

    private Long id;

    private String statDate;

    private String channel;

    private String subChannel;

    private Long pv;

    private String srcChannel;

    private String srcSubChannel;

    private Long type;

    private String srcUrl;

    private String toUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatDate() {
        return statDate;
    }

    public void setStatDate(String statDate) {
        this.statDate = statDate;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getSubChannel() {
        return subChannel;
    }

    public void setSubChannel(String subChannel) {
        this.subChannel = subChannel;
    }

    public Long getPv() {
        return pv;
    }

    public void setPv(Long pv) {
        this.pv = pv;
    }

    public String getSrcChannel() {
        return srcChannel;
    }

    public void setSrcChannel(String srcChannel) {
        this.srcChannel = srcChannel;
    }

    public String getSrcSubChannel() {
        return srcSubChannel;
    }

    public void setSrcSubChannel(String srcSubChannel) {
        this.srcSubChannel = srcSubChannel;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String getSrcUrl() {
        return srcUrl;
    }

    public void setSrcUrl(String srcUrl) {
        this.srcUrl = srcUrl;
    }

    public String getToUrl() {
        return toUrl;
    }

    public void setToUrl(String toUrl) {
        this.toUrl = toUrl;
    }
}
