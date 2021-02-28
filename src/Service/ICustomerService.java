package Service;

import Entity.CustomerEntity;
import Payment.IPayStrategy;

import java.util.ArrayList;

public interface ICustomerService {
    CustomerEntity getCustomerById(int _id);
    CustomerEntity saveCustomer(CustomerEntity _customer);
    ArrayList<CustomerEntity> getAllCustomers();
    boolean deleteCustomer(CustomerEntity _customer);
    void makePayment(CustomerEntity _customer, int _amount, IPayStrategy _paymentGate);
    ArrayList<CustomerEntity> convertStringListToEntityList(ArrayList<String> _strList);
    ArrayList<String> convertEntityListToStringList(ArrayList<CustomerEntity> _customerList);
}
