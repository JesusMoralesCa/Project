package com.jmorales.springbootreact.Controller;

import com.jmorales.springbootreact.Model.BoosterPack;
import com.jmorales.springbootreact.Model.Card;
import com.jmorales.springbootreact.Payload.Response.BoosterPackResponse;
import com.jmorales.springbootreact.Payload.Response.CardResponse;
import com.jmorales.springbootreact.Service.IBoosterPackService;

import com.jmorales.springbootreact.Service.ICardService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/packs")
@RequiredArgsConstructor
public class BoosterPackController {

    private final IBoosterPackService boosterPackService;
    private final ICardService cardService;

    @GetMapping("/all")
    public List<BoosterPack> getAllBoosterPacks(){
       return boosterPackService.getBoosterPacks();
    }

    @GetMapping("/allBooster")
    public ResponseEntity<List<BoosterPackResponse>> getAllBoosters() throws SQLException {
        List<BoosterPackResponse> packResponses = new ArrayList<>();
        List<BoosterPack> packsList = boosterPackService.getBoosterPacks();

        for (BoosterPack pack : packsList) {
            // Obtener la respuesta del paquete y agregarla a la lista
            BoosterPackResponse packResponse = boosterPackService.getBoosterPackResponse(pack.getName());
            packResponses.add(packResponse);
        }

        return new ResponseEntity<>(packResponses, HttpStatus.OK);
    }



    @GetMapping("/cards")
    public List<CardResponse> getAllCardsFromBoosterPack(@RequestParam String packName){
        return  boosterPackService.getAllCardResponseFromBoosterPack(packName);
    }


    @PostMapping("/createPack")
    public ResponseEntity<String> createPack(@RequestParam String packName, @RequestParam MultipartFile file){
        try {
            boosterPackService.createBoosterPack(packName, file);
            return ResponseEntity.ok("Pack created");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deletePack(@PathVariable("packName") String packName){
        try {
            boosterPackService.deleteBoosterPack(packName);
            return ResponseEntity.ok("Pack deleted");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }


}
