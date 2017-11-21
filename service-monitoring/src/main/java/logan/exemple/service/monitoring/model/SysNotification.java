package logan.exemple.service.monitoring.model;



import javax.persistence.*;
import java.util.Date;

/**
 * Created by qiufeng on 2017/9/19.
 */
@Entity
@Table(name = "sys_notification")
public class SysNotification {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="nt_id")
    private Integer  ntId;

    @Column(name="sys_name")
    private String  sysName;

    @Column(name="sys_code")
    private String  sysCode;

    @Column(name="service_name")
    private String  serviceName;

    @Column(name="service_code")
    private String  serviceCode;

    @Column(name="nct_name")
    private String  nctName;

    @Column(name="nct_type")
    private Integer  nctType;

    @Column(name="nct_uri")
    private String  nctUri;

    @Column(name="nct_parameters")
    private String  nctParameters;

    @Column(name="nct_status")
    private Integer  nctStatus;

    @Column(name="updata_time")
    private Date updataTime;

    @Column(name="create_time")
    private Date  createTime;

}
