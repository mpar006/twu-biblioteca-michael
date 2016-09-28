package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michaelparker on 28/09/2016.
 */
public class User {
    private String login;
    private String password;
    List<LibraryItem> borrowedItems;
    private String name;
    private String email;
    private String phone;

    public User(String login, String password, String name, String email, String phone) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
        borrowedItems = new ArrayList<LibraryItem>();
    }

    public User(String login, String password) {
        this(login, password, "", "", "");
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object obj) {
        User other = (User) obj;
        if(other.getLogin().equals(login) && other.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    public String showDetails() {
        return name + "," + email + "," + phone;
    }

    public void borrow(LibraryItem item) {
        borrowedItems.add(item);
    }

    public String showBorrowedTitles() {
        String list = "";
        for(LibraryItem item : borrowedItems) {
            list += item.getTitle() + "\n";
        }
        return list.trim();
    }
}
