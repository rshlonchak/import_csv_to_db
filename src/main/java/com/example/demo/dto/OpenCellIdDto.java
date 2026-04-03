package com.example.demo.dto;

import com.example.demo.model.OpenCellId;
import lombok.Data;

@Data
public class OpenCellIdDto implements DtoMapper<OpenCellId> {
    private String radio;
    private int mcc;
    private int mnc;
    private int lac;
    private long cid;
    private int unit;
    private double lon;
    private double lat;
    private int range;
    private int samples;
    private int changeable;
    private long created;
    private long updated;
    private int averageSignal;

    @Override
    public OpenCellIdDto fromEntity(OpenCellId entity) {
        this.radio = entity.getRadio();
        this.mcc = entity.getMcc();
        this.mnc = entity.getMnc();
        this.lac = entity.getLac();
        this.cid = entity.getCid();
        this.unit = entity.getUnit();
        this.lon = entity.getLon();
        this.lat = entity.getLat();
        this.range = entity.getRange();
        this.samples = entity.getSamples();
        this.changeable = entity.getChangeable();
        this.created = entity.getCreated();
        this.updated = entity.getUpdated();
        this.averageSignal = entity.getAverageSignal();
        return this;
    }
}
