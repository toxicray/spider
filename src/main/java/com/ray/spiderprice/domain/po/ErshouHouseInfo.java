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
//武汉二手房数据的实体类
@Table(name = "ershou_house_info")
public class ErshouHouseInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "house_id")
	private String houseId;

	//小区名称
	@Column(name = "village_name")
	private String villageName;

	//房屋面积
	@Column(name = "area")
	private BigDecimal area;

	//房屋总价
	@Column(name = "sum_price")
	private BigDecimal sumPrice;

	//房屋单价
	@Column(name = "unit_price")
	private BigDecimal unitPrice;

	//房屋信息,两室一厅?
	@Column(name = "house_maininfo")
	private String houseMaininfo;

	//楼层信息(18层/高楼层)
	@Column(name = "house_subinfo")
	private String houseSubinfo;

	//楼创建)
	@Column(name = "build_time")
	private String buildTime;

	//大区域
	@Column(name = "first_location")
	private String firstLocation;

	//二级区域
	@Column(name = "second_location")
	private String secondLocation;

	//梯户比
	@Column(name = "ratio")
	private String ratio;

	//房屋年限
	@Column(name = "house_limit")
	private String houseLimit;

	//房屋挂牌时间
	@Column(name = "hang_time")
	private String hangTime;

	//创建时间
	@Column(name = "create_time")
	private Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHouseId() {
		return houseId;
	}

	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}

	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}

	public BigDecimal getArea() {
		return area;
	}

	public void setArea(BigDecimal area) {
		this.area = area;
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

	public String getHouseSubinfo() {
		return houseSubinfo;
	}

	public void setHouseSubinfo(String houseSubinfo) {
		this.houseSubinfo = houseSubinfo;
	}

	public String getBuildTime() {
		return buildTime;
	}

	public void setBuildTime(String buildTime) {
		this.buildTime = buildTime;
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

	public String getRatio() {
		return ratio;
	}

	public void setRatio(String ratio) {
		this.ratio = ratio;
	}

	public String getHouseLimit() {
		return houseLimit;
	}

	public void setHouseLimit(String houseLimit) {
		this.houseLimit = houseLimit;
	}

	public String getHangTime() {
		return hangTime;
	}

	public void setHangTime(String hangTime) {
		this.hangTime = hangTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "ErshouHouseInfo{" +
				"id=" + id +
				", villageName='" + villageName + '\'' +
				", area=" + area +
				", sumPrice=" + sumPrice +
				", unitPrice=" + unitPrice +
				", houseMaininfo='" + houseMaininfo + '\'' +
				", houseSubinfo='" + houseSubinfo + '\'' +
				", buildTime='" + buildTime + '\'' +
				", firstLocation='" + firstLocation + '\'' +
				", secondLocation='" + secondLocation + '\'' +
				", ratio='" + ratio + '\'' +
				", houseLimit='" + houseLimit + '\'' +
				", hangTime='" + hangTime + '\'' +
				", createTime='" + createTime + '\'' +
				'}';
	}
}
