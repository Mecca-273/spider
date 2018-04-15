package com.mecca.spider.exhibition.domain;

/**
 * @author Created by Aijun Zhang
 * @since 18/4/12.
 */
public class UserDTO {
    private String time;
    private String place;
    private String venue;
    private String venueUrl;

    private String name;
    private String type;
    private String url;
    private String city;
    private String valid;
    private String area;
    private String num;
    private String visitorNum;
    private String content;
    private String organization;
    private String organizationUrl;
    private String address;
    private String telephone;
    private String user1;
    private String mobile;
    private String email;
    private String user2;
    private String mobile2;
    private String onlineStatus;
    private String logo;

    private String nextPageUrl;

    private String contact;

    public UserDTO(){}
    public UserDTO(String nextPageUrl){
        this.nextPageUrl = nextPageUrl;
    }

    public UserDTO initDetail(String venue, String venueUrl, String venueArea, String organization, String organizationUrl,
                              String content, String contact){
        this.venue = venue;
        this.venueUrl = venueUrl;
        this.area = venueArea;
        this.organization = organization;
        this.organizationUrl = organizationUrl;
        this.content = content;
        //此处须处理联系方式
        this.contact = contact;
        return this;
    }


    @Override
    public String toString(){
        return time+"\t"+city+"\t"+type+"\t"+"\t"+name+"\t"+url+"\n";
    }

    public String getVenueUrl() {
        return venueUrl;
    }

    public void setVenueUrl(String venueUrl) {
        this.venueUrl = venueUrl;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getOrganizationUrl() {
        return organizationUrl;
    }

    public void setOrganizationUrl(String organizationUrl) {
        this.organizationUrl = organizationUrl;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getVisitorNum() {
        return visitorNum;
    }

    public void setVisitorNum(String visitorNum) {
        this.visitorNum = visitorNum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getUser1() {
        return user1;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser2() {
        return user2;
    }

    public void setUser2(String user2) {
        this.user2 = user2;
    }

    public String getMobile2() {
        return mobile2;
    }

    public void setMobile2(String mobile2) {
        this.mobile2 = mobile2;
    }

    public String getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(String onlineStatus) {
        this.onlineStatus = onlineStatus;
    }
}
