/**
 * 
 */
package com.howbuy.uaa.quartz;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.howbuy.uaa.common.AppChannelSingleData;
import com.howbuy.uaa.common.execption.UaaRunTimeException;
import com.howbuy.uaa.utils.HttpUtil;

/**
 * @author qiankun.li
 * 
 */
public class UpdateChannelDayJob {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(UpdateChannelDayJob.class);

	private String channelUrl;

	public void run() {
		LOGGER.info("--------UpdateChannelDayJob start------");

		// 公募客户渠道API地址如下（渠道信息）
		try {
			if (StringUtils.isBlank(channelUrl)) {
				throw new UaaRunTimeException("update channel failed,the request url is null");
			}
			String json = HttpUtil.getHttpUtil().requestGet(channelUrl);
			JSONObject jsonobj = JSONObject.fromObject(json);
			JSONArray disArrs = jsonobj.getJSONObject("body").getJSONArray("list");
			String ch=disArrs.toString();
			if(StringUtils.isNotBlank(ch))
				AppChannelSingleData.getSingleData().setChannelData(disArrs.toString());
		} catch (Exception e) {
			LOGGER.error("UpdateChannelDayJob run error.", e);
		}
		LOGGER.info("--------UpdateChannelDayJob end------");

	}

	public String getChannelUrl() {
		return channelUrl;
	}

	public void setChannelUrl(String channelUrl) {
		this.channelUrl = channelUrl;
	}

}
