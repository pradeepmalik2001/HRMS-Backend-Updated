package com.ahom.hrms.serviceimpl;

import com.ahom.hrms.Repository.EventNameRepository;
import com.ahom.hrms.dto.EventNameDto;
import com.ahom.hrms.entities.EventName;
import com.ahom.hrms.service.EventNameService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventNameServiceImpl implements EventNameService {

    @Autowired
    EventNameRepository eventNameRepository;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public EventName eventNameDtoToEventName(EventNameDto eventNameDto) {

        EventName eventName = this.modelMapper.map(eventNameDto, EventName.class);
        return eventName;
    }

    @Override
    public EventNameDto eventNameToEventNameDto(EventName eventName) {
        EventNameDto eventNameDto = this.modelMapper.map(eventName, EventNameDto.class);
        return eventNameDto;
    }

    @Override
    public void saveEventName(EventNameDto eventNameDto) {
        eventNameRepository.save(eventNameDtoToEventName(eventNameDto));
    }

    //fetch
    @Override
    public List<EventNameDto> getAll() {
        List list=eventNameRepository.findAll();
        return list;
    }

}
