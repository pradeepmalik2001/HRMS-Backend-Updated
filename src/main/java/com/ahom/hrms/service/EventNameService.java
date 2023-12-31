package com.ahom.hrms.service;

import com.ahom.hrms.dto.EventNameDto;
import com.ahom.hrms.entities.EventName;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EventNameService {


    EventName eventNameDtoToEventName(EventNameDto eventNameDto);

    EventNameDto eventNameToEventNameDto(EventName eventName);

    EventNameDto saveEventName(EventNameDto eventNameDto);

    //fetch
    List<EventNameDto> getAll();

    EventName deleteEvent(int id);

    EventName updateEvent(EventName eventName,int id);
}
