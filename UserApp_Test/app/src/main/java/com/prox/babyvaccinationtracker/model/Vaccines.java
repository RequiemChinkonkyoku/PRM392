package com.prox.babyvaccinationtracker.model;

import android.net.Uri;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Vaccines implements Serializable {
    String vaccine_id;
    String vaccine_name;
    String vac_effectiveness;

    @Override
    public String toString() {
        return "Vaccines{" +
                "vaccine_id='" + vaccine_id + '\'' +
                ", vaccine_name='" + vaccine_name + '\'' +
                ", vac_effectiveness='" + vac_effectiveness + '\'' +
                ", post_vaccination_reactions='" + post_vaccination_reactions + '\'' +
                ", origin='" + origin + '\'' +
                ", vaccination_target_group='" + vaccination_target_group + '\'' +
                ", contraindications='" + contraindications + '\'' +
                ", quantity='" + quantity + '\'' +
                ", dosage='" + dosage + '\'' +
                ", unit='" + unit + '\'' +
                ", date_of_entry='" + date_of_entry + '\'' +
                ", price='" + price + '\'' +
                ", vaccine_image=" + vaccine_image +
                ", deleted=" + deleted +
                '}';
    }

    String post_vaccination_reactions;
    String origin;
    String vaccination_target_group;
    String contraindications;
    String quantity;
    String dosage;
    String unit;
    String date_of_entry;
    String price;
    ArrayList<String> vaccine_image;
    boolean deleted;

    public HashMap<String, String> getAdditionInformation() {
        return additionInformation;
    }

    public void setAdditionInformation(HashMap<String, String> additionInformation) {
        this.additionInformation = additionInformation;
    }

    private Vaccine_center vaccine_center_owner;

    public Vaccine_center getVaccine_center_owner() {
        return vaccine_center_owner;
    }

    public void setVaccine_center_owner(Vaccine_center vaccine_center_owner) {
        this.vaccine_center_owner = vaccine_center_owner;
    }
    HashMap<String, String> additionInformation = new HashMap<>();

    public Vaccines(String vaccine_id, String vaccine_name, String vac_effectiveness, String post_vaccination_reactions, String origin, String vaccination_target_group, String contraindications, String quantity, String dosage, String unit, String date_of_entry, String price, ArrayList<String> vaccine_image, boolean deleted) {
        this.vaccine_id = vaccine_id;

        this.vaccine_name = vaccine_name;
        this.vac_effectiveness = vac_effectiveness;
        this.post_vaccination_reactions = post_vaccination_reactions;
        this.origin = origin;
        this.vaccination_target_group = vaccination_target_group;
        this.contraindications = contraindications;
        this.quantity = quantity;
        this.dosage = dosage;
        this.unit = unit;
        this.date_of_entry = date_of_entry;
        this.price = price;
        this.vaccine_image = vaccine_image;
        this.deleted = deleted;
    }

    public String getVaccine_id() {
        return vaccine_id;
    }

    public void setVaccine_id(String vaccine_id) {
        this.vaccine_id = vaccine_id;
    }

    public Vaccines(){
        vaccine_name ="";
        vac_effectiveness ="";
        post_vaccination_reactions = "";
        origin = "";
        vaccination_target_group = "";
        contraindications = "";
        quantity = "";
        dosage = "";
        unit = "";
        date_of_entry = "";
        price = "";
        deleted = false;
    }

    public String getVaccine_name() {
        return vaccine_name;
    }

    public String getVac_effectiveness() {
        return vac_effectiveness;
    }

    public String getPost_vaccination_reactions() {
        return post_vaccination_reactions;
    }

    public String getOrigin() {
        return origin;
    }

    public String getVaccination_target_group() {
        return vaccination_target_group;
    }

    public String getContraindications() {
        return contraindications;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getDosage() {
        return dosage;
    }

    public String getUnit() {
        return unit;
    }

    public String getDate_of_entry() {
        return date_of_entry;
    }

    public String getPrice() {
        return price;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public ArrayList<String> getVaccine_image() {
        return vaccine_image;
    }


    public void setVaccine_name(String vaccine_name) {
        this.vaccine_name = vaccine_name;
    }

    public void setVac_effectiveness(String vac_effectiveness) {
        this.vac_effectiveness = vac_effectiveness;
    }

    public void setPost_vaccination_reactions(String post_vaccination_reactions) {
        this.post_vaccination_reactions = post_vaccination_reactions;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setVaccination_target_group(String vaccination_target_group) {
        this.vaccination_target_group = vaccination_target_group;
    }

    public void setContraindications(String contraindications) {
        this.contraindications = contraindications;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setDate_of_entry(String date_of_entry) {
        this.date_of_entry = date_of_entry;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setVaccine_image(ArrayList<String> vaccine_image) {
        this.vaccine_image = vaccine_image;
    }
}
