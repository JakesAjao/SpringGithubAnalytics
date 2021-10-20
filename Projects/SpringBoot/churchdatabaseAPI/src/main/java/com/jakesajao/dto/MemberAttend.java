package com.jakesajao.dto;

import java.time.LocalDate;
import java.util.List;

public class MemberAttend {
    private String firstName;
    private String lastName;
    private String gender;
    private String status;
    private LocalDate createdDate;
    private Long id;

    public String getMobilephone1() {
        return mobilephone1;
    }

    public void setMobilephone1(String mobilephone1) {
        this.mobilephone1 = mobilephone1;
    }

    private String mobilephone1;

    private boolean present;

    public MemberAttend(){
    }
    public MemberAttend(Long id,String firstName, String lastName, boolean present, String gender, LocalDate createdDate) {
        this.firstName = firstName;
        this.id = id;
        this.lastName = lastName;
        this.gender = gender;
        this.createdDate = createdDate;
        this.present = present;
        this.mobilephone1 = mobilephone1;
    }
    public MemberAttend(Long id,String firstName, String lastName, String status, String gender, LocalDate createdDate) {
        this.firstName = firstName;
        this.id = id;
        this.lastName = lastName;
        this.gender = gender;
        this.createdDate = createdDate;
        this.status = status;
        this.mobilephone1 = mobilephone1;
    }



    public MemberAttend(Long id,String firstName, String lastName) {
        this.firstName = firstName;
        this.id = id;
        this.lastName = lastName;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean getPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "MemberAttend{" +
                "id='" + id + '\'' +
                ",firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", createdDate=" + createdDate +
                ", status='" + status + '\'' +
                ", present=" + present +
                '}';
    }


}
