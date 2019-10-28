/*
 * CustomerEntity.java
 *
 * Copyright (c) 2000-2019 MotionPoint Corporation. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * MotionPoint Corp. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with MotionPoint.
 */
package com.motionpoint.domain;

public class CustomerEntity extends CustomizableEntity {
    private long id;
    private String companyname;
    private String contactname;
    private String email;
    private String address1;
    private String address2;
    private String city;
    private String foreignstate;
    private String zip;
    private String phone;
    private String fax;
    private String state;
    private String country;
    private Integer accesslevel;
    private Float textmonthlylimit;
    private Float filemonthlylimit;
    private Float textdailylimit;
    private String akamaiuserid;
    private String akamaipassword;
    private long concurrency;

    public long getId() {
        return id;
    }

    public void setId(long aId) {
        id = aId;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String aCompanyname) {
        companyname = aCompanyname;
    }

    public String getContactname() {
        return contactname;
    }

    public void setContactname(String aContactname) {
        contactname = aContactname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String aEmail) {
        email = aEmail;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String aAddress1) {
        address1 = aAddress1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String aAddress2) {
        address2 = aAddress2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String aCity) {
        city = aCity;
    }

    public String getForeignstate() {
        return foreignstate;
    }

    public void setForeignstate(String aForeignstate) {
        foreignstate = aForeignstate;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String aZip) {
        zip = aZip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String aPhone) {
        phone = aPhone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String aFax) {
        fax = aFax;
    }

    public String getState() {
        return state;
    }

    public void setState(String aState) {
        state = aState;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String aCountry) {
        country = aCountry;
    }

    public Integer getAccesslevel() {
        return accesslevel;
    }

    public void setAccesslevel(Integer aAccesslevel) {
        accesslevel = aAccesslevel;
    }

    public Float getTextmonthlylimit() {
        return textmonthlylimit;
    }

    public void setTextmonthlylimit(Float aTextmonthlylimit) {
        textmonthlylimit = aTextmonthlylimit;
    }

    public Float getFilemonthlylimit() {
        return filemonthlylimit;
    }

    public void setFilemonthlylimit(Float aFilemonthlylimit) {
        filemonthlylimit = aFilemonthlylimit;
    }

    public Float getTextdailylimit() {
        return textdailylimit;
    }

    public void setTextdailylimit(Float aTextdailylimit) {
        textdailylimit = aTextdailylimit;
    }

    public String getAkamaiuserid() {
        return akamaiuserid;
    }

    public void setAkamaiuserid(String aAkamaiuserid) {
        akamaiuserid = aAkamaiuserid;
    }

    public String getAkamaipassword() {
        return akamaipassword;
    }

    public void setAkamaipassword(String aAkamaipassword) {
        akamaipassword = aAkamaipassword;
    }

    public long getConcurrency() {
        return concurrency;
    }

    public void setConcurrency(long aConcurrency) {
        concurrency = aConcurrency;
    }

    @Override
    public boolean equals(Object aO) {
        if (this == aO)
            return true;
        if (aO == null || getClass() != aO.getClass())
            return false;

        CustomerEntity that = (CustomerEntity) aO;

        if (id != that.id)
            return false;
        if (concurrency != that.concurrency)
            return false;
        if (companyname != null ? !companyname.equals(that.companyname) : that.companyname != null)
            return false;
        if (contactname != null ? !contactname.equals(that.contactname) : that.contactname != null)
            return false;
        if (email != null ? !email.equals(that.email) : that.email != null)
            return false;
        if (address1 != null ? !address1.equals(that.address1) : that.address1 != null)
            return false;
        if (address2 != null ? !address2.equals(that.address2) : that.address2 != null)
            return false;
        if (city != null ? !city.equals(that.city) : that.city != null)
            return false;
        if (foreignstate != null ? !foreignstate.equals(that.foreignstate) : that.foreignstate != null)
            return false;
        if (zip != null ? !zip.equals(that.zip) : that.zip != null)
            return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null)
            return false;
        if (fax != null ? !fax.equals(that.fax) : that.fax != null)
            return false;
        if (state != null ? !state.equals(that.state) : that.state != null)
            return false;
        if (country != null ? !country.equals(that.country) : that.country != null)
            return false;
        if (accesslevel != null ? !accesslevel.equals(that.accesslevel) : that.accesslevel != null)
            return false;
        if (textmonthlylimit != null ? !textmonthlylimit.equals(that.textmonthlylimit) : that.textmonthlylimit != null)
            return false;
        if (filemonthlylimit != null ? !filemonthlylimit.equals(that.filemonthlylimit) : that.filemonthlylimit != null)
            return false;
        if (textdailylimit != null ? !textdailylimit.equals(that.textdailylimit) : that.textdailylimit != null)
            return false;
        if (akamaiuserid != null ? !akamaiuserid.equals(that.akamaiuserid) : that.akamaiuserid != null)
            return false;
        if (akamaipassword != null ? !akamaipassword.equals(that.akamaipassword) : that.akamaipassword != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (companyname != null ? companyname.hashCode() : 0);
        result = 31 * result + (contactname != null ? contactname.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (address1 != null ? address1.hashCode() : 0);
        result = 31 * result + (address2 != null ? address2.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (foreignstate != null ? foreignstate.hashCode() : 0);
        result = 31 * result + (zip != null ? zip.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (fax != null ? fax.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (accesslevel != null ? accesslevel.hashCode() : 0);
        result = 31 * result + (textmonthlylimit != null ? textmonthlylimit.hashCode() : 0);
        result = 31 * result + (filemonthlylimit != null ? filemonthlylimit.hashCode() : 0);
        result = 31 * result + (textdailylimit != null ? textdailylimit.hashCode() : 0);
        result = 31 * result + (akamaiuserid != null ? akamaiuserid.hashCode() : 0);
        result = 31 * result + (akamaipassword != null ? akamaipassword.hashCode() : 0);
        result = 31 * result + (int) (concurrency ^ (concurrency >>> 32));
        return result;
    }
}
