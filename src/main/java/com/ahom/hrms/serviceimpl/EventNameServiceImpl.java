package com.ahom.hrms.serviceimpl;

import com.ahom.hrms.Repository.EventNameRepository;
import com.ahom.hrms.dto.EventNameDto;
import com.ahom.hrms.entities.EventName;
import com.ahom.hrms.exception.CustomException;
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
    public EventNameDto saveEventName(EventNameDto eventNameDto)
    {
        EventName eventName=eventNameRepository.findByName(eventNameDto.getName());
        if(eventName==null)
        {
            eventNameRepository.save(eventNameDtoToEventName(eventNameDto));
        }
        else {
            throw new RuntimeException("Event Already Exist");
        }
        return eventNameDto;
    }

    //fetch
    @Override
    public List<EventNameDto> getAll() {
        List list=eventNameRepository.findAll();
        return list;
    }

    @Override
    public EventName deleteEvent(int id)
    {
        EventName eventName=eventNameRepository.findById(id).orElse(null);
        if(eventName!=null)
        {
            eventNameRepository.deleteById(id);
        }
        else
        {
            throw new CustomException("Id Not found");
        }
        return eventName;
    }

    @Override
    public EventName updateEvent(EventName eventName, int id)
    {
        EventName eventName1=eventNameRepository.findById(id).orElse(null);
        if(eventName1!=null)
        {
            eventName1.setName(eventName.getName());
            eventName1.setDescription(eventName.getDescription());
            eventNameRepository.save(eventName1);
        }
        return eventName1;
    }

}
