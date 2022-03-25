package com.mscheckout.checkout.services.service;

import com.mscheckout.checkout.form.PurchaseForm;

public interface RabbitmqService {

    String send(PurchaseForm purchaseForm) throws Exception;

}
