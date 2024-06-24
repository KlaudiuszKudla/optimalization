package org.example.enity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Individual {

    private static List<Product> products;
    private static List<Provider> providers;
    public List<Integer> sequenceOfProviders;
    public static int size;
    private int uniqueNumberCounter = 0;
    private int maxOccurences = 0;
    public Float cost;
    private Map<String, List<String>> basket;

    public Individual(List<Product> products, List<Provider> providers) {
        this.products = products;
        this.providers = providers;
        this.size = products.size();
        this.sequenceOfProviders = new ArrayList<>();
    }

    public Individual() {
    }

    public Individual(List<Integer> sequenceOfProviders) {
        this.sequenceOfProviders = sequenceOfProviders;
    }

    public void generateRandomSolution(){
        Random random = new Random();
        for(int i = 0; i < size; i++){
            sequenceOfProviders.add(random.nextInt(providers.size()));
        }
        repairSolution();
        calculateCost();
    }

    public void repairSolution() {
        for (int i = 0; i < size; i++) {
            int providerNumber = sequenceOfProviders.get(i);
            Provider provider = providers.get(providerNumber);
            Product item = products.get(i);
            if (!isSolutionValid(item, provider)) {
                sequenceOfProviders.set(i, findRandomProviderNumber(item));
            }
        }
    }

    public void repairSolutionV2() {
        for (int i = 0; i < size; i++) {
            int providerNumber = sequenceOfProviders.get(i);
            Provider provider = providers.get(providerNumber);
            Product item = products.get(i);
            if (!isSolutionValid(item, provider)) {
                sequenceOfProviders.set(i, findBestProvider(item));
            }
        }
    }

    public void generateGreedySequenceOfProviders() {
        for (int i = 0; i < size; i++) {
            Product product = products.get(i);
            int providerNumber = findBestProvider(product);
            sequenceOfProviders.add(providerNumber);
        }
        calculateCost();
    }

    public void calculateCost() {
        Float cost = 0.0F;
        List<Integer> newSequenceOfProviders = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Product product = products.get(i);
            Integer providerId = sequenceOfProviders.get(i);
            Provider provider = providers.get(providerId);
            if (!newSequenceOfProviders.contains(providerId)) {
                cost += provider.getInitialCost();
            }
            cost += provider.getUnitCostForProduct(product);
            newSequenceOfProviders.add(providerId);
        }
        this.cost = cost;
    }

    public void swapMutationV2() {
        var random = new Random();
        var firstIndex = random.nextInt(size);
        var secondIndex = random.nextInt(size);
        while (firstIndex == secondIndex) {
            secondIndex = random.nextInt(size);
        }
        var firstCity = this.sequenceOfProviders.get(firstIndex);
        sequenceOfProviders.set(firstIndex, sequenceOfProviders.get(secondIndex));
        sequenceOfProviders.set(secondIndex, firstCity);
        repairSolution();
        calculateCost();
    }

    public Individual inversionMutation(){
        var random = new Random();
        var firstIndex = random.nextInt(size);
        var secondIndex = random.nextInt(size);
        while(firstIndex == secondIndex){
            secondIndex = random.nextInt(size);
        }
        Individual mutant = new Individual();
        List<Integer> subsequence = new ArrayList<>(sequenceOfProviders);
        for (int i = firstIndex; i <= secondIndex; i++) {
            subsequence.set(i, sequenceOfProviders.get(secondIndex - (i - firstIndex)));
        }
        mutant.setSequenceOfProviders(subsequence);
        mutant.repairSolutionV2();
        mutant.calculateCost();
        return mutant;
    }

    private int findBestProvider(Product product) {
        Float curentCost = Float.MAX_VALUE;
        Provider bestProvider = null;
        for (Provider provider : product.getProviders()) {
            Float cost = 0.0F;
            int id = provider.getId();
            if (!sequenceOfProviders.contains(id)) {
                cost += provider.getInitialCost();
            }
            cost += provider.getUnitCostForProduct(product);
            if (cost < curentCost) {
                curentCost = cost;
                bestProvider = provider;
            }
        }
        return bestProvider.getId();
    }

    private int findRandomProviderNumber(Product product) {
        Random random = new Random();
        int productProvidersSize =  product.getProviders().size();
        var rand = random.nextInt(productProvidersSize);
        Integer providerNumber = product.getProviders().get(rand).getId();
        return providerNumber;
    }

    public void createBasket(){
        basket = new HashMap<>();
        for (int i = 0; i < size; i++) {
            int providerNumber = sequenceOfProviders.get(i);
            Provider provider = providers.get(providerNumber);
            Product item = products.get(i);
            List<String> itemList = basket.getOrDefault(provider.getName(), new ArrayList<>());
            itemList.add(item.getName());
            basket.put(provider.getName(), itemList);
        }
    }

    private boolean isSolutionValid(Product product, Provider provider) {
        return product.getProviders().contains(provider);
    }

    public List<Integer> getSequenceOfProviders() {
        return sequenceOfProviders;
    }

    public Float getCost() {
        return cost;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Provider> getProviders() {
        return providers;
    }

    public int getMaxOccurences() {
        return maxOccurences;
    }

    public int getUniqueNumberCounter() {
        return uniqueNumberCounter;
    }

    public Map<String, List<String>> getBasket() {
        return basket;
    }

    public void setSequenceOfProviders(List<Integer> sequenceOfProviders) {
        this.sequenceOfProviders = sequenceOfProviders;
    }

    //    public static void setProducts(List<Product> products) {
//        Individual.products = products;
//    }
//
//    public static void setProviders(List<Provider> providers) {
//        Individual.providers = providers;
//    }

    @JsonIgnore
    public StaticFields getStaticFields() {
        return new StaticFields(products, providers);
    }

//    @JsonIgnore
//    public void setStaticFields(StaticFields staticFields) {
//        Individual.products = staticFields.products;
//        Individual.providers = staticFields.providers;
//    }

    public static class StaticFields {
        @JsonProperty("products")
        public List<Product> products;

        @JsonProperty("providers")
        public List<Provider> providers;

        public StaticFields() {
        }

        public StaticFields(List<Product> products, List<Provider> providers) {
            this.products = products;
            this.providers = providers;
        }
    }

    @Override
    public String toString() {
        return "Individual{" +
                "products=" + products +
                ", providers=" + providers +
                '}';
    }
}
