package com.returns.store.storagemanager.model;

import com.returns.store.storagemanager.model.entity.Rack;

import java.util.Comparator;

public class RackComparator implements Comparator<Rack> {
    @Override
    public int compare(Rack o1, Rack o2) {
        return String.CASE_INSENSITIVE_ORDER.compare(o1.getRackName(), o2.getRackName());
    }
}
