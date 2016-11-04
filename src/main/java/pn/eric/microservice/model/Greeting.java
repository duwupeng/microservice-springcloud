package pn.eric.microservice.model;

import java.math.BigInteger;

/**
 * Created by eric on 16/7/5.
 */
public class Greeting {
        private BigInteger id;
        private String text;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
