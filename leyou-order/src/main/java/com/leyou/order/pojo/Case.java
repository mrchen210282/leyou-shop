package com.leyou.order.pojo;


import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "tb_case")
public class Case {
    @Id
    private Integer caseId;

    private String caseContent;

    private Date createTime;

    public Integer getCaseId() {
        return caseId;
    }

    public void setCaseId(Integer caseId) {
        this.caseId = caseId;
    }

    public String getCaseContent() {
        return caseContent;
    }

    public void setCaseContent(String caseContent) {
        this.caseContent = caseContent;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
