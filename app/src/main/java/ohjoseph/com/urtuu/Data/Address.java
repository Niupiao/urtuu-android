package ohjoseph.com.urtuu.Data;

/**
 * Created by Benjamin on 7/24/2015.
 */
public class Address {
    public String city;
    public String district;
    public String committee;
    public String street;
    public String door;
    public Address(){
    }

    public Address(String city, String district, String committee, String street, String door) {
        this.city = city;
        this.district = district;
        this.committee = committee;
        this.street = street;
        this.door = door;
    }
    @Override
    public String toString() {
        return city + ", " + district + ", " + committee + ", " + street + ", " + door;
    }
}
