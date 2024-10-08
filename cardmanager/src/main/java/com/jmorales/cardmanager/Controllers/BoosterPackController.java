package com.jmorales.cardmanager.Controllers;

import com.jmorales.cardmanager.Service.IBoosterPackService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/boosterPack")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class BoosterPackController {

    @Autowired
    private final IBoosterPackService boosterPackService;

    @GetMapping("/getBoosterPacks")
    public ResponseEntity<?> getBoosterPacks(){
        try {
            return ResponseEntity.ok(boosterPackService.getBoosterPacks());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/getBoosterPacksName")
    public ResponseEntity<?> getBoosterPacksName(){
        try {
            return ResponseEntity.ok(boosterPackService.getBoosterPacksName());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/getPack/{packName}")
    public ResponseEntity<?> getPack(@PathVariable("packName") String packName){
        try {
            return ResponseEntity.ok(boosterPackService.getPack(packName));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PostMapping("/createBoosterPack")
    public ResponseEntity<?> createBoosterPack(@RequestParam("name") String name,
                                               @RequestParam("file") String file){
        try {
            return ResponseEntity.ok(boosterPackService.createBoosterPack(name,file));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @DeleteMapping("/deleteBoosterPack/{packName}")
    public ResponseEntity<?> deleteBoosterPack(@PathVariable("packName") String packName){
        try {
            boosterPackService.deleteBoosterPack(packName);
            return ResponseEntity.ok("Pack eliminado");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

}
