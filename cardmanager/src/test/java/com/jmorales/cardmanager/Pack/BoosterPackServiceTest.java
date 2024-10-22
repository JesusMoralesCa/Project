package com.jmorales.cardmanager.Pack;

import com.jmorales.cardmanager.Models.BoosterPack;
import com.jmorales.cardmanager.Repository.BoosterPackRepository;
import com.jmorales.cardmanager.Service.BoosterPackService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class BoosterPackServiceTest {

    @Mock
    private BoosterPackRepository boosterPackRepository;

    @InjectMocks
    private BoosterPackService boosterPackService;

    @Test
    void getBoosterPacksReturnsListOfBoosterPacks() {
        BoosterPack pack1 = new BoosterPack();
        pack1.setName("Pack1");
        BoosterPack pack2 = new BoosterPack();
        pack2.setName("Pack2");

        Mockito.when(boosterPackRepository.findAll()).thenReturn(Arrays.asList(pack1, pack2));

        var result = boosterPackService.getBoosterPacks();

        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("Pack1", result.get(0).getName());
        Assertions.assertEquals("Pack2", result.get(1).getName());
    }

    @Test
    void getBoosterPackNamesReturnsListOfNames() {
        BoosterPack pack1 = new BoosterPack();
        pack1.setName("Pack1");
        BoosterPack pack2 = new BoosterPack();
        pack2.setName("Pack2");

        Mockito.when(boosterPackRepository.findAll()).thenReturn(Arrays.asList(pack1, pack2));

        var result = boosterPackService.getBoosterPacksName();

        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("Pack1", result.get(0));
        Assertions.assertEquals("Pack2", result.get(1));
    }

    @Test
    void createBoosterPackWhenNotExistsReturnsPack() {
        String name = "NuevoPack";
        BoosterPack pack = new BoosterPack();
        pack.setName(name);

        Mockito.when(boosterPackRepository.findByName(name)).thenReturn(Optional.empty());
        Mockito.when(boosterPackRepository.save(Mockito.any(BoosterPack.class))).thenReturn(pack);

        BoosterPack result = boosterPackService.createBoosterPack(name, "file.png");

        Assertions.assertEquals(name, result.getName());
        Assertions.assertEquals("file.png", result.getImage());
    }

    @Test
    void createBoosterPackWhenAlreadyExistsThrowsException() {
        String name = "PackExistente";
        BoosterPack existingPack = new BoosterPack();
        existingPack.setName(name);

        Mockito.when(boosterPackRepository.findByName(name)).thenReturn(Optional.of(existingPack));

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            boosterPackService.createBoosterPack(name, "file.png");
        });
    }

    @Test
    void getExistingPackReturnsPack() {
        String name = "PackExistente";
        BoosterPack existingPack = new BoosterPack();
        existingPack.setName(name);

        Mockito.when(boosterPackRepository.findByName(name)).thenReturn(Optional.of(existingPack));

        BoosterPack result = boosterPackService.getPack(name);

        Assertions.assertEquals(name, result.getName());
    }

    @Test
    void getNonExistingPackThrowsException() {
        String name = "PackInexistente";

        Mockito.when(boosterPackRepository.findByName(name)).thenReturn(Optional.empty());

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            boosterPackService.getPack(name);
        });
    }

    @Test
    void deleteExistingPackDeletesPack() {
        String name = "PackExistente";
        BoosterPack existingPack = new BoosterPack();
        existingPack.setName(name);

        Mockito.when(boosterPackRepository.findByName(name)).thenReturn(Optional.of(existingPack));

        Assertions.assertDoesNotThrow(() -> boosterPackService.deleteBoosterPack(name));
        Mockito.verify(boosterPackRepository).delete(existingPack);
    }

    @Test
    void deleteNonExistingPackThrowsException() {
        String name = "PackInexistente";

        Mockito.when(boosterPackRepository.findByName(name)).thenReturn(Optional.empty());

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            boosterPackService.deleteBoosterPack(name);
        });
    }
}
