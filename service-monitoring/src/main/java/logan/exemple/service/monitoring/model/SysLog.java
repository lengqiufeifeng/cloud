package logan.exemple.service.monitoring.model;


import javax.persistence.*;
import java.util.Date;

/**
 * Created by qiufeng on 2017/9/19.
 */
@Entity
@Table(name = "sys_log" )
public class SysLog {


    @Transient
    public String uuid;//业务唯一标识，不映射
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "log_id" )
    public Integer logId;

    @Column(name = "sys_name" )
    public String sysName;

    @Column(name = "sys_code" )
    public String sysCode;

    @Column(name = "service_name" )
    public String serviceName;

    @Column(name = "service_code" )
    public String serviceCode;

    @Column(name = "method_name" )
    public String methodName;

    @Column(name = "request_data" )
    public String requestData;

    @Column(name = "response_data" )
    public String responseData;

    @Column(name = "log_type" )
    public Integer logType;

    @Column(name = "update_time" )
    public Date updateTime;

    @Column(name = "create_time" )
    public Date createTime;

}
