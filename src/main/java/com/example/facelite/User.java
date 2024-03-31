package com.example.facelite;

import javafx.scene.image.Image;

import java.util.ArrayList;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

// Represents a user profile in the FaceLife application
class User implements Comparable<User> {

    // Fields
    final private String name;
    private String status;
    private Image profilePic; // Holds an Image object representing the profile picture
    private String updates;
    private boolean darkMode;
    private String massage;
    private final ArrayList<User> friends = new ArrayList<>(); // List of friends
    private String passwordHash;
    private byte[] salt;


    // Constructors

    // Default constructor with a default profile picture and status
    User(String name) {
        this(name, "No current status", com.example.facelite.Main.getDefaultPic(), false);
    }

    // Parameterized constructor with custom name, status, profile picture, and dark mode setting
    User(String name, String status, Image profilePic, boolean darkMode) {
        this.name = name;
        this.status = status;
        this.profilePic = profilePic;
        this.darkMode = darkMode;
        this.massage = "";
    }

    // Getters

    // Returns the name of the person
    public String getName() {
        return name;
    }

    // Returns the current status of the person
    public String getStatus() {
        return status;
    }

    // Returns the profile picture of the person
    public Image getProfilePic() {
        return profilePic;
    }

    // Returns the updates of the person
    public String getUpdates() {
        return updates;
    }

    // Setters

    // Sets the status of the person
    public void setStatus(String status) {
        this.status = status;
    }

    // Sets the profile picture of the person
    public void setProfilePic(Image profilePic) {this.profilePic = profilePic;}

    // Sets the updates of the person
    public void setUpdates(String updates) {
        this.updates = updates;
    }

    // Comparable interface implementation for sorting
    @Override
    public int compareTo(User that) {
        return this.name.compareTo(that.name);
    }

    // toString method to provide a string representation of the person
    @Override
    public String toString() {
        return this.name;
    }

    // Returns the list of friends
    public ArrayList<User> getFriends() {
        return this.friends;
    }

    // Returns a formatted string representing the list of friends
    public String getFriendsList() {
        String friendsList = "";
        for (User friend : this.friends) {
            friendsList += friend.getName() + "\n";
        }
        return friendsList;
    }

    // Returns whether the dark mode is enabled for the person
    public boolean isDarkMode() {
        return darkMode;
    }

    // Sets the dark mode for the person
    public void setDarkMode(boolean darkMode) {
        this.darkMode = darkMode;
    }

    // Sets the massage of the person
    public void setMassage(String massage) {
        this.massage = massage;
    }

    // Updates the massage of the person with the sender's information
    public void updateMassage(String massage, User sender) {
        this.massage = this.massage + sender.getName() + ": " + massage + "\n";

    }
    public void updateMassage(String massage, String sender) {
        this.massage = this.massage + sender + ": " + massage + "\n";

    }
    // Returns the massage history of the person
    public String getMassage() {
        return massage;
    }
    // Method to set (encrypt) the password
    public void setPassword(String password) {
        this.salt = generateSalt();
        this.passwordHash = hashPassword(password, this.salt);
    }

    // Method to verify the password
    public boolean verifyPassword(String password) {
        String attemptedHash = hashPassword(password, this.salt);
        return this.passwordHash.equals(attemptedHash);
    }

    // Generates a random salt
    private byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    // Hashes the password with SHA-256 and salt
    private String hashPassword(String password, byte[] salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] hashedPassword = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
