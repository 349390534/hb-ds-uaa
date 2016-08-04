/**
 * 
 */
package com.howbuy.uaa.dto;

/**
 * @author qiankun.li
 * 
 */
public class PenetrateApiDataResponse {

	/**
	 * 日期
	 */
	private String statdt;

	/**
	 * 分配部门（EC_RS，EC_RT，IC,好买已分配客户合计）
	 */
	private String departcode;

	/**
	 * 首次交易为普通公募基金总客户数
	 */
	private long cnt_b;
	/**
	 * 首次交易为货币基金的客户总数
	 */
	private long cnt_c;
	/**
	 * 普通公募基金存量（保有量市值）到过20万以上的客户总数
	 */
	private long cnt_d;
	/**
	 * 交易股票类基金的总客户数， 股票类基金是指不含货币和专户的其他公募基金
	 */
	private long cnt_e;
	/**
	 * 首次交易为公募基金的高净值产品交易总客户数， 高净值产品是指公募专户和私募产品
	 */
	private long cnt_f;
	/**
	 * 货转股人数
	 */
	private long cnt_c_e;
	/**
	 * 货转股的转化率
	 */
	private String c_e2c;
	/**
	 * 公转私的转化率
	 */
	private String f2b;
	/**
	 * 公转私的人数-20W以上(含)
	 */
	private long cnt_d_f;
	/**
	 * 公转私路径B->D->F的转化率
	 */
	private String d_f2b;
	/**
	 * 公转私路径B->E->F的转化数
	 */
	private long cnt_e_f;
	/**
	 * 公转私路径B->E->F的转化率
	 */
	private String e_f2b;

	// -----------六象限接口返回字段----------
	/**
	 * ">=20W"或"<20W"
	 */
	private String asset20w;
	/**
	 * 公转私的人数 首次货币(仅货币)
	 */
	private long a2c_cnt;
	/**
	 * 公转私的人数 首次货币（后买股）
	 */
	private long ab2c_cnt;
	/**
	 * 公转私的人数 首次非货币
	 */
	private long b2c_cnt;
	/**
	 * 公转私的人数
	 */
	private long sumcnt;

	public String getStatdt() {
		return statdt;
	}

	public void setStatdt(String statdt) {
		this.statdt = statdt;
	}

	public String getDepartcode() {
		return departcode;
	}

	public void setDepartcode(String departcode) {
		this.departcode = departcode;
	}

	public long getCnt_b() {
		return cnt_b;
	}

	public void setCnt_b(long cnt_b) {
		this.cnt_b = cnt_b;
	}

	public long getCnt_c() {
		return cnt_c;
	}

	public void setCnt_c(long cnt_c) {
		this.cnt_c = cnt_c;
	}

	public long getCnt_d() {
		return cnt_d;
	}

	public void setCnt_d(long cnt_d) {
		this.cnt_d = cnt_d;
	}

	public long getCnt_e() {
		return cnt_e;
	}

	public void setCnt_e(long cnt_e) {
		this.cnt_e = cnt_e;
	}

	public long getCnt_f() {
		return cnt_f;
	}

	public void setCnt_f(long cnt_f) {
		this.cnt_f = cnt_f;
	}

	public long getCnt_c_e() {
		return cnt_c_e;
	}

	public void setCnt_c_e(long cnt_c_e) {
		this.cnt_c_e = cnt_c_e;
	}

	public String getC_e2c() {
		return c_e2c;
	}

	public void setC_e2c(String c_e2c) {
		this.c_e2c = c_e2c;
	}

	public String getF2b() {
		return f2b;
	}

	public void setF2b(String f2b) {
		this.f2b = f2b;
	}

	public long getCnt_d_f() {
		return cnt_d_f;
	}

	public void setCnt_d_f(long cnt_d_f) {
		this.cnt_d_f = cnt_d_f;
	}

	public String getD_f2b() {
		return d_f2b;
	}

	public void setD_f2b(String d_f2b) {
		this.d_f2b = d_f2b;
	}

	public long getCnt_e_f() {
		return cnt_e_f;
	}

	public void setCnt_e_f(long cnt_e_f) {
		this.cnt_e_f = cnt_e_f;
	}

	public String getE_f2b() {
		return e_f2b;
	}

	public void setE_f2b(String e_f2b) {
		this.e_f2b = e_f2b;
	}

	public String getAsset20w() {
		return asset20w;
	}

	public void setAsset20w(String asset20w) {
		this.asset20w = asset20w;
	}

	public long getA2c_cnt() {
		return a2c_cnt;
	}

	public void setA2c_cnt(long a2c_cnt) {
		this.a2c_cnt = a2c_cnt;
	}

	public long getAb2c_cnt() {
		return ab2c_cnt;
	}

	public void setAb2c_cnt(long ab2c_cnt) {
		this.ab2c_cnt = ab2c_cnt;
	}

	public long getB2c_cnt() {
		return b2c_cnt;
	}

	public void setB2c_cnt(long b2c_cnt) {
		this.b2c_cnt = b2c_cnt;
	}

	public long getSumcnt() {
		return sumcnt;
	}

	public void setSumcnt(long sumcnt) {
		this.sumcnt = sumcnt;
	}

}
