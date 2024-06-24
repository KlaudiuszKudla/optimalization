package org.example.enity;

import java.util.HashMap;
import java.util.Map;

public class Provider implements Comparable<Provider> {


    private static int counter = 0;
    private int id;
    private String name;
    private Float initialCost;
    private Map<Product, Float> productCosts;

    public Provider(String name, Float initialCost) {
        this.id = counter++;
        this.name = name;
        this.initialCost = initialCost;
        this.productCosts = new HashMap<>();
    }

    public Provider() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInitialCost(Float initialCost) {
        this.initialCost = initialCost;
    }

    public Float getInitialCost() {
        return initialCost;
    }

    public Float getUnitCostForProduct(Product product) {
        return productCosts.get(product);
    }

    public void addProductCost(Product product, Float unitCost) {
        productCosts.put(product, unitCost);
    }

    public boolean canDeliver(Product product) {
        return productCosts.containsKey(product);
    }

    public Map<Product, Float> getProductCosts() {
        return productCosts;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Provider{id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", initialCost=").append(initialCost);
        sb.append(", productCosts={");
        for (Map.Entry<Product, Float> entry : productCosts.entrySet()) {
            sb.append(entry.getKey()).append(": ").append(entry.getValue()).append(", ");
        }
        sb.append("}}");
        return sb.toString();
    }
    @Override
    public int compareTo(Provider other) {
        return Integer.compare(this.id, other.id);
    }
}
