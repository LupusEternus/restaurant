package service.impl;

import domain.eto.Storage;
import org.junit.jupiter.api.BeforeEach;

public class MenuStorageServiceImplTest implements TestsMethods{

    MenuStorageServiceImpl menuStorageService;

    @BeforeEach
    public void setUp(){

        menuStorageService = new MenuStorageServiceImpl();
        Storage.CommonStorage.addToStorage(createChicken(),10);
        Storage.CommonStorage.addToStorage(createEgg(),10);
        Storage.CommonStorage.addToStorage(createVegetable("Onion"),10);
        Storage.CommonStorage.addToStorage(createVegetable("Tomato"),10);

    }





}
