package Model;

import java.util.Date;

public class EmployeeCertificate extends Certificate {

    private Date expiryDate;
    private Certificate certificate;

    public EmployeeCertificate(Certificate c, Date expiryDate) {
        super(c.name);
        this.expiryDate = expiryDate;

    }

    protected Certificate getCertificate() {
        return certificate;
    }
}
