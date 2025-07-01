package domain.models;

import java.time.LocalDateTime;

public class historyCall extends contact{
    private LocalDateTime date;

    public historyCall(contact contact)  {
        super(contact.getName(), contact.getNumber());
        this.date = LocalDateTime.now();
    }

    public LocalDateTime getDate() {
        return date;
    }
}
