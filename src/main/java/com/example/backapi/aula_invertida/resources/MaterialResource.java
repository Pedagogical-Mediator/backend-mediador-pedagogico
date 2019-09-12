package com.example.backapi.aula_invertida.resources;

import com.example.backapi.aula_invertida.domain.Material;
import com.example.backapi.aula_invertida.services.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/aulas")
public class MaterialResource {

    @Autowired
    MaterialService materialService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Material> save (@RequestBody Material material) throws Exception {

        Material material_salvo = materialService.save(material);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(material_salvo.getId()).toUri();
        return ResponseEntity.created(uri).body(material);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<Material>> findPage(
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="10") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="id") String orderBy,
            @RequestParam(value="direction", defaultValue="ASC") String direction) {
        Page<Material> paginas = materialService.findPage(page, linesPerPage, orderBy, direction);
        return ResponseEntity.ok().body(paginas);
    }
}
