package org.example.enity;

import java.util.ArrayList;
import java.util.List;

public class Product implements Comparable<Product> {

    private String name;
    private static int counter = 0;
    private int id;
    private List<Provider> providers;

    public Product(String name) {
        this.name = name;
        this.providers = new ArrayList<>();
        this.id = counter++;
    }

    public Product() {
    }

    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addProvider(Provider provider) {
        providers.add(provider);
    }

    public List<Provider> getProviders() {
        return providers;
    }

    @Override
    public String toString() {
        return "Product{name='" + name + "'}";
    }

    @Override
    public int compareTo(Product other) {
        return Integer.compare(this.id, other.id);
    }
}
