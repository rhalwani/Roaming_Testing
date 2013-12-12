/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean;

import ejb.HLRCommander;
import ejb.HlrCommandDetailFacade;
import ejb.HlrCommandFacade;
import ejb.HlrCommandParamFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import javax.ejb.EJB;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlColumn;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author Riad
 */
@ManagedBean
@ViewScoped
public class HLRMBean {

    /**
     * Creates a new instance of HLRMBean
     */
    public HLRMBean() {
    }
    
    @EJB
    private HlrCommandFacade hlrCmdEJB;

    @EJB
    private HlrCommandDetailFacade hlrCmdDetEJB;

    @EJB
    private HlrCommandParamFacade hlrCmdParamEJB;

    @EJB
    private HLRCommander hlrCmderEJB;
    private List<String> cmdList;
    private List<String> paramListTable;
    private ArrayList<String> cmdSringList;
    private String hlrCmdDesc;
    private String pgwReply;
    private String[] paramStringInput;
    private HtmlPanelGroup inputFieldPanelGroup;

    public List<String> getCmdList() {
        this.cmdList = this.hlrCmdEJB.getHLRCmdList();
        return this.cmdList;
    }

    public void setCmdList(List<String> cmdList) {
        this.cmdList = cmdList;
    }

    public String getHlrCmdDesc() {
        return this.hlrCmdDesc;
    }

    public void setHlrCmdDesc(String hlrCmdDesc) {
        this.hlrCmdDesc = hlrCmdDesc;
    }

    public String getPgwReply() {
        return this.pgwReply;
    }

    public void setPgwReply(String pgwReply) {
        this.pgwReply = pgwReply;
    }

    public ArrayList<String> getCmdSringList() {
        return this.cmdSringList;
    }

    public void setCmdSringList(ArrayList<String> cmdSringList) {
        this.cmdSringList = cmdSringList;
    }

    public void sendHLRCommand() {
        generateCmdString();
        this.hlrCmderEJB.executeCommand(getCmdSringList());
        setPgwReply(this.hlrCmderEJB.getCmdOutput());
    }

    private void generateCmdString() {
        String cmdDesc = getHlrCmdDesc();
        Short cmdId = this.hlrCmdEJB.getCmdId(cmdDesc);

        ArrayList[] hlrCmdDetail = this.hlrCmdDetEJB.getHLRCmdDetail(cmdId);

        this.cmdSringList = new ArrayList(hlrCmdDetail[0].size());

        Iterator cmdDetIndxIter = hlrCmdDetail[0].iterator();

        Iterator cmdDetActIter = hlrCmdDetail[1].iterator();

        ArrayList[] mmlCmdParamAndVal = this.hlrCmdParamEJB.getCmdParams(cmdId);

        ListIterator cmdParamIndxIter = mmlCmdParamAndVal[0].listIterator();

        Iterator cmdParamArgIter = mmlCmdParamAndVal[1].iterator();

        Iterator cmdParamValIter = mmlCmdParamAndVal[2].iterator();

        while (cmdDetIndxIter.hasNext()) {
            Short cmdIndx = (Short) cmdDetIndxIter.next();
            String mmlCmd = (String) cmdDetActIter.next();
            String nextCmd = mmlCmd.concat(":");
            int i = mmlCmdParamAndVal[0].size();
            while (cmdParamIndxIter.hasNext()) {
                Short cmdParamIndx = (Short) cmdParamIndxIter.next();
                if (cmdParamIndx.compareTo(cmdIndx) > 0) {
                    cmdParamIndxIter.previous();
                    break;
                }
                String mmlCmdParam = (String) cmdParamArgIter.next();
                String mmlCmdVal = (String) cmdParamValIter.next();
                if (mmlCmdVal.isEmpty()) {
                    nextCmd = nextCmd.concat(mmlCmdParam.concat("=".concat("\"" + getParamStringInput()[getParamListTable().indexOf(mmlCmdParam)] + "\"".concat(","))));
                } else {
                    nextCmd = nextCmd.concat(mmlCmdParam.concat("=".concat(mmlCmdVal.concat(","))));
                }
            }

            this.cmdSringList.add(nextCmd.substring(0, nextCmd.length() - 1).concat(";"));
        }
    }

    public void selectedCmdChanged(ValueChangeEvent vce) {
        this.inputFieldPanelGroup.getChildren().clear();
        populateFieldPanelGroup(vce.getNewValue().toString());
    }

    private void populateFieldPanelGroup(String cmdDesc) {
        this.inputFieldPanelGroup.getChildren().clear();

        Short cmdId = this.hlrCmdEJB.getCmdId(cmdDesc);
        setParamListTable(this.hlrCmdParamEJB.getCmdInputParams(cmdId));
        setParamStringInput(new String[getParamListTable().size()]);

        FacesContext fCtx = FacesContext.getCurrentInstance();
        ELContext elCtx = fCtx.getELContext();
        ExpressionFactory ef = fCtx.getApplication().getExpressionFactory();

        HtmlDataTable pTable = new HtmlDataTable();
        ValueExpression vExp = ef.createValueExpression(elCtx, "#{hLRMBean.paramListTable}", List.class);
        pTable.setValueExpression("value", vExp);
        pTable.setVar("cmdParam");

        Iterator cmdParamArgIter = getParamListTable().iterator();
        int i = 0;

        while (cmdParamArgIter.hasNext()) {
            String paramName = (String) cmdParamArgIter.next();
            HtmlColumn col = new HtmlColumn();
            HtmlOutputText paramHeader = new HtmlOutputText();
            paramHeader.setValue(paramName);
            col.setHeader(paramHeader);

            HtmlInputText paramValueInTxt = new HtmlInputText();

            vExp = ef.createValueExpression(elCtx, "#{hLRMBean.paramStringInput[" + i + "]}", String.class);
            paramValueInTxt.setValueExpression("value", vExp);
            col.setFooter(paramValueInTxt);
            pTable.getChildren().add(col);
            i++;
        }
        this.inputFieldPanelGroup.getChildren().add(pTable);
    }

    public HtmlPanelGroup getInputFieldPanelGroup() {
        return this.inputFieldPanelGroup;
    }

    public void setInputFieldPanelGroup(HtmlPanelGroup inputFieldPanelGroup) {
        this.inputFieldPanelGroup = inputFieldPanelGroup;
    }

    public List<String> getParamListTable() {
        return this.paramListTable;
    }

    public void setParamListTable(List<String> paramListTable) {
        this.paramListTable = paramListTable;
    }

    public String[] getParamStringInput() {
        return this.paramStringInput;
    }

    public void setParamStringInput(String[] paramStringInput) {
        this.paramStringInput = paramStringInput;
    }
}
