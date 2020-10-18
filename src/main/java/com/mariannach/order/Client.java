package com.mariannach.order;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
class Client {

    private @Id @GeneratedValue Long id;
    private String firstName;
    private String lastName;
    private String phoneNo;

    Client() {}

    Client(String firstName, String lastName, String phoneNo) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNo = phoneNo;
    }

    public String getName() {
        return this.firstName + " " + this.lastName;
    }

    public void setName(String name) {
        String[] parts = name.split(" ");
        this.firstName = parts[0];
        this.lastName = parts[1];
    }

    public Long getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getPhoneNo() {
        return this.phoneNo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Client))
            return false;
        Client client = (Client) o;
        return Objects.equals(this.id, client.id) && Objects.equals(this.firstName, client.firstName)
                && Objects.equals(this.lastName, client.lastName) && Objects.equals(this.phoneNo, client.phoneNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.firstName, this.lastName, this.phoneNo);
    }

    @Override
    public String toString() {
        return "Client{" + "id=" + this.id + ", firstName='" + this.firstName + '\'' + ", lastName='" + this.lastName
                + '\'' + ", phoneNumber='" + this.phoneNo + '\'' + '}';
    }
}