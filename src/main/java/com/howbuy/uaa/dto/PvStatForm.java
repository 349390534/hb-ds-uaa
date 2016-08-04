package com.howbuy.uaa.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 *  PV 统计
 * </pre>
 *
 * @author ji.ma
 * @create 13-3-25 下午3:43
 * @modify
 * @since JDK1.6
 */
public class PvStatForm {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private Long srcNum;

    private String srcUrl;

    private Long srcPv;

    private String toUrl;

    private Long toPv;

    private String bookUrl;

    private Long bookPv;

    public Long getSrcNum() {
        return srcNum;
    }

    public void setSrcNum(Long srcNum) {
        this.srcNum = srcNum;
    }

    public String getSrcUrl() {
        return srcUrl;
    }

    public void setSrcUrl(String srcUrl) {
        this.srcUrl = srcUrl;
    }

    public Long getSrcPv() {
        return srcPv;
    }

    public void setSrcPv(Long srcPv) {
        this.srcPv = srcPv;
    }

    public String getToUrl() {
        return toUrl;
    }

    public void setToUrl(String toUrl) {
        this.toUrl = toUrl;
    }

    public Long getToPv() {
        return toPv;
    }

    public void setToPv(Long toPv) {
        this.toPv = toPv;
    }

    public String getBookUrl() {
        return bookUrl;
    }

    public void setBookUrl(String bookUrl) {
        this.bookUrl = bookUrl;
    }

    public Long getBookPv() {
        return bookPv;
    }

    public void setBookPv(Long bookPv) {
        this.bookPv = bookPv;
    }
}
