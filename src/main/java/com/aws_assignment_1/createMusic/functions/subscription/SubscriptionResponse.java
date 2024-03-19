package com.aws_assignment_1.createMusic.functions.subscription;

import com.aws_assignment_1.createMusic.model.Subscription;

import java.util.ArrayList;
import java.util.List;

public class SubscriptionResponse {

    private List<Subscription> subs = new ArrayList<>();

    public List<Subscription> getSubs() {
        return subs;
    }

    public void setSubs(final List<Subscription> subs) {
        this.subs = subs;
    }
}