package Model;

import java.util.Date;

/**
 * A certificate that is assigned to a employee. Has an expiry date and a linked certificate
 */
public class EmployeeCertificate {

    private Date expiryDate;
    private Certificate certificate;

    /**
     * Constructs an EmployeeCertificate with an expiry date and a certificate
     *
     * @param c
     * @param expiryDate
     */
    protected EmployeeCertificate(Certificate c, Date expiryDate) {
        this.expiryDate = expiryDate;
        this.certificate = c;
    }

    protected EmployeeCertificate(Certificate c) {
        this.certificate = c;
    }

    /**
     * Gets the linked certificate of the EmployeeCertificate
     *
     * @return the certificate
     */
    public Certificate getCertificate() {
        return certificate;
    }

    /**
     * Gets the name of the certificate
     *
     * @return the name of the certificate
     */
    public String getCertificateName() {
        return this.certificate.NAME;
    }

    /**
     * Converts the expiryDate to a string and returns it
     * @return the expiry date as a string
     */
    public String getExpiryDateAsString(){
        return expiryDate.toString();
    }
}
