package cn.tz.www.customer.view;

import java.util.Date;
public class NewsVo {

	
	private Long id;
	private Integer status;
	private String title;
	private String img;
	private Integer type;
    private String context;
	private String createTime;
	private Integer viewTimes;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Integer getViewTimes() {
		return viewTimes;
	}
	public void setViewTimes(Integer viewTimes) {
		this.viewTimes = viewTimes;
	}

}
