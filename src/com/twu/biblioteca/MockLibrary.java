package com.twu.biblioteca;

/**
 * Created by root on 26/09/16.
 */
public class MockLibrary implements Library {
    boolean checkedout = false;

    @Override
    public String listBooks() {
        if(checkedout) {
            return "Perdido Street Station,China Mieville,2000";
        }
        return "The Dispossessed,Ursula K Le Guin,1974\n" +
                "Perdido Street Station,China Mieville,2000";
    }

    @Override
    public boolean checkout(String title) {
        if(title.equals("The Dispossessed")) {
            checkedout = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean returnBook(String title) {
        if(title.equals("The Dispossessed")) {
            checkedout = false;
            return true;
        }
        return false;
    }
}
