package com.ahom.hrms.controller;

import java.util.ArrayList;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ahom.hrms.entities.Approval;
import com.ahom.hrms.entities.Request;

@RestController
@RequestMapping("/api")
public class ApprovalController {
    private List<Request> requests = new ArrayList<>();
    private List<Approval> approvals = new ArrayList<>();
    private long requestCounter = 1;
    private long approvalCounter = 1;

    @PostMapping("/submit-request")
    public Request submitRequest(@RequestBody Request request){
        request.setId(requestCounter++);
        request.setStatus("pending");
        requests.add(request);
        return request;
    }

    @PostMapping("/approve-request/{requestId}")
    public Approval approveRequest(@PathVariable long requestId, @RequestBody Approval approval) {
        approval.setId(approvalCounter++);
        approval.setRequestId(requestId);
        approvals.add(approval);
     
            Request request = requests.stream()
            .filter(r -> r.getId() == requestId)
            .findFirst()
            .orElse(null);

        if(request != null){
            request.setStatus("approved");
        }
        return approval;
    }
}
