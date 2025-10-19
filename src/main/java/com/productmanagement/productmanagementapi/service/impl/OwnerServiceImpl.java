package com.productmanagement.productmanagementapi.service.impl;

import com.productmanagement.productmanagementapi.exception.NotFoundException;
import com.productmanagement.productmanagementapi.model.entity.Owner;
import com.productmanagement.productmanagementapi.repository.OwnerRepository;
import com.productmanagement.productmanagementapi.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;

    @Override
    public Owner ownerLogin(Owner owner) {
        if (ownerRepository.existsByUsernameContainingIgnoreCaseAndPassword(owner.getUsername(), owner.getPassword())) {
            return owner;
        }
        throw new NotFoundException("Owner not found");
    }
}
