package org.example.factory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.example.enity.Individual;
import org.example.enity.Product;
import org.example.enity.Provider;
import org.example.json.IndividualData;
import org.example.json.ProductCostData;
import org.example.json.ProductData;
import org.example.json.ProviderData;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ObjectFactory {

    public static Individual individual;

    public ObjectFactory() {
        if (individual == null) {
            createIndividualV3();
        }
    }

    public  Individual createIndividual() {
        // Tworzenie dostawców
        Provider providerA = new Provider("Provider A", 17F);
        Provider providerB = new Provider("Provider B", 15F);
        Provider providerC = new Provider("Provider C", 13F);
        Provider providerD = new Provider("Provider D", 20F);
        Provider providerE = new Provider("Provider E", 14F);
        Provider providerF = new Provider("Provider F", 18F);
        Provider providerG = new Provider("Provider G", 16F);

        // Tworzenie produktów
        Product product1 = new Product("Product 1");
        Product product2 = new Product("Product 2");
        Product product3 = new Product("Product 3");
        Product product4 = new Product("Product 4");
        Product product5 = new Product("Product 5");
        Product product6 = new Product("Product 6");
        Product product7 = new Product("Product 7");
        Product product8 = new Product("Product 8");
        Product product9 = new Product("Product 9");
        Product product10 = new Product("Product 10");

        // Przypisanie kosztów dostawy do produktów
        providerA.addProductCost(product1, 17F);
        providerA.addProductCost(product2, 8F);
        providerA.addProductCost(product3, 15F);
        providerA.addProductCost(product4, 10F);
        providerA.addProductCost(product5, 11F);

        providerB.addProductCost(product1, 16F);
        providerB.addProductCost(product2, 12F);
        providerB.addProductCost(product4, 9F);
        providerB.addProductCost(product6, 20F);

        providerC.addProductCost(product3, 17F);
        providerC.addProductCost(product7, 13F);
        providerC.addProductCost(product8, 14F);

        providerD.addProductCost(product4, 19F);
        providerD.addProductCost(product5, 21F);
        providerD.addProductCost(product9, 15F);

        providerE.addProductCost(product5, 14F);
        providerE.addProductCost(product6, 12F);
        providerE.addProductCost(product10, 18F);

        providerF.addProductCost(product7, 11F);
        providerF.addProductCost(product8, 16F);
        providerF.addProductCost(product9, 17F);

        providerG.addProductCost(product9, 13F);
        providerG.addProductCost(product10, 12F);

        // Dodanie dostawców do produktów
        product1.addProvider(providerA);
        product1.addProvider(providerB);

        product2.addProvider(providerA);
        product2.addProvider(providerB);

        product3.addProvider(providerA);
        product3.addProvider(providerC);

        product4.addProvider(providerA);
        product4.addProvider(providerB);
        product4.addProvider(providerD);

        product5.addProvider(providerA);
        product5.addProvider(providerD);
        product5.addProvider(providerE);

        product6.addProvider(providerB);
        product6.addProvider(providerE);

        product7.addProvider(providerC);
        product7.addProvider(providerF);

        product8.addProvider(providerC);
        product8.addProvider(providerF);

        product9.addProvider(providerD);
        product9.addProvider(providerF);
        product9.addProvider(providerG);

        product10.addProvider(providerE);
        product10.addProvider(providerG);

        // Lista produktów
        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        productList.add(product3);
        productList.add(product4);
        productList.add(product5);
        productList.add(product6);
        productList.add(product7);
        productList.add(product8);
        productList.add(product9);
        productList.add(product10);

        // Lista dostawców
        List<Provider> providerList = new ArrayList<>();
        providerList.add(providerA);
        providerList.add(providerB);
        providerList.add(providerC);
        providerList.add(providerD);
        providerList.add(providerE);
        providerList.add(providerF);
        providerList.add(providerG);

        // Tworzenie obiektu Individual
        Individual individual = new Individual(productList, providerList);
        System.out.println(productList);
        System.out.println(providerList);
        return individual;
    }

    public Individual createIndividualV2() {
        // Tworzenie dostawców
        Provider providerA = new Provider("Provider A", 17F);
        Provider providerB = new Provider("Provider B", 15F);
        Provider providerC = new Provider("Provider C", 13F);
        Provider providerD = new Provider("Provider D", 20F);
        Provider providerE = new Provider("Provider E", 14F);
        Provider providerF = new Provider("Provider F", 18F);
        Provider providerG = new Provider("Provider G", 16F);
        Provider providerH = new Provider("Provider H", 19F);
        Provider providerI = new Provider("Provider I", 21F);

        List<Provider> providers = new ArrayList<>();
        providers.add(providerA);
        providers.add(providerB);
        providers.add(providerC);
        providers.add(providerD);
        providers.add(providerE);
        providers.add(providerF);
        providers.add(providerG);
        providers.add(providerH);
        providers.add(providerI);

        // Tworzenie produktów
        List<Product> products = new ArrayList<>();
        for (int i = 1; i <= 60; i++) {
            products.add(new Product("Product " + i));
        }

        // Przypisanie kosztów dostawy do produktów
        Random rand = new Random();
        for (Provider provider : providers) {
            for (Product product : products) {
                float cost = 10F + rand.nextFloat() * 20F; // Koszt między 10 a 30
                provider.addProductCost(product, cost);
            }
        }

        // Dodanie dostawców do produktów
        for (Product product : products) {
            int numProviders = 2 + rand.nextInt(4); // Każdy produkt ma 2-5 dostawców
            List<Provider> shuffledProviders = new ArrayList<>(providers);
            java.util.Collections.shuffle(shuffledProviders);
            for (int i = 0; i < numProviders; i++) {
                product.addProvider(shuffledProviders.get(i));
            }
        }

        // Lista dostawców
        List<Provider> providerList = new ArrayList<>(providers);

        // Tworzenie obiektu Individual
        Individual individual = new Individual(products, providerList);
        return individual;
    }

    public static void createIndividualV3() {
        // Tworzenie dostawców
        Provider providerA = new Provider("Provider A", 50F);
        Provider providerB = new Provider("Provider B", 45F);
        Provider providerC = new Provider("Provider C", 40F);
        Provider providerD = new Provider("Provider D", 55F);
        Provider providerE = new Provider("Provider E", 60F);
        Provider providerF = new Provider("Provider F", 35F);
        Provider providerG = new Provider("Provider G", 42F);
        Provider providerH = new Provider("Provider H", 48F);
        Provider providerI = new Provider("Provider I", 52F);
        Provider providerJ = new Provider("Provider J", 57F);
        Provider providerK = new Provider("Provider K", 38F);
        Provider providerL = new Provider("Provider L", 49F);
        Provider providerM = new Provider("Provider M", 44F);
        Provider providerN = new Provider("Provider N", 53F);
        Provider providerO = new Provider("Provider O", 46F);
        Provider providerP = new Provider("Provider P", 41F);
        Provider providerQ = new Provider("Provider Q", 56F);
        Provider providerR = new Provider("Provider R", 39F);
        Provider providerS = new Provider("Provider S", 54F);
        Provider providerT = new Provider("Provider T", 47F);
        Provider providerU = new Provider("Provider U", 58F);
        Provider providerV = new Provider("Provider V", 43F);
        Provider providerW = new Provider("Provider W", 51F);
        Provider providerX = new Provider("Provider X", 37F);
        Provider providerY = new Provider("Provider Y", 59F);
        Provider providerZ = new Provider("Provider Z", 36F);

        List<Provider> providers = new ArrayList<>();
        providers.add(providerA);
        providers.add(providerB);
        providers.add(providerC);
        providers.add(providerD);
        providers.add(providerE);
        providers.add(providerF);
        providers.add(providerG);
        providers.add(providerH);
        providers.add(providerI);
        providers.add(providerJ);
        providers.add(providerK);
        providers.add(providerL);
        providers.add(providerM);
        providers.add(providerN);
        providers.add(providerO);
        providers.add(providerP);
        providers.add(providerQ);
        providers.add(providerR);
        providers.add(providerS);
        providers.add(providerT);
        providers.add(providerU);
        providers.add(providerV);
        providers.add(providerW);
        providers.add(providerX);
        providers.add(providerY);
        providers.add(providerZ);


        // Tworzenie produktów
        List<Product> products = new ArrayList<>();
        for (int i = 1; i <= 200; i++) {
            products.add(new Product("Product " + i));
        }

        // Przypisanie kosztów dostawy do produktów
        Random rand = new Random(1000); // Ustawienie ziarna na 1000
        for (Provider provider : providers) {
            for (Product product : products) {
                float cost = 20F + rand.nextFloat() * 20F; // Koszt między 10 a 110
                provider.addProductCost(product, cost);
            }
        }

        // Dodanie dostawców do produktów
        for (Product product : products) {
            int numProviders = 8 + rand.nextInt(9); // Każdy produkt ma 3-6 dostawców
            List<Provider> shuffledProviders = new ArrayList<>(providers);
            Collections.shuffle(shuffledProviders, rand); // Użycie tego samego obiektu Random
            for (int i = 0; i < numProviders; i++) {
                product.addProvider(shuffledProviders.get(i));
            }
        }

        // Tworzenie obiektu Individual
        Individual individual = new Individual(products, providers);

        // Zapis do pliku JSON
        saveIndividualToFile(individual, "entryData.json");
        System.out.println(individual);

        setIndividual(individual);
    }




    public static void saveIndividualToFile(Individual individual, String filename) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
            File directory = new File("src/main/resources/20to200");
            if (!directory.exists()) {
                directory.mkdir();
            }
            File outputFile = new File(directory, filename);
            // Zapisz obiekt Individual wraz z jego polami statycznymi
            Individual.StaticFields staticFields = individual.getStaticFields();
            mapper.writeValue(outputFile, staticFields);
            System.out.println("Dane zostały zapisane do pliku " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Individual loadIndividualFromFile(String filename) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File inputFile = new File("output", filename);
            // Odczytaj pola statyczne
            Individual.StaticFields staticFields = mapper.readValue(inputFile, Individual.StaticFields.class);
            Individual individual = new Individual(staticFields.products, staticFields.providers);
            return individual;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Individual createIndividualFromJson(String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        // Deserialize the JSON into the IndividualData class
        IndividualData individualData = mapper.readValue(new File(path), IndividualData.class);

        // Convert IndividualData to Individual
        List<Provider> providers = new ArrayList<>();
        for (ProviderData providerData : individualData.providers) {
            Provider provider = new Provider(providerData.name, providerData.baseCost);
            for (ProductCostData productCostData : providerData.productCosts) {
                provider.addProductCost(new Product(productCostData.productName), productCostData.cost);
            }
            providers.add(provider);
        }

        List<Product> products = new ArrayList<>();
        for (ProductData productData : individualData.products) {
            Product product = new Product(productData.name);
            for (String providerName : productData.providers) {
                for (Provider provider : providers) {
                    if (provider.getName().equals(providerName)) {
                        product.addProvider(provider);
                    }
                }
            }
            products.add(product);
        }
//      products.forEach(product -> System.out.println(product.getName()));
//        providers.forEach(provider -> System.out.println(provider.getName() + provider.ge));
        System.out.println(products);
        System.out.println(providers);

        return new Individual(products, providers);
    }

    public static Individual getIndividual() {
        if (individual == null) {
            createIndividualV3();
        }
        return individual;
    }

    public static void setIndividual(Individual individual) {
        ObjectFactory.individual = individual;
    }
}
