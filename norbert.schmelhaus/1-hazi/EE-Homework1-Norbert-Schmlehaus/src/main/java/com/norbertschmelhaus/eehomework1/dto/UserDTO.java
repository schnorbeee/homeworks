package com.norbertschmelhaus.eehomework1.dto;

import com.norbertschmelhaus.eehomework1.enums.Sex;
import com.norbertschmelhaus.eehomework1.constraint.*;
import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

/**
 *
 * @author norbeee
 */
@FirstFillLastFill
@DateOfBirth
public class UserDTO implements Serializable {

    @NotNull
    @Size(min = 6)
    private String userName;
    @NotNull
    @Password
    private String password;
    private String firstName;
    private String lastName;
    @Address
    private String address;
    @Phone
    private String phone;
    @NotNull
    @Email
    private String email;
    private Sex sex;
    @NotNull
    @Past
    private Date registrationDate;
    private Date dateOfBirth;
    private boolean admin;
    
    public UserDTO() {
        //Empty constructor
    }

    public UserDTO(String userName, String password, String email, Date registrationDate) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.registrationDate = registrationDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (this.getClass() != obj.getClass())
            return false;

        UserDTO ex = (UserDTO) obj;
        int size = 0;
        boolean isTrue = false;
        if ((ex.address == null && this.address == null) || this.address.equals(ex.address)) {
            size++;
        }
        if (this.admin == ex.admin) {
            size++;
        }
        if ((ex.dateOfBirth == null && this.dateOfBirth == null) || this.dateOfBirth.equals(ex.dateOfBirth)) {
            size++;
        }
        if (this.email.equals(ex.email)) {
            size++;
        }
        if ((ex.firstName == null && this.firstName == null) || this.firstName.equals(ex.firstName)) {
            size++;
        }
        if ((ex.lastName == null && this.lastName == null) || this.lastName.equals(ex.lastName)) {
            size++;
        }
        if (this.password.equals(ex.password)) {
            size++;
        }
        if ((ex.phone == null && this.phone == null) || this.phone.equals(ex.phone)) {
            size++;
        }
        if ((ex.sex == null && this.sex == null) || this.sex.equals(ex.sex)) {
            size++;
        }
        if (this.userName.equals(ex.userName)) {
            size++;
        }
        if (size == 10) {
            isTrue = true;
        }
        return isTrue;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((userName == null) ? 0 : userName.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((phone == null) ? 0 : phone.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((sex == null) ? 0 : sex.hashCode());
        result = prime * result + ((registrationDate == null) ? 0 : registrationDate.hashCode());
        result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
        return result;
    }
}
