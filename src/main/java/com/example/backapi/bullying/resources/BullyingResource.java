package com.example.backapi.bullying.resources;

import com.example.backapi.bullying.domain.Bullying;
import com.example.backapi.bullying.domain.BullyingDTO;
import com.example.backapi.bullying.services.BullyingService;
import com.example.backapi.utils.mapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/bullying")
public class BullyingResource {
    @Autowired
    BullyingService bullyingService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<BullyingDTO> save(@RequestBody BullyingDTO bullyingDTO){
        Bullying bullyingSalvo = bullyingService.save(modelMapper.modelMapper().map(bullyingDTO, Bullying.class));
        bullyingDTO = modelMapper.modelMapper().map(bullyingSalvo, BullyingDTO.class);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(bullyingDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(bullyingDTO);
    }
}
