package com.example.freeaccess.service.bullying;

import com.example.freeaccess.config.ModelMapper;
import com.example.freeaccess.domain.bullying.Bullying;
import com.example.freeaccess.domain.bullying.BullyingDTO;
import com.example.freeaccess.exceptions.ObjectLimitException;
import com.example.freeaccess.repository.BullyingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveBullying {

    @Autowired
    private BullyingRepository repository;

    @Autowired
    private ModelMapper modelMapper;


    public BullyingDTO execute(BullyingDTO bullyingDTO) throws ObjectLimitException {

        if (!this.repository.findAll().isEmpty()) {
            throw new ObjectLimitException("The number of bullying information allowed is only one. If you want to update the information, send a PUT");
        }

        Bullying bullying = modelMapper.modelMapper().map(bullyingDTO, Bullying.class);

        bullying = this.repository.save(bullying);

        return modelMapper.modelMapper().map(bullying, BullyingDTO.class);

    }
}
