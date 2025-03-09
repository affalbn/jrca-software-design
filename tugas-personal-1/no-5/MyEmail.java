interface SignatureProvider {
    String getSignature();
}

class SimpleSignatureProvider implements SignatureProvider {
    private final String signature;
    
    public SimpleSignatureProvider(String signature) {
        this.signature = signature;
    }

    @Override
    public String getSignature() {
        return signature;
    }
}

class Email {
    private final User recipient;
    private final String body;
    private final SignatureProvider signatureProvider;

    public Email(User recipient, String body, SignatureProvider signatureProvider) {
        this.recipient = recipient;
        this.body = body;
        this.signatureProvider = signatureProvider;
    }

    public String createFormattedEmail() {
        return getFormattedRecipientName() + "\n" +
               body + "\n" +
               signatureProvider.getSignature();
    }
    
    public String getFormattedRecipientName() {
        return recipient.getFullName();
    }
}

class User {
    private final String first;
    private final String last;

    public User(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public String getFirstName() {
        return first;
    }

    public String getLastName() {
        return last;
    }

    public String getFullName() {
        return first + " " + last;
    }
}

public class MyEmail {
    public static void main(String[] args) {
        User user = new User("Afif", "Albana");
        SignatureProvider signatureProvider = new SimpleSignatureProvider("Regards,");
        Email email = new Email(user, "This is the email body.", signatureProvider);
        String formattedEmail = email.createFormattedEmail();
        System.out.println(formattedEmail);
    }
}
