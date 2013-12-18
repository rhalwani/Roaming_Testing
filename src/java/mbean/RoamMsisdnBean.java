/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import ejb.GPRSTestingMSISDNFacade;
import entity.GPRSTestingMSISDN;
import java.util.List;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.PostActivate;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;

/**
 *
 * @author Riad
 */
@ManagedBean
@ViewScoped
public class RoamMsisdnBean implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private List<RoamTestMsisdn> msisdnList, filteredMsisdnList;
    private String testMsisdn, testOperator, testTapCode, testCountry, testDesc;
   
    @EJB
    private GPRSTestingMSISDNFacade RoamTestNumEJB;

    /**
     * Creates a new instance of RoamOpBean
     */
    public RoamMsisdnBean() {
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        
        System.out.println("Old Value = "+oldValue+"\nNew Value = "+newValue);
        System.out.println("Value Columnn= "+event.getColumn().getHeaderText()+"\nValue Component= "+event.getColumn().getColumnKey());

        if ((newValue != null) && (!newValue.equals(oldValue))) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    @PostConstruct
    @PostActivate
    private void populateFullList() {

        List<GPRSTestingMSISDN> gprsTestNumList = RoamTestNumEJB.getRoamTestNumbers();
        msisdnList = new ArrayList(gprsTestNumList.size());
        //System.out.println("Roam List:\n"+RoamTestNumEJB.getRoamTestNumbers());
        for (GPRSTestingMSISDN testNum : gprsTestNumList) {
            msisdnList.add(new RoamTestMsisdn(testNum.getGPRSTestingMSISDNPK().getMsisdn(), testNum.getCountry(), testNum.getOperator(), testNum.getGPRSTestingMSISDNPK().getTAPCode(), testNum.getDescription()));
        }
    }

    public List<RoamTestMsisdn> getMsisdnList() {
        return msisdnList;
    }

    public void setMsisdnList(ArrayList<RoamTestMsisdn> msisdnList) {
        this.msisdnList = msisdnList;
    }

    public List<RoamTestMsisdn> getFilteredMsisdnList() {
        return filteredMsisdnList;
    }

    public void setFilteredMsisdnList(List<RoamTestMsisdn> filteredMsisdnList) {
        this.filteredMsisdnList = filteredMsisdnList;
    }

    public String getTestMsisdn() {
        return testMsisdn;
    }

    public void setTestMsisdn(String testMsisdn) {
        this.testMsisdn = testMsisdn;
    }

    public String getTestOperator() {
        return testOperator;
    }

    public void setTestOperator(String testOperator) {
        this.testOperator = testOperator;
    }

    public String getTestTapCode() {
        return testTapCode;
    }

    public void setTestTapCode(String testTapCode) {
        this.testTapCode = testTapCode;
    }

    public String getTestCountry() {
        return testCountry;
    }

    public void setTestCountry(String testCountry) {
        this.testCountry = testCountry;
    }

    public String getTestDesc() {
        return testDesc;
    }

    public void setTestDesc(String testDesc) {
        this.testDesc = testDesc;
    }

    public void deleteFromList(RoamTestMsisdn testMs) {
        RoamTestNumEJB.deleteTestNumber(testMs.getTapCode(), testMs.getMsisdn());
        populateFullList();
    }
    
    public void deleteFromList1(RoamTestMsisdn testMs) {
        msisdnList.remove(testMs);
    }
    
    public void updateRoamMsisdn(RoamTestMsisdn testMs) {
        
    }
    
    public void addRoamMsisdn() {
        //RequestContext context = RequestContext.getCurrentInstance();
        //context.reset("form:inputpanel");
        RoamTestNumEJB.addTestNumber(testTapCode, testMsisdn, testOperator, testDesc);
        populateFullList();
    }
    
    public void addRoamMsisdn1() {
        msisdnList.add(new RoamTestMsisdn(testMsisdn, testCountry, testOperator, testTapCode, testDesc));
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,testMsisdn+" added!", "")); 
        //RequestContext context = RequestContext.getCurrentInstance();
        //context.reset("form:inputpanel");
        //RoamTestNumEJB.addTestNumber(testTapCode, testMsisdn, testOperator, testDesc);
        //populateFullList();
    }
    
public static class RoamTestMsisdn {

    private String msisdn;
    private String country;
    private String operator;
    private String tapCode;
    private String description;

    public RoamTestMsisdn(String msisdn, String country, String operator, String tapCode, String description) {
        this.msisdn = msisdn;
        this.country = country;
        this.operator = operator;
        this.tapCode = tapCode;
        this.description = description;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getTapCode() {
        return tapCode;
    }

    public void setTapCode(String tapCode) {
        this.tapCode = tapCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

}
