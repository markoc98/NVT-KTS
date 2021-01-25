package com.nwt_kts_project.CulturalOfferings.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.*;

@Entity
@Table(name = "user_table")
public class User implements UserDetails {



    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "isEnabled")
    private boolean isEnabled;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String username;


    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Set<Review> reviews = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "user_cultural_offering",
            joinColumns = @JoinColumn(name = "user_id"  , referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "cultural_offering_id" , referencedColumnName = "cultural_offering_id"))
    private Set<CulturalOffering> subscribedTo;

    @Column(name = "last_password_reset_date")
    private Timestamp lastPasswordResetDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
    private List<Authority> authorities;


    public User() {
    }

    public User(String email, String password, String username,
                Set<Review> reviews, Set<CulturalOffering> subscribedTo) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.reviews = reviews;
        this.subscribedTo = subscribedTo;
    }

    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }
    public User(String email, String username, String password, boolean isEnabled) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.isEnabled = isEnabled;
    }
    public User(String email, String username, String password, boolean isEnabled,Set<CulturalOffering> subscribedTo) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.isEnabled = isEnabled;
        this.subscribedTo = subscribedTo;
    }
    public User(String email,String username) {
    	this.email = email;
    	this.username = username;
    }

    public User(boolean isEnabled, Long id, String email, String password, String username, Set<Review> reviews, Set<CulturalOffering> subscribedTo, Timestamp lastPasswordResetDate, List<Authority> authorities) {
        this.isEnabled = isEnabled;
        this.email = email;
        this.password = password;
        this.username = username;
        this.reviews = reviews;
        this.subscribedTo = subscribedTo;
    }

    public User(Long userId) {
        this.id = userId;
    }

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        Timestamp now = new Timestamp(new Date().getTime());
        this.setLastPasswordResetDate(now);
        this.password = password;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }


    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public Set<CulturalOffering> getSubscribedTo() {
        return subscribedTo;
    }

    public void setSubscribedTo(Set<CulturalOffering> subscribedTo) {
        this.subscribedTo = subscribedTo;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", reviews=" + reviews +
                ", subscribedTo=" + subscribedTo +
                "authority " + authorities.toString()+
                '}';
    }

    public Timestamp getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Timestamp lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
}

