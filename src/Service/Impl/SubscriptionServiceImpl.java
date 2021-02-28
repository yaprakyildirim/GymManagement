package Service.Impl;

import Entity.CustomerEntity;
import Entity.SubscriptionEntity;
import Repository.IDataAccess;
import Service.ISubscriptionService;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SubscriptionServiceImpl implements ISubscriptionService {
    private IDataAccess repository;

    public SubscriptionServiceImpl(IDataAccess repository) {
        this.repository = repository;
    }

    @Override
    public SubscriptionEntity saveSubscription(SubscriptionEntity _subscription) {
        ArrayList<SubscriptionEntity> subscriptionEntityArrayList = this.convertStringListToEntityList(this.repository.getAll());
        boolean isUpdated = false;
        for (SubscriptionEntity subscription : subscriptionEntityArrayList){
            if(subscription.getId() == _subscription.getId()){
                subscription.setEndDate(_subscription.getEndDate());
                subscription.setCustomerId(_subscription.getCustomerId());
                subscription.setTrainerId(_subscription.getTrainerId());
                subscription.setExerciseId(_subscription.getExerciseId());
                isUpdated = true;
            }
        }
        if(!isUpdated){
            int id = subscriptionEntityArrayList.size() == 0 ? 1 : (subscriptionEntityArrayList.get(subscriptionEntityArrayList.size() - 1).getId() + 1);
            _subscription.setId(id);
            subscriptionEntityArrayList.add(_subscription);
        }
        this.repository.save(this.convertEntityListToStringList(subscriptionEntityArrayList));
        return _subscription;
    }

    @Override
    public SubscriptionEntity getSubscriptionById(int _id) {
        ArrayList<SubscriptionEntity> subscriptionsAsList = this.convertStringListToEntityList(this.repository.getById(_id));
        if(subscriptionsAsList.isEmpty()){
            return null;
        }
        return subscriptionsAsList.get(0);
    }

    @Override
    public ArrayList<SubscriptionEntity> getAllSubscriptions() {
        return this.convertStringListToEntityList(
                this.repository.getAll()
        );
    }

    @Override
    public ArrayList<SubscriptionEntity> getCustomerSubscription(CustomerEntity _subscription) {
        ArrayList<SubscriptionEntity> allSubscriptions = this.getAllSubscriptions();
        ArrayList<SubscriptionEntity> customerSubscriptions = new ArrayList<>();
        for(SubscriptionEntity subscriptionEntity : allSubscriptions){
            if(subscriptionEntity.getCustomerId() == _subscription.getId()){
                customerSubscriptions.add(subscriptionEntity);
            }
        }
        return customerSubscriptions;
    }

    @Override
    public boolean deleteSubscription(SubscriptionEntity _subscription) {
        return this.repository.delete(_subscription.getId());
    }

    @Override
    public ArrayList<SubscriptionEntity> convertStringListToEntityList(ArrayList<String> _strList) {
        ArrayList<SubscriptionEntity> subscriptionList = new ArrayList<SubscriptionEntity>();
        for (String item : _strList) {
            SubscriptionEntity subscription = null;
                subscription = new SubscriptionEntity(
                        Integer.parseInt(item.split("\t")[0]),
                        new Date(Long.parseLong(item.split("\t")[1])),
                        Integer.parseInt(item.split("\t")[2]),
                        Integer.parseInt(item.split("\t")[3]),
                        Integer.parseInt(item.split("\t")[4])
                );
            subscriptionList.add(subscription);
        }
        return subscriptionList;
    }

    @Override
    public ArrayList<String> convertEntityListToStringList(ArrayList<SubscriptionEntity> _subscriptionList) {
        ArrayList<String> strList = new ArrayList<String>();
        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        for (SubscriptionEntity subscription : _subscriptionList) {
            strList.add(
                    subscription.getId() + "\t" +
                            subscription.getEndDate().getTime() + "\t"+
                            subscription.getCustomerId() + "\t"+
                            subscription.getTrainerId() + "\t"+
                            subscription.getExerciseId() + "\n"
            );
        }
        return strList;
    }
}
