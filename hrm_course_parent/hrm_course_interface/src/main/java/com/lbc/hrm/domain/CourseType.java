package com.lbc.hrm.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程目录
 * </p>
 *
 * @author yhptest
 * @since 2019-08-31
 */
@Data
@TableName("t_course_type")
public class CourseType extends Model<CourseType> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private Long createTime;
    private Long updateTime;
    /**
     * 类型名
     */
    private String name;
    /**
     * 父ID
     */
    private Long pid;

    /**
     * mybatisplus关联查询
     */
    @TableField(exist = false )
    private CourseType parent;
    /**
     * 图标
     */
    private String logo;
    /**
     * 描述
     */
    private String description;
    private Integer sortIndex;
    /**
     * 路径
     */
    private String path;
    /**
     * 商品数量
     */
    private Integer totalCount;

    //用来存放儿子
    @TableField(exist = false)
    private List<CourseType> children = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(Integer sortIndex) {
        this.sortIndex = sortIndex;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public CourseType getParent() {
        return parent;
    }

    public void setParent(CourseType parent) {
        this.parent = parent;
    }

    public List<CourseType> getChildren() {
        return children;
    }

    public void setChildren(List<CourseType> children) {
        this.children = children;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "CourseType{" +
        ", id=" + id +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", name=" + name +
        ", pid=" + pid +
        ", logo=" + logo +
        ", description=" + description +
        ", sortIndex=" + sortIndex +
        ", path=" + path +
        ", totalCount=" + totalCount +
        "}";
    }

    public CourseType(Long id, Long createTime, Long updateTime, String name, Long pid, CourseType parent, String logo, String description, Integer sortIndex, String path, Integer totalCount, List<CourseType> children) {
        this.id = id;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.name = name;
        this.pid = pid;
        this.parent = parent;
        this.logo = logo;
        this.description = description;
        this.sortIndex = sortIndex;
        this.path = path;
        this.totalCount = totalCount;
        this.children = children;
    }

    public CourseType() {
    }
}
