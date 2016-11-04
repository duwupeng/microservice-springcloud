package pn.eric.microservice.model;


/**
 * Created by eric on 16/7/5.
 */
public class Customer {
    private Long id;
    private String name;
    private int age;
    public Customer(int age,String name) {
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
