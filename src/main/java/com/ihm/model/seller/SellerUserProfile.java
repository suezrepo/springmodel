package com.ihm.model.seller;
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
import javax.persistence.ManyToOne;

@Entity

public class SellerUserProfile {

    /**
     */
    private String givenName;

    /**
     */
    private String lastName;

    /**
     */
    @Size(max = 5)
    private String gender;

    /**
     */
    private String emailAddress;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date dob;

    /**
     */
    @Size(max = 16)
    private String primaryPhoneNo;

    /**
     */
    @Size(max = 16)
    private String secondaryPhoneNo;

    /**
     */
    @Size(max = 16)
    private String emergencyContactNo;

    /**
     */
    @ManyToOne
    private SellerInfo sellerInfo;

    /**
     */
    @ManyToOne
    private SellerUserCredential internalUserId;

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

	public String toJson() {
        return new JSONSerializer()
        .exclude("*.class").deepSerialize(this);
    }

	public String toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").deepSerialize(this);
    }

	public static SellerUserProfile fromJsonToSellerUserProfile(String json) {
        return new JSONDeserializer<SellerUserProfile>()
        .use(null, SellerUserProfile.class).deserialize(json);
    }

	public static String toJsonArray(Collection<SellerUserProfile> collection) {
        return new JSONSerializer()
        .exclude("*.class").deepSerialize(collection);
    }

	public static String toJsonArray(Collection<SellerUserProfile> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").deepSerialize(collection);
    }

	public static Collection<SellerUserProfile> fromJsonArrayToSellerUserProfiles(String json) {
        return new JSONDeserializer<List<SellerUserProfile>>()
        .use("values", SellerUserProfile.class).deserialize(json);
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
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

	public String getGivenName() {
        return this.givenName;
    }

	public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

	public String getLastName() {
        return this.lastName;
    }

	public void setLastName(String lastName) {
        this.lastName = lastName;
    }

	public String getGender() {
        return this.gender;
    }

	public void setGender(String gender) {
        this.gender = gender;
    }

	public String getEmailAddress() {
        return this.emailAddress;
    }

	public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

	public Date getDob() {
        return this.dob;
    }

	public void setDob(Date dob) {
        this.dob = dob;
    }

	public String getPrimaryPhoneNo() {
        return this.primaryPhoneNo;
    }

	public void setPrimaryPhoneNo(String primaryPhoneNo) {
        this.primaryPhoneNo = primaryPhoneNo;
    }

	public String getSecondaryPhoneNo() {
        return this.secondaryPhoneNo;
    }

	public void setSecondaryPhoneNo(String secondaryPhoneNo) {
        this.secondaryPhoneNo = secondaryPhoneNo;
    }

	public String getEmergencyContactNo() {
        return this.emergencyContactNo;
    }

	public void setEmergencyContactNo(String emergencyContactNo) {
        this.emergencyContactNo = emergencyContactNo;
    }

	public SellerInfo getSellerInfo() {
        return this.sellerInfo;
    }

	public void setSellerInfo(SellerInfo sellerInfo) {
        this.sellerInfo = sellerInfo;
    }

	public SellerUserCredential getInternalUserId() {
        return this.internalUserId;
    }

	public void setInternalUserId(SellerUserCredential internalUserId) {
        this.internalUserId = internalUserId;
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
}
