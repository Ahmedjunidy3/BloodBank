
package com.example.bloodbank.data.model.generalSource;

import com.example.bloodbank.data.model.login.Client;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GeneralSourceData {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("governorate_id")
    @Expose
    private String governorateId;
    @SerializedName("governorate")
    @Expose
    private GeneralSourceData governorate;
    @SerializedName("governorates")
    @Expose
    private List<String> governorates = null;
    @SerializedName("blood_types")
    @Expose
    private List<String> bloodTypes = null;
    @SerializedName("attached")
    @Expose
    private List<Object> attached = null;
    @SerializedName("detached")
    @Expose
    private List<Integer> detached = null;
    @SerializedName("data")
    @Expose
    private GeneralSourceData dataObject;
    @SerializedName("client_id")
    @Expose
    private String clientId;
    @SerializedName("patient_name")
    @Expose
    private String patientName;
    @SerializedName("patient_age")
    @Expose
    private String patientAge;
    @SerializedName("blood_type_id")
    @Expose
    private String bloodTypeId;
    @SerializedName("bags_num")
    @Expose
    private String bagsNum;
    @SerializedName("hospital_name")
    @Expose
    private String hospitalName;
    @SerializedName("hospital_address")
    @Expose
    private String hospitalAddress;
    @SerializedName("city_id")
    @Expose
    private String cityId;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("notes")
    @Expose
    private String notes;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("city")
    @Expose
    private GeneralSourceData city;
    @SerializedName("client")
    @Expose
    private Client client;
    @SerializedName("blood_type")
    @Expose
    private GeneralSourceData bloodType;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;
    @SerializedName("publish_date")
    @Expose
    private String publishDate;
    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("thumbnail_full_path")
    @Expose
    private String thumbnailFullPath;
    @SerializedName("is_favourite")
    @Expose
    private Boolean isFavourite;
    @SerializedName("category")
    @Expose
    private GeneralSourceData category;
    @SerializedName("donation_request_id")
    @Expose
    private String donationRequestId;
    @SerializedName("pivot")
    @Expose
    private GeneralSourceData pivot;
    @SerializedName("notification_id")
    @Expose
    private String notificationId;
    @SerializedName("is_read")
    @Expose
    private String isRead;
    @SerializedName("pin_code_for_test")
    @Expose
    private Integer pinCodeForTest;
    @SerializedName("mail_fails")
    @Expose
    private List<Object> mailFails = null;
    @SerializedName("email")
    @Expose
    private String email;

    private boolean isSelected;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGovernorateId() {
        return governorateId;
    }

    public void setGovernorateId(String governorateId) {
        this.governorateId = governorateId;
    }

    public GeneralSourceData getGovernorate() {
        return governorate;
    }

    public void setGovernorate(GeneralSourceData governorate) {
        this.governorate = governorate;
    }

    public List<String> getGovernorates() {
        return governorates;
    }

    public void setGovernorates(List<String> governorates) {
        this.governorates = governorates;
    }

    public List<String> getBloodTypes() {
        return bloodTypes;
    }

    public void setBloodTypes(List<String> bloodTypes) {
        this.bloodTypes = bloodTypes;
    }

    public List<Object> getAttached() {
        return attached;
    }

    public void setAttached(List<Object> attached) {
        this.attached = attached;
    }

    public List<Integer> getDetached() {
        return detached;
    }

    public void setDetached(List<Integer> detached) {
        this.detached = detached;
    }

    public GeneralSourceData getDataObject() {
        return dataObject;
    }

    public void setDataObject(GeneralSourceData dataObject) {
        this.dataObject = dataObject;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(String patientAge) {
        this.patientAge = patientAge;
    }

    public String getBloodTypeId() {
        return bloodTypeId;
    }

    public void setBloodTypeId(String bloodTypeId) {
        this.bloodTypeId = bloodTypeId;
    }

    public String getBagsNum() {
        return bagsNum;
    }

    public void setBagsNum(String bagsNum) {
        this.bagsNum = bagsNum;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getHospitalAddress() {
        return hospitalAddress;
    }

    public void setHospitalAddress(String hospitalAddress) {
        this.hospitalAddress = hospitalAddress;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public GeneralSourceData getCity() {
        return city;
    }

    public void setCity(GeneralSourceData city) {
        this.city = city;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public GeneralSourceData getBloodType() {
        return bloodType;
    }

    public void setBloodType(GeneralSourceData bloodType) {
        this.bloodType = bloodType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getThumbnailFullPath() {
        return thumbnailFullPath;
    }

    public void setThumbnailFullPath(String thumbnailFullPath) {
        this.thumbnailFullPath = thumbnailFullPath;
    }

    public Boolean getFavourite() {
        return isFavourite;
    }

    public void setFavourite(Boolean favourite) {
        isFavourite = favourite;
    }

    public GeneralSourceData getCategory() {
        return category;
    }

    public void setCategory(GeneralSourceData category) {
        this.category = category;
    }

    public String getDonationRequestId() {
        return donationRequestId;
    }

    public void setDonationRequestId(String donationRequestId) {
        this.donationRequestId = donationRequestId;
    }

    public GeneralSourceData getPivot() {
        return pivot;
    }

    public void setPivot(GeneralSourceData pivot) {
        this.pivot = pivot;
    }

    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    public Integer getPinCodeForTest() {
        return pinCodeForTest;
    }

    public void setPinCodeForTest(Integer pinCodeForTest) {
        this.pinCodeForTest = pinCodeForTest;
    }

    public List<Object> getMailFails() {
        return mailFails;
    }

    public void setMailFails(List<Object> mailFails) {
        this.mailFails = mailFails;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
