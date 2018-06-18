/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waterfordtours;

import java.io.Serializable;

/**
 *
 * @author Arron
 */
public class Attractions implements Serializable{

    String name;
    String address;
    Double COE;
    String OT;
    String desc;

    public Attractions(String nameIn, String addressIn, Double COEin, String OTin, String descIn) {
        name = nameIn;
        address = addressIn;
        COE = COEin;
        OT = OTin;
        desc = descIn;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getCOE() {
        return COE;
    }

    public void setCOE(Double COE) {
        this.COE = COE;
    }

    public String getOT() {
        return OT;
    }

    public void setOT(String OT) {
        this.OT = OT;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
