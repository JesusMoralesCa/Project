package com.jmorales.springbootreact.Controller;

import com.jmorales.springbootreact.Model.BoosterPack;
import com.jmorales.springbootreact.Model.Card;
import com.jmorales.springbootreact.Service.IBoosterPackService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Blob;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/packs")
@RequiredArgsConstructor
public class BoosterPackController {

    private final IBoosterPackService boosterPackService;

    @GetMapping("/all")
    public List<BoosterPack> getAllBoosterPacks(){
       return boosterPackService.getBoosterPacks();
    }


    @GetMapping("/cards")
    public List<Card> getAllCardsFromBoosterPack(@RequestParam String packname){
        return  boosterPackService.getAllCardsFromBoosterPack(packname);
    }


    @PostMapping("/createPack")
    public ResponseEntity<String> createPack(@RequestParam String packname, @RequestParam Blob image){
        try {
            boosterPackService.createBoosterPack(packname, image);
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
