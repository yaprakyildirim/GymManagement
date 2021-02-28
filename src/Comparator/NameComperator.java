package Comparator;

import Entity.CustomerEntity;

import java.util.Comparator;

public class NameComperator implements Comparator {
    @Override
    public int compare(Object o, Object t1) {
        CustomerEntity customer = (CustomerEntity)o;
        CustomerEntity customer2 = (CustomerEntity)t1;
        return customer.getName().compareTo(customer2.getName());
    }
}
