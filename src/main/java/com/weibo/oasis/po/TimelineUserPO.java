package com.weibo.oasis.po;

public class TimelineUserPO {

    private Integer cuid;
    private Integer ouid;
    private String cursor;
    private String count;
    private String version;

    public Integer getCuid() {
        return cuid;
    }

    public void setCuid(Integer cuid) {
        this.cuid = cuid;
    }

    public Integer getOuid() {
        return ouid;
    }

    public void setOuid(Integer ouid) {
        this.ouid = ouid;
    }

    public String getCursor() {
        return cursor;
    }

    public void setCursor(String cursor) {
        this.cursor = cursor;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
