package io.zipcoder.persistenceapp.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "PERSON")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long ID;
    private String FIRST_NAME;
    private String LAST_NAME;
    private String MOBILE;
    private Date BIRTHDAY;
    @ManyToOne
//    @JoinColumn(name = "HOME_ID")
    private Home home;

    public Person() {}

    public Person(Long ID, String FIRST_NAME, String LAST_NAME, String MOBILE, Date BIRTHDAY, Home home) {
        this.ID = ID;
        this.FIRST_NAME = FIRST_NAME;
        this.LAST_NAME = LAST_NAME;
        this.MOBILE = MOBILE;
        this.BIRTHDAY = BIRTHDAY;
        this.home = home;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getFIRST_NAME() {
        return FIRST_NAME;
    }

    public void setFIRST_NAME(String FIRST_NAME) {
        this.FIRST_NAME = FIRST_NAME;
    }

    public String getLAST_NAME() {
        return LAST_NAME;
    }

    public void setLAST_NAME(String LAST_NAME) {
        this.LAST_NAME = LAST_NAME;
    }

    public String getMOBILE() { return MOBILE; }

    public void setMOBILE(String MOBILE) { this.MOBILE = MOBILE; }

    public Home getHome() { return home; }

    public void setHome(Home home) { this.home = home; }

    public Date getBIRTHDAY() { return BIRTHDAY; }

    public void setBIRTHDAY(Date BIRTHDAY) {
        this.BIRTHDAY = BIRTHDAY;
    }

}
