package top.chen.train.business.req;


import java.util.Date;

public class StudentSaveReq {

    /**
    * id
    */
    private Long id;

    /**
    * 手机号
    */
    private String phone;

    /**
    * 姓名
    */
    private String username;

    /**
    * 年龄
    */
    private Integer age;

    /**
    * 电子邮箱
    */
    private String email;

    /**
    * 学校
    */
    private String school;
    private Date createTime;
    private Date updateTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getId() {
    return id;
    }

    public void setId(Long id) {
    this.id = id;
    }

    public String getPhone() {
    return phone;
    }

    public void setPhone(String phone) {
    this.phone = phone;
    }

    public String getUsername() {
    return username;
    }

    public void setUsername(String username) {
    this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
    return email;
    }

    public void setEmail(String email) {
    this.email = email;
    }

    public String getSchool() {
    return school;
    }

    public void setSchool(String school) {
    this.school = school;
    }

@Override
public String toString() {
StringBuilder sb = new StringBuilder();
sb.append(getClass().getSimpleName());
sb.append(" [");
sb.append("Hash = ").append(hashCode());
    sb.append(", id=").append(id);
    sb.append(", phone=").append(phone);
    sb.append(", username=").append(username);
    sb.append(", age=").append(age);
    sb.append(", email=").append(email);
    sb.append(", school=").append(school);
sb.append("]");
return sb.toString();
}
}