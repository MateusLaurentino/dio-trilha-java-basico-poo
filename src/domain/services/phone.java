package domain.services;

import domain.models.contact;

import java.util.Optional;

public interface phone {
    void call(Optional<contact> contact);
    void endCall();
    void voiceMail();
    void history();
    void contacts();
    void addContact();
    void deleteContact(contact contact);
    contact editContact(contact contact);
    void run();
}
