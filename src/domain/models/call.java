package domain.models;

public class call {
    private boolean inCall;
    private contact infoContacts;

    public call() {
        this.inCall = false;
    }

    public boolean isInCall() {
        return infoContacts != null;
    }

    public contact getInfoContacts() {
        return infoContacts;
    }

    public void setInfoContacts(contact infoContacts) {
        this.infoContacts = infoContacts;
    }

    public void endCall(){
        this.infoContacts = null;
    }
}
