package com.ihm.model.customer;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import org.springframework.format.annotation.DateTimeFormat;

@Entity

public class CustomerUserCredential {

    /**
     */
    private String userId;

    /**
     */
    @Size(max = 1)
    private String userCategory;

    /**
     */
    @Size(max = 20)
    private String passKey;

    /**
     */
    private Integer userLoginAttempt;

    /**
     */
    @Size(max = 1)
    private String userLockedFlg;

    /**
     */
    @Size(max = 1)
    private String userActiveFlg;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date userActivationDate;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date userExpiryDate;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date passwordExpiryDate;

    /**
     */
    @Size(max = 1)
    private String disableFlg;

    /**
     */
    @Size(max = 16)
    private String createdBy;

    /**
     */
    @Size(max = 16)
    private String updatedBy;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date createdOn;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date updatedOn;

	public String getUserId() {
        return this.userId;
    }

	public void setUserId(String userId) {
        this.userId = userId;
    }

	public String getUserCategory() {
        return this.userCategory;
    }

	public void setUserCategory(String userCategory) {
        this.userCategory = userCategory;
    }

	public String getPassKey() {
        return this.passKey;
    }

	public void setPassKey(String passKey) {
        this.passKey = passKey;
    }

	public Integer getUserLoginAttempt() {
        return this.userLoginAttempt;
    }

	public void setUserLoginAttempt(Integer userLoginAttempt) {
        this.userLoginAttempt = userLoginAttempt;
    }

	public String getUserLockedFlg() {
        return this.userLockedFlg;
    }

	public void setUserLockedFlg(String userLockedFlg) {
        this.userLockedFlg = userLockedFlg;
    }

	public String getUserActiveFlg() {
        return this.userActiveFlg;
    }

	public void setUserActiveFlg(String userActiveFlg) {
        this.userActiveFlg = userActiveFlg;
    }

	public Date getUserActivationDate() {
        return this.userActivationDate;
    }

	public void setUserActivationDate(Date userActivationDate) {
        this.userActivationDate = userActivationDate;
    }

	public Date getUserExpiryDate() {
        return this.userExpiryDate;
    }

	public void setUserExpiryDate(Date userExpiryDate) {
        this.userExpiryDate = userExpiryDate;
    }

	public Date getPasswordExpiryDate() {
        return this.passwordExpiryDate;
    }

	public void setPasswordExpiryDate(Date passwordExpiryDate) {
        this.passwordExpiryDate = passwordExpiryDate;
    }

	public String getDisableFlg() {
        return this.disableFlg;
    }

	public void setDisableFlg(String disableFlg) {
        this.disableFlg = disableFlg;
    }

	public String getCreatedBy() {
        return this.createdBy;
    }

	public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

	public String getUpdatedBy() {
        return this.updatedBy;
    }

	public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

	public Date getCreatedOn() {
        return this.createdOn;
    }

	public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

	public Date getUpdatedOn() {
        return this.updatedOn;
    }

	public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

	@Version
    @Column(name = "version")
    private Integer version;

	public Long getId() {
        return this.id;
    }

	public void setId(Long id) {
        this.id = id;
    }

	public Integer getVersion() {
        return this.version;
    }

	public void setVersion(Integer version) {
        this.version = version;
    }

	public String toJson() {
        return new JSONSerializer()
        .exclude("*.class").deepSerialize(this);
    }

	public String toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").deepSerialize(this);
    }

	public static CustomerUserCredential fromJsonToCustomerUserCredential(String json) {
        return new JSONDeserializer<CustomerUserCredential>()
        .use(null, CustomerUserCredential.class).deserialize(json);
    }

	public static String toJsonArray(Collection<CustomerUserCredential> collection) {
        return new JSONSerializer()
        .exclude("*.class").deepSerialize(collection);
    }

	public static String toJsonArray(Collection<CustomerUserCredential> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").deepSerialize(collection);
    }

	public static Collection<CustomerUserCredential> fromJsonArrayToCustomerUserCredentials(String json) {
        return new JSONDeserializer<List<CustomerUserCredential>>()
        .use("values", CustomerUserCredential.class).deserialize(json);
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
