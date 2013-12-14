/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Riad
 */
@ManagedBean
@RequestScoped
public class RoamOpBean implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private ArrayList<TestMsisdn> msisdnList;
    /**
     * Creates a new instance of RoamOpBean
     */
    public RoamOpBean() {
    }

    public ArrayList<TestMsisdn> getMsisdnList() {
        return msisdnList;
    }

    public void setMsisdnList(ArrayList<TestMsisdn> msisdnList) {
        this.msisdnList = msisdnList;
    }
    
    class TestMsisdn {
        
        private String Msisdn;
        private String Country;
        private String Operator;
        private String TapCode;
        private String Description;
        
        TestMsisdn(String msisdn, String country, String operator, String tapCode, String description) {            
            this.Msisdn = msisdn;
            this.Country = country;
            this.Operator = operator;
            this.TapCode = tapCode;
            this.Description = description;
        }

        public String getMsisdn() {
            return Msisdn;
        }

        public void setMsisdn(String Msisdn) {
            this.Msisdn = Msisdn;
        }

        public String getCountry() {
            return Country;
        }

        public void setCountry(String Country) {
            this.Country = Country;
        }

        public String getOperator() {
            return Operator;
        }

        public void setOperator(String Operator) {
            this.Operator = Operator;
        }

        public String getTapCode() {
            return TapCode;
        }

        public void setTapCode(String TapCode) {
            this.TapCode = TapCode;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String Description) {
            this.Description = Description;
        }
                
    }
    
}
