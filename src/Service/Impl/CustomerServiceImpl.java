package Service.Impl;

import Entity.CustomerEntity;
import Payment.IPayStrategy;
import Repository.IDataAccess;
import Service.ICustomerService;
import Service.IPersonService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class CustomerServiceImpl implements ICustomerService, IPersonService {
    private IDataAccess repository;

    public CustomerServiceImpl(IDataAccess _repository) {
        this.repository = _repository;
    }

    @Override
    public CustomerEntity getCustomerById(int _id) {
        ArrayList<CustomerEntity> customerAsList = this.convertStringListToEntityList(this.repository.getById(_id));
        if(customerAsList.isEmpty()){
            return null;
        }
        return customerAsList.get(0);
    }

    @Override
    public CustomerEntity saveCustomer(CustomerEntity _customer) {
        ArrayList<String> txtList = repository.getAll();
        ArrayList<CustomerEntity> customerList = this.convertStringListToEntityList(txtList);
        boolean isUpdated = false;
        for (CustomerEntity customer : customerList) {
            if (customer.getId() == _customer.getId()) {
                customer.setName(_customer.getName());
                customer.setSurname(_customer.getSurname());
                customer.setPhoneNumber(_customer.getPhoneNumber());
                customer.setPassword(_customer.getPassword());
                customer.setStatus(_customer.isStatus());
                customer.setAddress(_customer.getAddress());
                customer.setEmail(_customer.getEmail());
                customer.setPayAmount(_customer.getPayAmount());
                customer.setPayMethodCode(_customer.getPayMethodCode());
                isUpdated = true;
            }
        }
        if (!isUpdated) {
            int id = customerList.size() == 0 ? 1 : (customerList.get(customerList.size() - 1).getId() + 1);
            _customer.setId(id);
            customerList.add(_customer);
        }
        this.repository.save(this.convertEntityListToStringList(customerList));
        return _customer;
    }

    @Override
    public ArrayList<CustomerEntity> getAllCustomers() {
        if (this.repository != null) {
            ArrayList<String> customerList = this.repository.getAll();
            if (!customerList.isEmpty()) {
                return this.convertStringListToEntityList(customerList);
            }
            return null;
        }
        return null;
    }

    @Override
    public boolean deleteCustomer(CustomerEntity _customer) {
        return this.repository.delete(_customer.getId());
    }

    @Override
    public void makePayment(CustomerEntity _customer, int _amount, IPayStrategy _paymentGate) {
        _customer.setPayMethodCode(_paymentGate.pay(_amount));
        _customer.setPayAmount(_amount);
    }

    @Override
    public ArrayList<CustomerEntity> convertStringListToEntityList(ArrayList<String> _strList) {
        ArrayList<CustomerEntity> customerList = new ArrayList<CustomerEntity>();
        for (String item : _strList) {
            CustomerEntity customer = new CustomerEntity(
                    Integer.parseInt(item.split("\t")[0]),
                    item.split("\t")[1],
                    item.split("\t")[2],
                    item.split("\t")[3],
                    item.split("\t")[4],
                    Boolean.parseBoolean(item.split("\t")[5]),
                    item.split("\t")[6],
                    item.split("\t")[7],
                    Integer.parseInt(item.split("\t")[8]),
                    item.split("\t")[9]
            );
            customerList.add(customer);
        }
        return customerList;
    }

    @Override
    public ArrayList<String> convertEntityListToStringList(ArrayList<CustomerEntity> _customerList) {
        ArrayList<String> strList = new ArrayList<String>();
        for (CustomerEntity customer : _customerList) {
            strList.add(
                    customer.getId() + "\t" +
                            customer.getName() + "\t" +
                            customer.getSurname() + "\t" +
                            customer.getPhoneNumber() + "\t" +
                            customer.getPassword() + "\t" +
                            customer.isStatus() + "\t" +
                            customer.getAddress() + "\t" +
                            customer.getEmail() + "\t" +
                            customer.getPayAmount() + "\t"+
                            customer.getPayMethodCode() + "\n"
            );
        }
        return strList;
    }

    @Override
    public Object login(String _name, String _password) {
        for(CustomerEntity customer:this.getAllCustomers()){
            if(customer.getName().equals(_name) && customer.getPassword().equals(_password)){
                return customer;
            }
        }
        return null;
    }
}
