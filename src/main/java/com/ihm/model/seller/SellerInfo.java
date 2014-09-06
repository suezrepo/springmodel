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

public class SellerInfo {

    /**
     */
    private String code;

    /**
     */
    private String name;

    /**
     */
    private String description;

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

    /**
     */
    @ManyToOne
    private SellerAddress address;

	public String toJson() {
        return new JSONSerializer()
        .exclude("*.class").deepSerialize(this);
    }

	public String toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").deepSerialize(this);
    }

	public static SellerInfo fromJsonToSellerInfo(String json) {
        return new JSONDeserializer<SellerInfo>()
        .use(null, SellerInfo.class).deserialize(json);
    }

	public static String toJsonArray(Collection<SellerInfo> collection) {
        return new JSONSerializer()
        .exclude("*.class").deepSerialize(collection);
    }

	public static String toJsonArray(Collection<SellerInfo> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").deepSerialize(collection);
    }

	public static Collection<SellerInfo> fromJsonArrayToSellerInfoes(String json) {
        return new JSONDeserializer<List<SellerInfo>>()
        .use("values", SellerInfo.class).deserialize(json);
    }

	public String getCode() {
        return this.code;
    }

	public void setCode(String code) {
        this.code = code;
    }

	public String getName() {
        return this.name;
    }

	public void setName(String name) {
        this.name = name;
    }

	public String getDescription() {
        return this.description;
    }

	public void setDescription(String description) {
        this.description = description;
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

	public SellerAddress getAddress() {
        return this.address;
    }

	public void setAddress(SellerAddress address) {
        this.address = address;
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

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
