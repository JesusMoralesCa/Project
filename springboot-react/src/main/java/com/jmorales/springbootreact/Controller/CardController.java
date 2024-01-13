package com.jmorales.springbootreact.Controller;


import com.jmorales.springbootreact.Model.Card;
import com.jmorales.springbootreact.Payload.Response.CardResponse;
import com.jmorales.springbootreact.Service.ICardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardController {

    private final ICardService cardService;

    @GetMapping("/allCards")
    public ResponseEntity<List<CardResponse>> getCards(){
       return new ResponseEntity<>(cardService.getAllCardsResponse(),HttpStatus.OK);
    }

    @GetMapping("/allCards/{cardName}")
    public ResponseEntity<CardResponse> getCard(@PathVariable("cardName") String cardName) {
        return new ResponseEntity<>(cardService.getCardResponse(cardName),HttpStatus.OK);
    }

    @PostMapping("/create-new-card")
    public ResponseEntity<String> createCard(@RequestParam("cardName") String cardName,
                                             @RequestParam("cardImage") MultipartFile cardImage,
                                             @RequestParam("description") String description,
                                             @RequestParam("packName") String packName){
        try {
            cardService.createCard(cardName, cardImage, description, packName);
            return ResponseEntity.ok("Card created");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

    }


}
