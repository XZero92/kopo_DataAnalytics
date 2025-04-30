package com.example.jsp_19_memberex_ysh.dto;

public class MemberDTO {
    private String id;
    private String paswd;
    private String username;
    private String email;
    private String mobile;
    private String gender;

    // 기본 생성자
    public MemberDTO() {}

    // 모든 필드를 포함한 생성자
    public MemberDTO(String id, String paswd, String username, String email, String mobile, String gender) {
        this.id = id;
        this.paswd = paswd;
        this.username = username;
        this.email = email;
        this.mobile = mobile;
        this.gender = gender;
    }

    // Getter와 Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPaswd() {
        return paswd;
    }

    public void setPaswd(String paswd) {
        this.paswd = paswd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}