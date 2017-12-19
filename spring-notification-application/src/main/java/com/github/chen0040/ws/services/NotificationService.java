package com.github.chen0040.ws.services;

import com.github.chen0040.ws.models.AuditEvent;

public interface NotificationService {
    AuditEvent process(AuditEvent alarm);
}
