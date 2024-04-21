package com.example.cryptotrading.scheduler;

import java.util.List;

public class HuobiResponse {
    private List<HuobiItem> data ;
    String status;
    private long ts;

    @Override
    public String toString() {
        return "HuobiResponse{" +
                "data=" + data +
                ", status='" + status + '\'' +
                ", ts=" + ts +
                '}';
    }

    public HuobiResponse(List<HuobiItem> data, String status, long ts) {
        this.data = data;
        this.status = status;
        this.ts = ts;
    }

    public List<HuobiItem> getData() {
        return data;
    }

    public void setData(List<HuobiItem> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }
}
