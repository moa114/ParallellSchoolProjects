package Model;

import java.util.Date;

public class EmployeeCertificate {

    private Date expiryDate;
    private Certificate certificate;

    public EmployeeCertificate(Certificate c, Date expiryDate) {
        this.expiryDate = expiryDate;
        this.certificate = c;
    }

    protected Certificate getCertificate() {
        return certificate;
    }
    public String getCertificateName() {
        return this.certificate.name;
    }
}
