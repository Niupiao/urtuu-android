package ohjoseph.com.urtuu.Data;

/**
 * Created by Joseph on 7/22/15.
 */
public class User {

    private static User sUser;
    String mUsername;
    String mFirstName;
    String mLastName;
    final String mEmail;
    final String mPassword;

    public static User get() {
        if (sUser == null) {
            sUser = new User();
        }

        return sUser;
    }

    private User() {
        mUsername = "sampleperson93";
        mFirstName = "Sample";
        mLastName = "Person";
        mEmail = "asdf@gmail.com";
        mPassword = "asdfasdf";
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
}
