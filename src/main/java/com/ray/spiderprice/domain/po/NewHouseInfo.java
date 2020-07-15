package com.ray.spiderprice.domain.po;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * Package:com.ray.spiderprice.domain.po
 * *Author:ray
 * *version:...
 * *Created in 2020/1/26  13:27
 **/
//武汉新房数据的实体类
@Table(name = "new_house_info")
public class NewHouseInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	//小区编号
	@Column(name = "village_id")
	private String villageId;

	//小区名称
	@Column(name = "village_name")
	private String villageName;

	//房屋属性
	@Column(name = "house_type")
	private String houseType;

	//房屋面积(小)
	@Column(name = "min_area")
	private BigDecimal min_area;

	//房屋面积(大)
	@Column(name = "max_area")
	private BigDecimal max_area;

	//房屋总价
	@Column(name = "sum_price")
	private BigDecimal sumPrice;

	//房屋单价(x/平方)
	@Column(name = "unit_price")
	private BigDecimal unitPrice;

	//房屋信息,两室一厅
	@Column(name = "house_maininfo")
	private String houseMaininfo;

	//大区域
	@Column(name = "first_location")
	private String firstLocation;

	//二级区域
	@Column(name = "second_location")
	private String secondLocation;

	//创建时间
	@Column(name = "create_time")
	private Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}

	public BigDecimal getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(BigDecimal sumPrice) {
		this.sumPrice = sumPrice;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getHouseMaininfo() {
		return houseMaininfo;
	}

	public void setHouseMaininfo(String houseMaininfo) {
		this.houseMaininfo = houseMaininfo;
	}


	public String getFirstLocation() {
		return firstLocation;
	}

	public void setFirstLocation(String firstLocation) {
		this.firstLocation = firstLocation;
	}

	public String getSecondLocation() {
		return secondLocation;
	}

	public void setSecondLocation(String secondLocation) {
		this.secondLocation = secondLocation;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getVillageId() {
		return villageId;
	}

	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}

	public BigDecimal getMin_area() {
		return min_area;
	}

	public void setMin_area(BigDecimal min_area) {
		this.min_area = min_area;
	}

	public BigDecimal getMax_area() {
		return max_area;
	}

	public void setMax_area(BigDecimal max_area) {
		this.max_area = max_area;
	}

	public String getHouseType() {
		return houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}

	@Override
	public String toString() {
		return "NewHouseInfo{" +
				"id=" + id +
				", villageId='" + villageId + '\'' +
				", villageName='" + villageName + '\'' +
				", min_area=" + min_area +
				", max_area=" + max_area +
				", sumPrice=" + sumPrice +
				", unitPrice=" + unitPrice +
				", houseMaininfo='" + houseMaininfo + '\'' +
				", firstLocation='" + firstLocation + '\'' +
				", secondLocation='" + secondLocation + '\'' +
				", createTime=" + createTime +
				'}';
	}
}
