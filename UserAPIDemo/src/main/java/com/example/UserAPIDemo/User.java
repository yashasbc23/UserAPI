package com.example.UserAPIDemo;

import lombok.Builder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.UUID;

@Entity
//@Table
@Builder
public class User {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column
    private String id;

    @Column
    @NotBlank(message="Required First Name")
    //@Max(20)
    @Size(max = 20)
    @Pattern(regexp="^[A-Za-z]*$",message = "Invalid First Name")
    private  String firstName;
    @Column
    @NotBlank(message="Required Last Name")
    //@Max(20)
    @Size(max = 20)
    @Pattern(regexp="^[A-Za-z]*$",message = "Invalid Last Name")
    private  String lastName;
    @Column
    //@Max(1)
    @Size(max = 1)
    @Pattern(regexp="^[A-Za-z]*$",message = "Invalid middle Initial")
    private  String middleInitial;
    @Column
    @NotBlank(message="Required Email")
   // @Max(50)
    @Size(max = 50)
    private  String email;
    @Column
    @NotNull(message="Required Phone number")
    @Size(max = 10)
    @Digits(message="It should only have 10 digits",fraction=0,integer=10)
    private  String phoneNumber;
    @Column
    @NotBlank(message="Required Billing Address")
    private  String billingAddress;
    @Column
    private  String shippingAddress;

    public User(String id,String firstName, String lastName, String middleInitial, String email, String phoneNumber, String billingAddress, String shippingAddress) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleInitial = middleInitial;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
    }

    public User() {

    }

    public String getId() {
        return id;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleInitial() {
        return middleInitial;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
}
