package com.example.demo.Entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.List;


@Document("metinler")

public class Metin {


    @Id
    private ObjectId id;


    private List<String> inputs;

    private String output;

    private boolean mergable;

    private long time;

    public Metin() {
    }

    public Metin(ObjectId id, List<String> inputs, String output, boolean mergable, long time) {
        this.id = id;
        this.inputs = inputs;
        this.output = output;
        this.mergable = mergable;
        this.time = time;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public List<String> getInputs() {
        return inputs;
    }

    public void setInputs(List<String> inputs) {
        this.inputs = inputs;
    }


    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public boolean isMergable() {
        return mergable;
    }

    public void setMergable(boolean mergable) {
        this.mergable = mergable;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }











}
