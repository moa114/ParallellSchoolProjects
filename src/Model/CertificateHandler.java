package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * A class that handles the certificates. Has a list with certificates and a hash map that link the employees with certificates
 */
public class CertificateHandler {
    private static CertificateHandler single_instance = null;
    private List<Certificate> allCertificates;
    private HashMap<Certificate, List<Employee>> employeeLinkCertificate;


    private CertificateHandler() {
        this.allCertificates = new ArrayList<>();
        this.employeeLinkCertificate = new HashMap<>();
    }

    /**
     * Gets the instance of the CertificateHandler which handles all certificates
     *
     * @return the single instance of the CertificateHandler
     */
    public static CertificateHandler getInstance() {
        if (single_instance == null)
            single_instance = new CertificateHandler();
        return single_instance;
    }

    /**
     * Links the given employee to a certain certificate in the hash map
     *
     * @param c The certificate that the employee will be linked to
     * @param e The employee that will be linked to a certificate
     */
    public void linkEmployeeToCertificate(Certificate c, Employee e) {
        employeeLinkCertificate.get(c).add(e);
    }

    /**
     * Unlinks the given employee with a certain certificate in the hash map
     *
     * @param c The certificate that the employee is linked to
     * @param e The employee that will be unliked to a certificate
     */
    public void unlinkEmployeeToCertificate(Certificate c, Employee e) {
        employeeLinkCertificate.get(c).remove(e);
    }

    /**
     * Gets a list with employees that are linked to a certificate
     *
     * @param c A certificate
     * @return A list with employees that are linked to the certificate
     */
    public Iterator<Employee> getEmployeeWithCertificate(Certificate c) {
        return employeeLinkCertificate.get(c).iterator();
    }

    /**
     * Takes in a certificate to check which employees has it
     *
     * @param certificate The certificate to check which employee has
     * @return How many employees has the certificate
     */
    public int getEmployeeWithCertificateSize(Certificate certificate) {
        return employeeLinkCertificate.get(certificate).size();
    }

    /**
     * Checks if a specified employee has a specified certificate
     *
     * @param certificate The certificate to check if the employee has
     * @param employee    Employee to check if has certificate
     * @return true if employee has the certificate or false if otherwise
     */
    public boolean checkEmployeeWithCertificate(Certificate certificate, Employee employee) {
        return employeeLinkCertificate.get(certificate).contains(employee);
    }

    /**
     * Deletes all the certificates
     */
    public void deleteAllCertificates() {
        this.allCertificates.clear();
    }

    /**
     * Gets all the certificates using an Iterator
     *
     * @return the iterator of all the certificates
     */
    public Iterator<Certificate> getAllCertificates() {
        return allCertificates.iterator();
    }

    /**
     * Gets the certificate by its name
     *
     * @param name Name of the certificate
     * @return the certificate that has the given name, or returns null if the name is invalid
     */
    public Certificate getCertificate(String name) {
        for (Certificate c : allCertificates) {
            if (c.NAME.equals(name))
                return c;
        }
        System.out.println("invalid name");
        return null;
    }

    /**
     * Gets the certificate by its ID
     *
     * @param ID Id of the certificate
     * @return the certificate that has the given ID, or returns null if the ID is invalid
     */
    public Certificate getCertificate(long ID) {
        for (Certificate c : allCertificates) {
            if (c.ID == ID)
                return c;
        }
        System.out.println("invalid ID");
        return null;
    }

    /**
     * Creates a new Certificate and adds it to the list of all certificates and to the hash map
     *
     * @param nameOfCertificate The name of the new certificate
     */
    public void createNewCertificate(String nameOfCertificate) {
        Certificate tmp = new Certificate(nameOfCertificate);
        this.allCertificates.add(tmp);
        employeeLinkCertificate.put(tmp, new ArrayList<>());
    }

    /**
     * Deletes a certificate from the hash map (removes all the links to it to the employees) and removes it from the list of all certificates
     *
     * @param certificate The certificate that will be removed
     */
    public void deleteCertificate(Certificate certificate) {
        for (Employee e : employeeLinkCertificate.get(certificate)) {
            e.unAssignCertificate(e.getEmployeeCertificate(certificate));
        }
        this.allCertificates.remove(certificate);
    }

    /**
     * Deletes the certificate that has the given ID from the list of all certificates
     *
     * @param ID The ID of the certificate that will be removed
     */
    public void deleteCertificate(int ID) {
        this.allCertificates.remove(ID);
    }

    /**
     * Deletes the certificate that has the given name from the list of all certificates
     *
     * @param name The name of the certificate that will be removed
     */
    public void deleteCertificate(String name) {
        for (Certificate c : allCertificates) {
            if (c.NAME.equalsIgnoreCase(name)) {
                deleteCertificate(c);
                break;
            }
        }
    }

    /**
     * Assigns an EmployeeCertificate to to list of employees
     *
     * @param certificate the EmployeeCertificate that will be assigned
     * @param employees   A list of employees
     */
    public void assignCertificateToEmployees(EmployeeCertificate certificate, List<Employee> employees) {
        for (Employee e : employees) {
            e.assignCertificate(certificate);
        }
    }
}
