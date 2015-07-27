package ohjoseph.com.urtuu.Data;

/**
 * Created by Benjamin on 7/27/2015.
 */
public class Review {
    public String reviewer;
    public String body;
    public int rating;
    public Review() {

    }

    public Review(String reviewer, String body, int rating) {
        this.reviewer = reviewer;
        this.body = body;
        this.rating = rating;
    }
}
