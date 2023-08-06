package com.returns.store.storagemanager.service;

import com.returns.store.storagemanager.model.bindings.RackBinding;
import com.returns.store.storagemanager.model.entity.Rack;
import com.returns.store.storagemanager.model.enums.SizeEnum;
import com.returns.store.storagemanager.model.view.RackViewModel;
import com.returns.store.storagemanager.repo.RackRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RackService {

    private final RackRepo rackRepo;
    private final ModelMapper modelMapper;

    public RackService(RackRepo rackRepo, ModelMapper modelMapper) {
        this.rackRepo = rackRepo;
        this.modelMapper = modelMapper;
    }

    public boolean isRackExists(RackBinding rackBinding) {
        Optional<Rack> byRackName = this.rackRepo.findByRackName(rackBinding.getRackName());
        return byRackName.isPresent();
    }

    public void addRack(RackBinding rackBinding) {
        SizeEnum sizeEnum = SizeEnum.valueOf(rackBinding.getType());
        Rack rack = new Rack();
        rack.setRackName(rackBinding.getRackName())
                .setQuantity(rackBinding.getQuantity())
                .setSize(sizeEnum);
        this.rackRepo.saveAndFlush(rack);
    }

    public List<RackViewModel> getAllRacks() {
        return this.rackRepo.findAll()
                .stream()
                .map(r -> this.modelMapper.map(r, RackViewModel.class))
                .toList();
    }
}
