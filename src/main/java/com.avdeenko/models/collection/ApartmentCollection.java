package com.avdeenko.models.collection;

import com.avdeenko.models.model.Apartment;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ApartmentCollection {
    private List<Apartment> apartments;

    public ApartmentCollection() {
        this.apartments = new ArrayList<>();
    }

    public ApartmentCollection(List<Apartment> apartments) {
        this.apartments = apartments;
    }

    public void addApartment(Apartment apartment) {
        this.apartments.add(apartment);
    }
}
