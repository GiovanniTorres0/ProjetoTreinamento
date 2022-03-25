package com.mscheckout.checkout.services.service;

import com.mscheckout.checkout.form.PurchaseForm;

public interface RabbitmqService {

    void send(PurchaseForm purchaseForm) throws Exception;

}
