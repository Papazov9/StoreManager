package com.returns.store.storagemanager.service;

import com.returns.store.storagemanager.model.bindings.RackBinding;
import com.returns.store.storagemanager.model.entity.Rack;
import com.returns.store.storagemanager.model.entity.SellingProduct;
import com.returns.store.storagemanager.model.enums.SizeEnum;
import com.returns.store.storagemanager.model.exceptions.AllRacksAreFullException;
import com.returns.store.storagemanager.model.exceptions.UnableToSaveProductToRackException;
import com.returns.store.storagemanager.model.view.RackViewModel;
import com.returns.store.storagemanager.model.view.RackViewResponseEntity;
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
                .setSize(sizeEnum)
                .setNextFree(1);
        this.rackRepo.saveAndFlush(rack);
    }

    public List<RackViewModel> getAllRacks() {
        return this.rackRepo.findAll()
                .stream()
                .map(r -> this.modelMapper.map(r, RackViewModel.class))
                .toList();
    }

    public RackViewResponseEntity findFirstFreeRackNumberBySize(SizeEnum size) {
        List<Rack> allRackBySize = this.rackRepo.findAllBySizeOrderByRackNameAsc(size);
        allRackBySize = allRackBySize
                .stream()
                .filter(r -> r.getNextFree() != -1)
                .toList();

        if (allRackBySize.size() == 0)
        {
            throw new AllRacksAreFullException(size);
        }
        RackViewResponseEntity result = new RackViewResponseEntity();
        result.setRackName(allRackBySize.get(0).getRackName());
        result.setRackNumber(allRackBySize.get(0).getNextFree());
        return result;
    }

    public RackViewResponseEntity findDifferentRackNumber(SizeEnum valueOf, String rackName, int rackNumber) {
        Rack rack = this.rackRepo.findByRackName(rackName).get();
        RackViewResponseEntity result = new RackViewResponseEntity();
        if (rackNumber < rack.getQuantity()) {
            result.setRackName(rackName);
            result.setRackNumber(rackNumber + 1);
            return result;
        }

        return null;
    }

    public void updateRack(SellingProduct sellingProduct, String rackName, Integer rackNumber) {
        Optional<Rack> byRackName = this.rackRepo.findByRackName(rackName);
        if (byRackName.isPresent()){
            if (byRackName.get().addProduct(sellingProduct)) {
                this.rackRepo.saveAndFlush(byRackName.get());
            }
            else throw new UnableToSaveProductToRackException(byRackName.get().getId());
        }
    }
}
