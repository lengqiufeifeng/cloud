package logan.exemple.dispatch.model.fact;

/**
 * 你的支持是我努力的最大动力！社区的建立离不开你的支持。
 * 此系列课程正在持续更新中，相关讨论QQ（593177274）已经建立，欢迎大家加入讨论。
 * 本人博客地址：http://blog.csdn.net/wo541075754
 */
public class AddressCheckResult {

    private boolean postCodeResult = false; // true:通过校验；false：未通过校验

    public boolean isPostCodeResult() {
        return postCodeResult;
    }

    public void setPostCodeResult(boolean postCodeResult) {
        this.postCodeResult = postCodeResult;
    }
}
