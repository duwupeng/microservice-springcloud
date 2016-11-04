package pn.eric.microservice.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by eric on 16/7/5.
 */
@Entity
@Table(name="persons")
public class Person {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private boolean sex;
    private Date birthday;
    private int age;

    public Person(String name, boolean sex, Date birthday, int age) {
        this.name = name;
        this.sex = sex;
        this.birthday = birthday;
        this.age = age;
    }
    public Person(){

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

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
