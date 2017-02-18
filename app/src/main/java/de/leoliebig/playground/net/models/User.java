package de.leoliebig.playground.net.models;

import java.net.URL;

import de.leoliebig.playground.patterns.mvp.userprofile.UserProfileMvp;

/**
 * Data bag for user data
 * TODO
 * - add JSON annotations
 */
public class User implements UserProfileMvp.Model {

    public static final int NO_ID = -1;

    private long id = NO_ID;
    private String firstName;
    private String lastName;
    private String biography;
    private URL avatarUrl;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public URL getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(URL avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Override
    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}
