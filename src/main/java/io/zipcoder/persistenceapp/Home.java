package io.zipcoder.persistenceapp;

public class Home {

    Integer ID;
    String ADDRESS;
    String HOMENUMBER;

    public Home() {}

    public Home(Integer ID, String ADDRESS, String HOMENUMBER) {
        this.ID = ID;
        this.ADDRESS = ADDRESS;
        this.HOMENUMBER = HOMENUMBER;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getADDRESS() {
        return ADDRESS;
    }

    public void setADDRESS(String ADDRESS) {
        this.ADDRESS = ADDRESS;
    }

    public String getHOMENUMBER() {
        return HOMENUMBER;
    }

    public void setHOMENUMBER(String HOMENUMBER) {
        this.HOMENUMBER = HOMENUMBER;
    }
}
