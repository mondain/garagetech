package com.lewdlistings.entity;

import org.joda.time.DateTime;

public interface Auditable {

    public DateTime getCreated();
    public void setCreated(DateTime created);

    public DateTime getUpdated();
    public void setUpdated(DateTime updated);
}