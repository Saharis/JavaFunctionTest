package com.virgil.http;

public class BaffleDataModel implements Cloneable {
	public int dataId = 0;// 数据主键
	public String serviceName = "";// 服务名称
	public String dataDescription = "";// 挡板数据Title
	public String createrID = "";// 创建者ID
	public int privacy = 0;// 挡板权限
	public long timecreate = 0l;// 创建时间
	public long timelastupdate = 0l;// 更新时间

	public BaffleDataModel() {

	}

	@Override
	protected BaffleDataModel clone() throws CloneNotSupportedException {
		return (BaffleDataModel) super.clone();
	}
}
