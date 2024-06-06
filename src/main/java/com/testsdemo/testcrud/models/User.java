package com.testsdemo.testcrud.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;

@Entity
@Table(name="user")
public class User {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;

  private String profile;

  private String cover;
  
  private String username;
  
  private String name;

  private String lastname;
  
  private String nickname;
  
  private String position;
  
  private String nationality;
  
  private String tel;
  
  @Column(name="starting_date")
  private LocalDateTime startingDate;

  @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  private LocalDateTime createdAt;
  
  
  @Column(name="address")
  private String addresss;
  
  @Column(name="sub_district")
  private Integer subDistrcit;

  private Integer district;
  
  private Integer province;
  
  @Column(name="postal_code")
  private String postalCode;
  
  private String facebook;
  
  @Column(name="line_id")
  private String lineId;
  
  private String instagram;
  
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  @JsonManagedReference
  private List<EducationalInfomation> educationalInfomations;
  
  @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
  @JsonManagedReference
  private List<ExpInfo> expInfos;
  
  @OneToMany(mappedBy="user",cascade = CascadeType.ALL)
  @JsonManagedReference
  private List<Skill> skills;
  
  @OneToMany(mappedBy="user",cascade = CascadeType.ALL)
  @JsonManagedReference
  private List<InterestInfo> interestInfo;

  @OneToMany(mappedBy="user",cascade = CascadeType.ALL)
  @JsonManagedReference
  private List<GuildInfo> guildInfo;
  
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public LocalDateTime getStartingDate() {
		return startingDate;
	}

	public void setStartingDate(LocalDateTime startingDate) {
		this.startingDate = startingDate;
	}

	public String getAddresss() {
		return addresss;
	}

	public void setAddresss(String addresss) {
		this.addresss = addresss;
	}

	public Integer getSubDistrcit() {
		return subDistrcit;
	}

	public void setSubDistrcit(Integer subDistrcit) {
		this.subDistrcit = subDistrcit;
	}

	public Integer getDistrict() {
		return district;
	}

	public void setDistrict(Integer district) {
		this.district = district;
	}

	public Integer getProvince() {
		return province;
	}

	public void setProvince(Integer province) {
		this.province = province;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getLineId() {
		return lineId;
	}

	public void setLineId(String lineId) {
		this.lineId = lineId;
	}

	public String getInstagram() {
		return instagram;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}
	
	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	@PrePersist
    public void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

	public List<EducationalInfomation> getEducationalInfomations() {
		return educationalInfomations;
	}

	public void setEducationalInfomations(List<EducationalInfomation> educationalInfomations) {
		this.educationalInfomations = educationalInfomations;
	}
	
  
}