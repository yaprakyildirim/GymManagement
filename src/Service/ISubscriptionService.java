package Service;

import Entity.CustomerEntity;
import Entity.SubscriptionEntity;

import java.util.ArrayList;

public interface ISubscriptionService {
    SubscriptionEntity saveSubscription(SubscriptionEntity _subscription);
    SubscriptionEntity getSubscriptionById(int _id);
    ArrayList<SubscriptionEntity> getAllSubscriptions();
    ArrayList<SubscriptionEntity> getCustomerSubscription(CustomerEntity _customer);
    boolean deleteSubscription(SubscriptionEntity _trainer);
    ArrayList<SubscriptionEntity> convertStringListToEntityList(ArrayList<String> _strList);
    ArrayList<String> convertEntityListToStringList(ArrayList<SubscriptionEntity> _subscriptionList);
}
