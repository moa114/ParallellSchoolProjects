package Model;

import java.util.ArrayList;
import java.util.List;

public class CertificateHandler {
    private static CertificateHandler single_instance = null;
    private List<String> availableCertificates;
    private List<AssociationDepartment> departmentAssociations;
    private List<AssociationEmployee> employeeAssociations;


    private CertificateHandler(){
        this.availableCertificates = new ArrayList<>();
        this.departmentAssociations = new ArrayList<>();
        this.employeeAssociations = new ArrayList<>();
    }

    public List<String> getCertificates(Employee e){
        List<String> tmp = new ArrayList<>();
        for (AssociationEmployee a : employeeAssociations){
            if (a.employee == e)
                tmp.add(a.certificate.name);
        }
        return tmp;
    }

    public static CertificateHandler getInstance(){
        if (single_instance == null)
            single_instance = new CertificateHandler();
        return single_instance;
    }

    public void createNewCertificate(String nameOfCertificate){
        if (availableCertificates.contains(nameOfCertificate))
            return;
        availableCertificates.add(nameOfCertificate);
    }

    public void assignCertificateToEmployee(String certificate, Employee employee, java.util.Date expires){
        if (availableCertificates.contains(certificate))
            employeeAssociations.add(new AssociationEmployee(new Certificate(certificate, expires), employee));
    }

    public void assignCertificateToEmployee(String certificate, Employee employee){
        if (availableCertificates.contains(certificate))
            employeeAssociations.add(new AssociationEmployee(new Certificate(certificate), employee));
    }

    public void assignCertificateToEmployee(int certificate, Employee employee){
        if (availableCertificates.size() > certificate)
            employeeAssociations.add(new AssociationEmployee(new Certificate(availableCertificates.get(certificate)), employee));
    }

    public void assignCertificateToEmployee(int certificate, Employee employee, java.util.Date expires){
        if (availableCertificates.size() > certificate)
            employeeAssociations.add(new AssociationEmployee(new Certificate(availableCertificates.get(certificate), expires), employee));
    }

    public void assignCertificateToEmployees(String certificate, List<Employee> employees, java.util.Date expires){
        if (availableCertificates.contains(certificate)) {
            Certificate c = new Certificate(certificate, expires);
            for (Employee e : employees) {
                employeeAssociations.add(new AssociationEmployee(c, e));
            }
        }
    }

    public void assignCertificateToEmployees(int certificate, List<Employee> employees, java.util.Date expires){
        if (availableCertificates.size()>certificate) {
            Certificate c = new Certificate(availableCertificates.get(certificate), expires);
            for (Employee e : employees) {
                employeeAssociations.add(new AssociationEmployee(c, e));
            }
        }
    }
    public void assignCertificateToEmployees(String certificate, List<Employee> employees){
        if (availableCertificates.contains(certificate)) {
            Certificate c = new Certificate(certificate);
            for (Employee e : employees) {
                employeeAssociations.add(new AssociationEmployee(c, e));
            }
        }
    }
    public void assignCertificateToEmployees(int certificate, List<Employee> employees){
        if (availableCertificates.size()>certificate) {
            Certificate c = new Certificate(availableCertificates.get(certificate));
            for (Employee e : employees) {
                employeeAssociations.add(new AssociationEmployee(c, e));
            }
        }
    }


    private class Certificate{
        final String name;
        final java.util.Date expires;

        public Certificate(String name, java.util.Date expires) {
            this.name = name;
            this.expires = expires;
        }

        public Certificate(String name){
            this.name = name;
            this.expires = new java.util.Date();
            this.expires.setTime(Long.MAX_VALUE);
        }
    }

    private class AssociationEmployee{
        Certificate certificate;
        Employee employee;

        public AssociationEmployee(Certificate certificate, Employee employee) {
            this.certificate = certificate;
            this.employee = employee;
        }
    }

    private class AssociationDepartment{
        Certificate certificate;
        Department department;

        public AssociationDepartment(Certificate certificate, Department department) {
            this.certificate = certificate;
            this.department = department;
        }
    }
}
