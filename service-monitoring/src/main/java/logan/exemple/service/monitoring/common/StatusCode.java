package logan.exemple.service.monitoring.common;

/**
 * 系统状态码定义
 * 
 * @author logan 2016-6-8
 * @qq 425018553
 */
public enum StatusCode {
	Success("成功", 1),
	Fail("失败", 0),
	NoData("无数据", 3),
	MisParam("缺少参数", 4),
	JsonErr("json格式错误", 5),
	PwdErr("密码错误",6), 
	Disable("禁用", 7),
	Invalid("认证失败", 8),
	UserExist("手机号已注册", 9),
	UserNoth("用户不存在",9),
	CodeErr("验证码错误", 11),
	DoesNot("记录不存在", 12),
	ValidatePass("校验通过", 14),
	ValidateFail("校验未通过", 15),
	DateIntersection("日期存在交集", 16),
	EXISTBRANCH("已存在该分公司合作协议", 17),
	DataException("数据异常", 500),
	Exception("异常", 500),
	LOGINEXCEPTION("登录异常", 100),
	DATEERROR("异常", 18);
	

	//角色
	public static String ROLE_SYSTEM="system"; //系统管理员
	public static String ROLE_ADMIN="admin"; //保险公司总公司
	public static String ROLE_BRANCH="branch"; //保险公司分公司
	public static String ROLE_AUCTION="auction"; //拍卖公司
	// 成员变量
	private String msg;
	private Integer index;
	
	public static String AUCTION_INVALID="0"; //无效
	public static String AUCTION_EFFECTIVE="1"; //有效

	// 构造方法
    StatusCode(String name, Integer index) {
		this.msg = name;
		this.index = index;
	}

	// 普通方法
	public static String getName(int index) {
		for (StatusCode c : StatusCode.values()) {
			if (c.getIndex() == index) {
				return c.msg;
			}
		}
		return null;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
