/**
 * 
 */
package com.howbuy.uaa.dto.fundsale;

import java.math.BigDecimal;

/**
 * @author qiankun.li 销量数据-基金类型明细dto
 */
public class FundTypeDataDetailDto {

	/**
	 * 日期
	 */
	private String TRADE_DT;
	/**
	 * 基金类型
	 */
	private String FUND_TYPE;

	/**
	 * 基金类型名称
	 */
	private String FUND_TYPE_NAME;
	/**
	 * 存量金额
	 */
	private BigDecimal MARKET_AMT;
	/**
	 * 申购金额（下单）
	 */
	private BigDecimal APP_AMT;

	/**
	 * 赎回份额（下单）
	 */
	private BigDecimal SELL_APP_VOL;
	/**
	 * 申购金额（确认）
	 */
	private BigDecimal ACK_AMT;

	/**
	 * 赎回金额（确认）
	 */
	private BigDecimal SELL_ACK_AMT;

	/**
	 * 净申购金额（确认）
	 */
	private BigDecimal NET_ACK_AMT;
	/**
	 * 费率
	 */
	private BigDecimal FEE_RATE;
	/**
	 * 尾随金额
	 */
	private BigDecimal SVC_AMT;
	/**
	 * 认购费收入
	 */
	private BigDecimal RAISE_FEE;
	/**
	 * 申购费收入
	 */
	private BigDecimal SUBS_FEE;
	/**
	 * 赎回费收入
	 */
	private BigDecimal REDE_FEE;
	/**
	 * 尾随收入
	 */
	private BigDecimal SVC_FEE;
	/**
	 * 销售服务费收入
	 */
	private BigDecimal AGENT_SVC_FEE;
	/**
	 * 额外营销费用
	 */
	private BigDecimal OTHER_AMT;

	/**
	 * 额外营销费用
	 */
	private BigDecimal OTHER_FEE;

	/**
	 * 收入
	 */
	private BigDecimal INCOME;
	/**
	 * 成本
	 */
	private BigDecimal CAPITAL;

	public String getTRADE_DT() {
		return TRADE_DT;
	}

	public void setTRADE_DT(String tRADE_DT) {
		TRADE_DT = tRADE_DT;
	}

	public String getFUND_TYPE() {
		return FUND_TYPE;
	}

	public void setFUND_TYPE(String fUND_TYPE) {
		FUND_TYPE = fUND_TYPE;
	}

	public String getFUND_TYPE_NAME() {
		return FUND_TYPE_NAME;
	}

	public void setFUND_TYPE_NAME(String fUND_TYPE_NAME) {
		FUND_TYPE_NAME = fUND_TYPE_NAME;
	}

	public BigDecimal getMARKET_AMT() {
		return MARKET_AMT;
	}

	public void setMARKET_AMT(BigDecimal mARKET_AMT) {
		MARKET_AMT = mARKET_AMT;
	}

	public BigDecimal getAPP_AMT() {
		return APP_AMT;
	}

	public void setAPP_AMT(BigDecimal aPP_AMT) {
		APP_AMT = aPP_AMT;
	}

	public BigDecimal getSELL_APP_VOL() {
		return SELL_APP_VOL;
	}

	public void setSELL_APP_VOL(BigDecimal sELL_APP_VOL) {
		SELL_APP_VOL = sELL_APP_VOL;
	}

	public BigDecimal getACK_AMT() {
		return ACK_AMT;
	}

	public void setACK_AMT(BigDecimal aCK_AMT) {
		ACK_AMT = aCK_AMT;
	}

	public BigDecimal getSELL_ACK_AMT() {
		return SELL_ACK_AMT;
	}

	public void setSELL_ACK_AMT(BigDecimal sELL_ACK_AMT) {
		SELL_ACK_AMT = sELL_ACK_AMT;
	}

	public BigDecimal getNET_ACK_AMT() {
		return NET_ACK_AMT;
	}

	public void setNET_ACK_AMT(BigDecimal nET_ACK_AMT) {
		NET_ACK_AMT = nET_ACK_AMT;
	}

	public BigDecimal getFEE_RATE() {
		return FEE_RATE;
	}

	public void setFEE_RATE(BigDecimal fEE_RATE) {
		FEE_RATE = fEE_RATE;
	}

	public BigDecimal getSVC_AMT() {
		return SVC_AMT;
	}

	public void setSVC_AMT(BigDecimal sVC_AMT) {
		SVC_AMT = sVC_AMT;
	}

	public BigDecimal getRAISE_FEE() {
		return RAISE_FEE;
	}

	public void setRAISE_FEE(BigDecimal rAISE_FEE) {
		RAISE_FEE = rAISE_FEE;
	}

	public BigDecimal getSUBS_FEE() {
		return SUBS_FEE;
	}

	public void setSUBS_FEE(BigDecimal sUBS_FEE) {
		SUBS_FEE = sUBS_FEE;
	}

	public BigDecimal getREDE_FEE() {
		return REDE_FEE;
	}

	public void setREDE_FEE(BigDecimal rEDE_FEE) {
		REDE_FEE = rEDE_FEE;
	}

	public BigDecimal getSVC_FEE() {
		return SVC_FEE;
	}

	public void setSVC_FEE(BigDecimal sVC_FEE) {
		SVC_FEE = sVC_FEE;
	}

	public BigDecimal getAGENT_SVC_FEE() {
		return AGENT_SVC_FEE;
	}

	public void setAGENT_SVC_FEE(BigDecimal aGENT_SVC_FEE) {
		AGENT_SVC_FEE = aGENT_SVC_FEE;
	}

	public BigDecimal getOTHER_AMT() {
		return OTHER_AMT;
	}

	public void setOTHER_AMT(BigDecimal oTHER_AMT) {
		OTHER_AMT = oTHER_AMT;
	}

	public BigDecimal getINCOME() {
		return INCOME;
	}

	public void setINCOME(BigDecimal iNCOME) {
		INCOME = iNCOME;
	}

	public BigDecimal getCAPITAL() {
		return CAPITAL;
	}

	public void setCAPITAL(BigDecimal cAPITAL) {
		CAPITAL = cAPITAL;
	}

	public BigDecimal getOTHER_FEE() {
		return OTHER_FEE;
	}

	public void setOTHER_FEE(BigDecimal oTHER_FEE) {
		OTHER_FEE = oTHER_FEE;
	}

}
