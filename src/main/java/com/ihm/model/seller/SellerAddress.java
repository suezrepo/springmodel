package com.ihm.model.seller;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import javax.validation.constraints.Size;
import com.ihm.model.StateInfo;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import javax.persistence.ManyToOne;
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

public class SellerAddress {

    /**
     */
    @Size(max = 1)
    private String addressType;

    /**
     */
    private String address1;

    /**
     */
    private String address2;

    /**
     */
    private String address3;

    /**
     */
    private String city;

    /**
     */
    @ManyToOne
    private StateInfo stateInfo;

    /**
     */
    private String zip;

    /**
     */
    private Long latitude;

    /**
     */
    private Long longitude;

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

	public String getAddressType() {
        return this.addressType;
    }

	public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

	public String getAddress1() {
        return this.address1;
    }

	public void setAddress1(String address1) {
        this.address1 = address1;
    }

	public String getAddress2() {
        return this.address2;
    }

	public void setAddress2(String address2) {
        this.address2 = address2;
    }

	public String getAddress3() {
        return this.address3;
    }

	public void setAddress3(String address3) {
        this.address3 = address3;
    }

	public String getCity() {
        return this.city;
    }

	public void setCity(String city) {
        this.city = city;
    }

	public StateInfo getStateInfo() {
        return this.stateInfo;
    }

	public void setStateInfo(StateInfo stateInfo) {
        this.stateInfo = stateInfo;
    }

	public String getZip() {
        return this.zip;
    }

	public void setZip(String zip) {
        this.zip = zip;
    }

	public Long getLatitude() {
        return this.latitude;
    }

	public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }

	public Long getLongitude() {
        return this.longitude;
    }

	public void setLongitude(Long longitude) {
        this.longitude = longitude;
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

	public String toJson() {
        return new JSONSerializer()
        .exclude("*.class").deepSerialize(this);
    }

	public String toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").deepSerialize(this);
    }

	public static SellerAddress fromJsonToSellerAddress(String json) {
        return new JSONDeserializer<SellerAddress>()
        .use(null, SellerAddress.class).deserialize(json);
    }

	public static String toJsonArray(Collection<SellerAddress> collection) {
        return new JSONSerializer()
        .exclude("*.class").deepSerialize(collection);
    }

	public static String toJsonArray(Collection<SellerAddress> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").deepSerialize(collection);
    }

	public static Collection<SellerAddress> fromJsonArrayToSellerAddresses(String json) {
        return new JSONDeserializer<List<SellerAddress>>()
        .use("values", SellerAddress.class).deserialize(json);
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
