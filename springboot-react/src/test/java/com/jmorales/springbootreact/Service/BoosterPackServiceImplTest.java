package com.jmorales.springbootreact.Service;

import com.jmorales.springbootreact.Model.BoosterPack;
import com.jmorales.springbootreact.Model.Card;
import com.jmorales.springbootreact.Payload.Response.CardResponse;
import com.jmorales.springbootreact.Repository.BoosterPackRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class BoosterPackServiceImplTest {

    @Mock
    private BoosterPackRepository boosterPackRepository;

    @InjectMocks
    private BoosterPackServiceImpl boosterPackService;

    @Test
    public void getBoosterPacks() {
        BoosterPack boosterPack1 = new BoosterPack();
        boosterPack1.setId(1L);
        boosterPack1.setName("pack1");

        BoosterPack boosterPack2 = new BoosterPack();
        boosterPack2.setId(1L);
        boosterPack2.setName("pack2");

        List<BoosterPack> mockBoosterPack = new ArrayList<>();
        mockBoosterPack.add(boosterPack1);
        mockBoosterPack.add(boosterPack2);

        when(boosterPackRepository.findAll()).thenReturn(mockBoosterPack);

        List<BoosterPack> result = boosterPackService.getBoosterPacks();

        assertEquals(mockBoosterPack.size(), result.size());
        assertEquals(mockBoosterPack.get(0).getName(), result.get(0).getName());
        assertEquals(mockBoosterPack.get(1).getName(), result.get(1).getName());
        assertNotEquals(mockBoosterPack.get(0).getName(), result.get(1).getName());

        Mockito.verify(boosterPackRepository).findAll();


    }

    @Test
    void createBoosterPack() throws SQLException, IOException {
        String packName = "NuevoPack";
        MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "Spring Framework".getBytes());


        when(boosterPackRepository.findByName(packName)).thenReturn(Optional.empty());

        BoosterPack createdPack = boosterPackService.createBoosterPack(packName, file);

        assertEquals(packName, createdPack.getName());
        assertNotNull(createdPack.getImage());

        Mockito.verify(boosterPackRepository).save(Mockito.any(BoosterPack.class));

    }

    @Test
    void getAllCardResponseFromBoosterPack() throws SQLException {

        BoosterPack mockBoosterPack = new BoosterPack();
        mockBoosterPack.setId(1L);
        mockBoosterPack.setName("pack");

        byte[] imageData = "ImageData".getBytes(); // Datos de imagen ficticios
        Blob mockBlob1 = new SerialBlob(imageData);

        Card card1 = new Card();
        card1.setId(1L);
        card1.setName("Card1");
        card1.setImage(mockBlob1);
        card1.setDescription("Description1");
        card1.setBoosterPack(mockBoosterPack);

        Card card2 = new Card();
        card2.setId(2L);
        card2.setName("Card2");
        card2.setImage(mockBlob1);
        card2.setDescription("Description2");
        card2.setBoosterPack(mockBoosterPack);

        mockBoosterPack.setCardsList(Arrays.asList(card1, card2));

        when(boosterPackRepository.findByName("pack")).thenReturn(java.util.Optional.of(mockBoosterPack));

        List<CardResponse> cardResponses = boosterPackService.getAllCardResponseFromBoosterPack("pack");

        assertEquals(2, cardResponses.size());


    }

    @Test
    void deleteBoosterPack() {
    }

    @Test
    void getPack() {
    }

    @Test
    void getBoosterPackResponse() {
    }

    @Test
    void getBoosterPackResponseLow() {
    }
}