package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "open_cell_id")
public class OpenCellId {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int Id;

    @Column(name = "radio")
    private String radio;

    @Column(name = "mcc")
    private int mcc;

    @Column(name = "mnc")
    private int mnc;

    @Column(name = "lac")
    private int lac;

    @Column(name = "cid")
    private long cid;

    @Column(name = "unit")
    private int unit;

    @Column(name = "lon")
    private double lon;

    @Column(name = "lat")
    private double lat;

    @Column(name = "cell_range")
    private int range;

    @Column(name = "samples")
    private int samples;

    @Column(name = "changeable")
    private int changeable;

    @Column(name = "created")
    private int created;

    @Column(name = "updated")
    private int updated;

    @Column(name = "average_signal")
    private int averageSignal;
}
