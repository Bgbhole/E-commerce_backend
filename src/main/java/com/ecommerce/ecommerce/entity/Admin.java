package com.ecommerce.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "admins")
public class Admin {


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long adminId;

private String firstName;
private String lastName;
private String companyIdNumber;
private String email;
private String mobile;

@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
private String password;

public Long getAdminId() {
    return adminId;
}

public void setAdminId(Long adminId) {
    this.adminId = adminId;
}

public String getFirstName() {
    return firstName;
}

public void setFirstName(String firstName) {
    this.firstName = firstName;
}

public String getLastName() {
    return lastName;
}

public void setLastName(String lastName) {
    this.lastName = lastName;
}

public String getCompanyIdNumber() {
    return companyIdNumber;
}

public void setCompanyIdNumber(String companyIdNumber) {
    this.companyIdNumber = companyIdNumber;
}

public String getEmail() {
    return email;
}

public void setEmail(String email) {
    this.email = email;
}

public String getMobile() {
    return mobile;
}

public void setMobile(String mobile) {
    this.mobile = mobile;
}

public String getPassword() {
    return password;
}

public void setPassword(String password) {
    this.password = password;
}


}
