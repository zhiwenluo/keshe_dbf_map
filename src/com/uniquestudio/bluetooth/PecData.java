package com.uniquestudio.bluetooth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.uniquestudio.bluetooth.CHexConver;

import android.util.Printer;
import static com.uniquestudio.stringconstant.StringConstant.PecDataItem;

public class PecData {

    private DataFlag[] dataFlag;
    private byte[] stTimer;
    
    public PecData() {
	this.dataFlag = new DataFlag[DataInd.DATA_IND_MAX];
	this.stTimer = new byte[50];
	for (int i = 0; i < DataInd.DATA_IND_MAX; i++) {
	    this.dataFlag[i] = new DataFlag();
	}
    }
    
    public void setDataFlags(DataFlag[] newDataFlag) {
	if (newDataFlag.length > DataInd.DATA_IND_MAX)
	    return;
	for (int i = 0; i < newDataFlag.length; i++) {
	    this.dataFlag[i].setDataFlag(newDataFlag[i]);
	}
    }
    
    public void setTimer(byte[] newTimer) {
	if (newTimer.length > 50) {
	    return;
	}
	this.stTimer = new byte[newTimer.length];
	System.arraycopy(newTimer, 0, this.stTimer, 0, newTimer.length);
    }
    
    public DataFlag[] getDataFlags() {
	return this.dataFlag;
    }
    
    public byte[] getTimer() {
	return this.stTimer;
    }
    
    
    public HashMap<String, Object> toList() {
	HashMap<String , Object> hashMap  = new HashMap<String, Object>();
	String stTimerString = CHexConver.decode(CHexConver.printHexString("stTimer : ", this.stTimer));
	hashMap.put("Timer", stTimerString);
	for (int i = 0; i < PecDataItem.length; i++) {
	    if(this.dataFlag[i].getFlag())	
		hashMap.put(PecDataItem[i], this.dataFlag[i].getData());
	    else
		hashMap.put(PecDataItem[i], "无");
	}
	return hashMap;
    }
    public void printer() {
	System.out.println("stTimer:"+CHexConver.decode(CHexConver.printHexString("stTimer : ", this.stTimer)));
	for (int i = 0; i < DataInd.DATA_IND_MAX; i++) {
	    if(this.dataFlag[i].getFlag())
		System.out.println(i+":" +this.dataFlag[i].getData());
	    else
		System.out.println(i+": null");
	}
    }
    /*
    float Ua;
    float Ia;
    float Ub;
    float Ib;
    float Uc;
    float Ic;

    float Pa;//A向有功功率
    float Pb;
    float Pc;
    float Qa;//A向无功功率
    float Qb;
    float Qc;

    float Aa; //A向相角
    float Ab;
    float Ac;

    float Pabc;//abc有功总功率
    float Qabc;//abc向无功功率总和

    float Uab;//AB向电压角度
    float Uac;

    float Iac;//AC向电流角度
    float Cos;//功率因素；
    float Fre;//频率;
    float Ucb;*/

}
