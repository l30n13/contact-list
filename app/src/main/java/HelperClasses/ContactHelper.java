package HelperClasses;

import java.util.ArrayList;

/**
 * Created by ruhul_000 on 6/22/2015.
 */
public class ContactHelper {


    private int id;
    private String name;
    private String email;
    private String image;
    private String company;
    private ArrayList<String> phone;
    private ArrayList<String> phoneTypes;
    private ArrayList<String> emails;
    private ArrayList<String> emailTypes;

    private String address;
    private String street;
    private String poBox;
    private String events;
    private String city;
    private String state;
    private String zipCode;
    private String note;
    private String date;
    private String groups;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getEmails() {
        return emails;
    }

    public void setEmails(ArrayList<String> emails) {
        this.emails = emails;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public ArrayList<String> getPhone() {
        return phone;
    }

    public void setPhone(ArrayList<String> phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPoBox() {
        return poBox;
    }

    public void setPoBox(String poBox) {
        this.poBox = poBox;
    }

    public String getEvents() {
        return events;
    }

    public void setEvents(String events) {
        this.events = events;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public ArrayList<String> getPhoneTypes() {
        return phoneTypes;
    }

    public void setPhoneTypes(ArrayList<String> phoneTypes) {
        this.phoneTypes = phoneTypes;
    }

    public ArrayList<String> getEmailTypes() {
        return emailTypes;
    }

    public void setEmailTypes(ArrayList<String> emailTypes) {
        this.emailTypes = emailTypes;
    }
}
