package com.virgil.http;

public class BaffleDataModel implements Cloneable {
	public int dataId = 0;// ��������
	public String serviceName = "";// ��������
	public String dataDescription = "";// ��������Title
	public String createrID = "";// ������ID
	public int privacy = 0;// ����Ȩ��
	public long timecreate = 0l;// ����ʱ��
	public long timelastupdate = 0l;// ����ʱ��

	public BaffleDataModel() {

	}

	@Override
	protected BaffleDataModel clone() throws CloneNotSupportedException {
		return (BaffleDataModel) super.clone();
	}
}
