package top.itjee.www.zchain.webcontroller.vo;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

public class User {

    private Long id;
    @NotBlank
    private String name;

    @NotEmpty
    private List<String> list;

    @Max(value = 100)
    @Min(20)
    private String sex;

    private Date date;
    @Email
    private String email;
    @Pattern(regexp = "")
    private String pattern;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
}
