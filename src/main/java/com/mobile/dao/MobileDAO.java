package com.mobile.dao;

import com.mobile.model.Display;
import com.mobile.model.Mobile;
import com.mobile.model.Ram;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MobileDAO {

    private List<Mobile> storedMobiles = new ArrayList<>();
    private Map<Integer, Ram> storedRam = new HashMap<>();
    private Map<Integer, Display> storedDisplay = new HashMap<>();

    public MobileDAO() {
        this.storedDisplay.put(1, Display.builder().density(440).screenSize(5.6f).screenType("AMOLED").build());
        this.storedDisplay.put(2, Display.builder().density(499).screenSize(6.4f).screenType("SAMOLED").build());
        this.storedRam.put(1, Ram.builder().size(2048).speed(1666).manufacturer("Aikeon").build());
        this.storedRam.put(2, Ram.builder().size(4096).speed(2444).manufacturer("Ceeka").build());
    }

    public MobileResponse addMobile(Mobile mobile) {
        this.storedMobiles.add(mobile);
        MobileDataResponse mobileDataResponse = new MobileDataResponse();
        mobileDataResponse.setDisplay(this.storedDisplay.get(mobile.getDisplayCode()));
        mobileDataResponse.setRam(this.storedRam.get(mobile.getRamCode()));
        mobileDataResponse.setModelName(mobile.getModelName());
        mobileDataResponse.setVersion(mobile.getVersion());
        MobileResponse mobileResponse = new MobileResponse();
        mobileResponse.addData(mobileDataResponse);
        mobileResponse.setStatus("Success");
        return mobileResponse;
    }

    public MobileResponse getAll() {
        MobileResponse mobileResponse = new MobileResponse();
        MobileDataResponse mobileDataResponse = new MobileDataResponse();
        for (Mobile record: storedMobiles) {
            mobileDataResponse.setVersion(record.getVersion());
            mobileDataResponse.setRam(storedRam.get(record.getRamCode()));
            mobileDataResponse.setDisplay(storedDisplay.get(record.getDisplayCode()));
            mobileDataResponse.setModelName(record.getModelName());
            mobileResponse.addData(mobileDataResponse);
        }
        mobileResponse.setStatus("Success");
        return mobileResponse;
    }

    public MobileResponse filter(String modelName, int version, int ramSize) {
        MobileResponse mobileResponse = new MobileResponse();
        MobileDataResponse mobileDataResponse = new MobileDataResponse();
        for (Mobile record: storedMobiles) {
            if (record.getVersion() == version && record.getModelName().equalsIgnoreCase(modelName) && storedRam.get(record.getRamCode()).getSize() == ramSize) {
                mobileDataResponse.setVersion(record.getVersion());
                mobileDataResponse.setRam(storedRam.get(record.getRamCode()));
                mobileDataResponse.setDisplay(storedDisplay.get(record.getDisplayCode()));
                mobileDataResponse.setModelName(record.getModelName());
                mobileResponse.addData(mobileDataResponse);
            }
        }
        mobileResponse.setStatus("Success");
        return mobileResponse;
    }

    public MobileResponse clear() {
        this.storedMobiles.clear();
        return getAll();
    }

    @Data
    public static class MobileDataResponse {
        private String modelName;
        private int version;
        private Ram ram;
        private Display display;
    }
    @Data
    public static class MobileResponse {
        private List<MobileDataResponse> data;
        private String status;

        public void addData(MobileDataResponse mobileDataResponse) {
            if (this.data == null)
                this.data = new ArrayList<>();
            this.data.add(mobileDataResponse);
        }
    }
}
