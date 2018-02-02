package com.yuyu.bo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * OpenLock entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "OPEN_LOCK", schema = "SIOR")
public class OpenLock implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String code;
	private String havelock;
	private String type;
	private String remark;
	private String telmessagenum;
	private String telappid;
	private String telpwd;
	private String telurl;

	// Constructors

	/** default constructor */
	public OpenLock() {
	}

	/** minimal constructor */
	public OpenLock(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public OpenLock(Integer id, String name, String code, String havelock,
			String type, String remark, String telmessagenum, String telappid,
			String telpwd, String telurl) {
		this.id = id;
		this.name = name;
		this.code = code;
		this.havelock = havelock;
		this.type = type;
		this.remark = remark;
		this.telmessagenum = telmessagenum;
		this.telappid = telappid;
		this.telpwd = telpwd;
		this.telurl = telurl;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false, precision = 5, scale = 0)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "NAME", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "CODE", length = 100)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "HAVELOCK", length = 4)
	public String getHavelock() {
		return this.havelock;
	}

	public void setHavelock(String havelock) {
		this.havelock = havelock;
	}

	@Column(name = "TYPE", length = 10)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "REMARK", length = 500)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "TELMESSAGENUM", length = 100)
	public String getTelmessagenum() {
		return this.telmessagenum;
	}

	public void setTelmessagenum(String telmessagenum) {
		this.telmessagenum = telmessagenum;
	}

	@Column(name = "TELAPPID", length = 100)
	public String getTelappid() {
		return this.telappid;
	}

	public void setTelappid(String telappid) {
		this.telappid = telappid;
	}

	@Column(name = "TELPWD", length = 100)
	public String getTelpwd() {
		return this.telpwd;
	}

	public void setTelpwd(String telpwd) {
		this.telpwd = telpwd;
	}

	@Column(name = "TELURL", length = 500)
	public String getTelurl() {
		return this.telurl;
	}

	public void setTelurl(String telurl) {
		this.telurl = telurl;
	}

}