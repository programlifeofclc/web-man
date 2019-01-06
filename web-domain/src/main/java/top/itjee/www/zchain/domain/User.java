package top.itjee.www.zchain.domain;

import java.util.Date;

public class User {

    private Long id;
    private String name;
    private String xingHao;
    private String pinPai;
    private Date createTime;
    private String updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getXingHao() {
        return xingHao;
    }

    public void setXingHao(String xingHao) {
        this.xingHao = xingHao;
    }

    public String getPinPai() {
        return pinPai;
    }

    public void setPinPai(String pinPai) {
        this.pinPai = pinPai;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
