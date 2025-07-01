package domain.models;

public class voiceMail {
    private final long number;
    private final String message;

    public voiceMail(long number, String message) {
        this.number = number;
        this.message = message;
    }

    public long getNumber() {
        return number;
    }

    public String getMessage() {
        return String.format("Remetente: %s \nMensagem: %s", number, message);
    }
}
