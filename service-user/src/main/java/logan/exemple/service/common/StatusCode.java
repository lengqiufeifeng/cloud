package logan.exemple.service.common;

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
    PwdErr("密码错误", 6),
    Disable("禁用", 7),
    Invalid("认证失败", 8),
    UserExist("手机号已注册", 9),
    UserNoth("用户不存在", 9),
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

    public static String SYSTEM_PASSWORD = "123456";

    //角色
    public static String ROLE_SYSTEM = "system"; //系统管理员
    public static String ROLE_ADMIN = "admin"; //保险公司总公司
    public static String ROLE_BRANCH = "branch"; //保险公司分公司
    public static String ROLE_AUCTION = "auction"; //拍卖公司
    // 成员变量
    private String msg;
    private Integer index;

    public static String AUCTION_INVALID = "0"; //无效
    public static String AUCTION_EFFECTIVE = "1"; //有效
    //是否存在
    public static String IS_EXIST = "100000";    //00表示存在
    public static String IS_NO_EXIST = "100001";//01表示不存在

    //组织机构级别
    public static String TOP_ORG_CODE = "";  //顶级组织机构
    public static int ORG_HEADQUARTERS = 1;
    public static int ORG_BRANCH_COMPANY = 2;

    public static String DEFAULT_SELECT_VALUE = "";

    public static String CUSTOMER_TYPE_LIVENEO = "0";//朗泰公司
    public static String CUSTOMER_TYPE_INSURANCE_COMPANY = "1"; //保险公司
    public static String CUSTOMER_TYPE_AUCTION = "2"; //拍卖公司

    //合作方式
    public static String COOPERATE_TYPE_BRANCH = "1";    //分公司合作
    public static String COOPERATE_TYPE_TOTAL = "0";  //总公司合作


    public static String IMAGE_DAMAGEPHOTE_TYPE = "0";
    public static String IMAGE_PROCEDURESPHOTO_TYPE = "1";
    //easyUi
    public static String EASYUI_TREEGRID_CLOSED = "closed"; //treegrid 菜单闭合属性
    public static boolean EASYUI_TREEGRID_CHECKED = true; //treegrid 选中
    public static boolean EASYUI_TREEGRID_NO_CHECKED = false; //treegrid 不选中
    public static String EASYUI_TREEGRID_OPEN = "open"; //treegrid 菜单闭合属性  展开

    // 构造方法
    private StatusCode(String name, Integer index) {
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
