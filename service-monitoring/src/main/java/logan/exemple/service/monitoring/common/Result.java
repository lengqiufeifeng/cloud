package logan.exemple.service.monitoring.common;

/**
 * @Desc：
 * 
 * @author: Logan
 * @version: 1.0
 * @date: 2016年12月19日 下午2:38:00
 */
public class Result {
	public int resultCode;
	public String errCode;
	public String errMessage;
	public String extMessage;
	public Object dataContent;

	public Object getDataContent() {
		return dataContent;
	}

	public void setDataContent(Object dataContent) {
		this.dataContent = dataContent;
	}

	public int getResultCode() {
		return resultCode;
	}
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	public String getErrMessage() {
		return errMessage;
	}
	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}
	public String getExtMessage() {
		return extMessage;
	}
	public void setExtMessage(String extMessage) {
		this.extMessage = extMessage;
	}

}
