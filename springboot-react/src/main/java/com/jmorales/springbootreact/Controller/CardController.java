package com.jmorales.springbootreact.Controller;

import com.jmorales.springbootreact.Exception.CardNotFoundException;
import com.jmorales.springbootreact.Model.Card;
import com.jmorales.springbootreact.Service.ICardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardController {

    private final ICardService cardService;

    @GetMapping("/allCards")
    public List<Card> getCards(){
       return cardService.getCards();
    }

    @GetMapping("/allCards/{cardName}")
    public Card getCard(@PathVariable("cardName") String cardName) {
        return cardService.getCard(cardName);
    }




}
