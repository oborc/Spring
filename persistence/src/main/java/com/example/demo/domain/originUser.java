package com.example.demo.domain;


import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "test_unique")
public class originUser
{
    private String last_name;
    private String first_name;

//    @Basic
//    @Column(name = "last_name")
    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

//    @Basic
//    @Column(name = "first_name")
    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
}
