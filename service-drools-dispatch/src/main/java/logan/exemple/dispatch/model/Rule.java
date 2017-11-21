package logan.exemple.dispatch.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;

//@Entity
public class Rule implements Serializable {

	private static final long serialVersionUID = 1L;
//    @Id
//	@GeneratedValue
	private Long id;
//	@Column(nullable = false, unique = true)
	private String ruleKey;
//	@Column(nullable = false)
	private String content;
	private String version;
	private String lastModifyTime;
	private String createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRuleKey() {
		return ruleKey;
	}

	public void setRuleKey(String ruleKey) {
		this.ruleKey = ruleKey;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(String lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}



	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}