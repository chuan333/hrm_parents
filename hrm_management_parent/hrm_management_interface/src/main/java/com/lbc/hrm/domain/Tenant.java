package com.lbc.hrm.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.IdType;

import java.util.*;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import org.springframework.scheduling.support.SimpleTriggerContext;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author chuange
 * @since 2019-09-03
 */
@TableName("t_tenant")
public class Tenant extends Model<Tenant> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String companyName;
    private String companyNum;
    private Date registerTime;
    private Boolean state;
    private String address;
    private String logo;

    /**
     * 添加住户信息
     */
    @TableField(exist = false)
    private Employee admin;

    /**
     * 添加套餐信息
     * 套餐和租户是多对对关系
     */
    @TableField(exist = false)
    private List<Meal> meals = new ArrayList<>();

    public List<Map<String,Long>> getTenantMeals(){
        List<Map<String,Long>> maps = new ArrayList<>();
        if (meals.size()>0) {
            for (Meal meal : meals) {
                Map<String,Long> map = new HashMap<>();
                map.put("mealId", meal.getId());
                map.put("tenantId", this.getId());
                maps.add(map);
            }
        }

        return maps;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyNum() {
        return companyNum;
    }

    public void setCompanyNum(String companyNum) {
        this.companyNum = companyNum;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }


    public Employee getAdmin() {
        return admin;
    }

    public void setAdmin(Employee admin) {
        this.admin = admin;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Tenant{" +
        ", id=" + id +
        ", companyName=" + companyName +
        ", companyNum=" + companyNum +
        ", registerTime=" + registerTime +
        ", state=" + state +
        ", address=" + address +
        ", logo=" + logo +
        "}";
    }


}
