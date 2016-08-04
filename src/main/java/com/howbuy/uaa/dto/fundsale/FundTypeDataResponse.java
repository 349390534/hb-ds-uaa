/**
 * 
 */
package com.howbuy.uaa.dto.fundsale;

import java.util.List;

/**
 * @author qiankun.li 基金类型数据
 */
public class FundTypeDataResponse {

	private List<FundTypeDataDetailDto> list;

	public List<FundTypeDataDetailDto> getList() {
		return list;
	}

	public void setList(List<FundTypeDataDetailDto> list) {
		this.list = list;
	}

}
