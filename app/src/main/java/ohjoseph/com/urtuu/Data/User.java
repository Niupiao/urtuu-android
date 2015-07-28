package ohjoseph.com.urtuu.Data;

import java.util.ArrayList;

/**
 * Created by Joseph on 7/22/15.
 */
public class User {

    private static User sUser;
    String mUsername;
    String mFirstName;
    String mLastName;
    ArrayList<Address> mAddresses;
    ArrayList<String> mPhoneNumbers;
    final String mEmail;
    final String mPassword;

    public static User create(String email, String password) {
        sUser = new User(email, password);
        return  sUser;
    }

    public static User get() {
        return sUser;
    }

    private User(String email, String password) {
        mUsername = "sampleperson93";
        mFirstName = "Sample";
        mLastName = "Person";
        mEmail = "foo@bar.com";
        mPassword = "password";
        mAddresses = new ArrayList<>();
        mPhoneNumbers = new ArrayList<>();
        mAddresses.add(new Address("North Adams", "12", "Porches", "Veazie St", "28"));
        mPhoneNumbers.add("123-456-7890");
    }

    public String getEmail() {
        return mEmail;
    }

    public String getPassword() {
        return mPassword;
    }

    public String getName() {
        return mFirstName + " " + mLastName;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public ArrayList<Address> getAddresses() {
        return mAddresses;
    }

    public void setAddresses(ArrayList<Address> addresses) {
        mAddresses = addresses;
    }

    public ArrayList<String> getPhoneNumbers() { return mPhoneNumbers; }

    public void setPhoneNumbers(ArrayList<String> phoneNumbers) {
        mPhoneNumbers = phoneNumbers;
    }
}
