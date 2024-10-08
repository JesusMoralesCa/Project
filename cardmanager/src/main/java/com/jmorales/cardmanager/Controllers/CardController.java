package com.jmorales.cardmanager.Controllers;

import com.jmorales.cardmanager.Service.ICardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class CardController {

    @Autowired
    private final ICardService cardService;

    @GetMapping("/getCard/{name}")
    public ResponseEntity<?> getCard(@PathVariable("name") String name){
        try {
            return ResponseEntity.ok(cardService.getCard(name));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/getAllCards")
    public ResponseEntity<?> getAllCards(){
        try {
            return ResponseEntity.ok(cardService.getAllCards());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }


    @PostMapping("/createCard")
    public ResponseEntity<?> createCard(@RequestParam("name") String name,
                                        @RequestParam("image") String image,
                                        @RequestParam("rareza") String rareza,
                                        @RequestParam("color") String color,
                                        @RequestParam("dp") int dp,
                                        @RequestParam("boosterPack") String boosterPack){
        try {
            cardService.createCard(name,image,rareza,color,dp,boosterPack);

            return ResponseEntity.ok("Carta creada");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @DeleteMapping("/deleteCard/{card}")
    public ResponseEntity<?> deleteCard(@PathVariable("card") String card){
        try {
            cardService.deleteCard(card);
            return ResponseEntity.ok("Carta eliminado");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

}
