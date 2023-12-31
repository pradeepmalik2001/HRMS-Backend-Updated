package com.ahom.hrms.serviceimpl;

import com.ahom.hrms.Repository.CreateLeaveRequestRepository;
import com.ahom.hrms.Repository.EmployeeRepository;
import com.ahom.hrms.Repository.LeaveRecordRepository;
import com.ahom.hrms.dto.CreateLeaveRequestDto;
import com.ahom.hrms.entities.*;
import com.ahom.hrms.exception.CustomException;
import com.ahom.hrms.service.CreateLeaveRequestService;
import com.ahom.hrms.service.LeaveRecordService;
import com.ahom.hrms.service.Notification1Service;
import com.ahom.hrms.service.NotificationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class CreateLeaveRequestServiceImpl implements CreateLeaveRequestService{

	@Autowired
	CreateLeaveRequestRepository createLeaveRequestRepository;

	@Autowired
	ModelMapper modelMapper;
	@Autowired
	LeaveRecordService leaveRecordService;

	@Autowired
	JavaMailSender mailSender;
	@Value("${mail.from}")
	private String fromEmail;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	LeaveRecordRepository leaveRecordRepository;
	@Autowired
	NotificationService notificationService;

	@Autowired
	Notification1Service notification1Service;



	@Override
	public CreateLeaveRequest saveCreateLeaveRequest(CreateLeaveRequest createLeaveRequest) throws ParseException {
		LocalDate localDate=LocalDate.now();
		DateTimeFormatter formatter1=DateTimeFormatter.ofPattern("MMMM yyyy");
		String month= localDate.format(formatter1);
		Employee employee = employeeRepository.findById(createLeaveRequest.getId()).orElse(null);
		LeaveRecord leaveRecord1=leaveRecordRepository.findByEmployeeIdAndLeaveRecordOfMonth(createLeaveRequest.getId(),month);

		CreateLeaveRequest createLeaveRequest1=createLeaveRequestRepository.findByEmployeeIdAndStartDateAndEndDate(createLeaveRequest.getId(),createLeaveRequest.getStartDate(), createLeaveRequest.getEndDate());

		Notification notification=new Notification();

		if (employee!=null) {
			if (createLeaveRequest1!=null){
				throw new RuntimeException("Leave request already present");
			}
			LocalDate currentDate = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String startDateString = createLeaveRequest.getStartDate();
			String endDateString = createLeaveRequest.getEndDate();
			LocalDate startDate = LocalDate.parse(startDateString, formatter);
			LocalDate endDate = LocalDate.parse(endDateString, formatter);

			long daysInBetween= ChronoUnit.DAYS.between(startDate,endDate);
			long finalDays = daysInBetween + 1;
			System.out.println("daysInBetwwnnnnnnn : "+daysInBetween);
			System.out.println("finalDaysssss : "+finalDays);

			LocalDate startDate1= LocalDate.parse(createLeaveRequest.getStartDate());
			LocalDate endDate1=LocalDate.parse(createLeaveRequest.getEndDate());

			while(!startDate1.isAfter(endDate1)) {
				if (currentDate.isEqual(startDate) || startDate.isAfter(currentDate)) {
					if (endDate.isAfter(startDate) || endDate.isAfter(currentDate) || endDate.isEqual(startDate)) {
						createLeaveRequest.setEmail(employee.getUsername());
						createLeaveRequest.setEmployeeId(createLeaveRequest.getId());
						createLeaveRequest.setStatus("3");
						createLeaveRequest.setNoOfDays(finalDays);
						createLeaveRequest.setLeaveRecord(leaveRecord1);
						createLeaveRequest.setDate(startDate1);
						notification.setMessage("New leave request from Employee: " + createLeaveRequest.getEmail());
						notification.setStatus(true);
						notificationService.saveNotification(notification);
						createLeaveRequestRepository.save(createLeaveRequest);
						startDate1=startDate1.plusDays(1);
					} else {
						throw new CustomException("End date cannot be earlier than current date or before start date ");
					}

				} else {
					throw new RuntimeException("Start date cannot be earlier than the current date");
				}
			}
		}

		else {
			throw new RuntimeException("no employee");
		}
		return createLeaveRequest;
	}

	@Override
	public List<CreateLeaveRequestDto> getAllCreateLeaveRequest(){
		List<CreateLeaveRequest> listCreateLeaveRequest= this.createLeaveRequestRepository.findAll();
		return listCreateLeaveRequest.stream().map(this::createLeaveRequesttoCreateLeaveRequestdto).collect(Collectors.toList());
	}


	@Override
	public CreateLeaveRequestDto updateCreateLeaveRequest(CreateLeaveRequestDto createLeaveRequestDto,String id) {
		CreateLeaveRequest createLeaveRequest=createLeaveRequestRepository.findById(id).orElse(null);

		Notification1 notification1=new Notification1();

		if(createLeaveRequest!=null)
		{
			createLeaveRequest.setStatus(createLeaveRequestDto.getStatus());
			createLeaveRequestRepository.saveAndFlush(createLeaveRequest);
			if(createLeaveRequestDto.getStatus().equals("1"))
			{
				try {
					MimeMessage message=mailSender.createMimeMessage();
					MimeMessageHelper messageToEmployee=new MimeMessageHelper(message);
					messageToEmployee.setFrom(fromEmail);
					messageToEmployee.setTo(createLeaveRequest.getEmail());
					messageToEmployee.setSubject("Response Against Leave Request");
					messageToEmployee.setText("Request for leave");
					mailSender.send(message);
					System.out.println("message");
					notification1.setMessage("Hurrah! "+createLeaveRequest.getSelectEmployee()+" your leave has been approved");
					notification1.setStatus(true);
					notification1Service.saveNotification(notification1);

//					LocalDate localDate=LocalDate.now();
//
//					int currentMonth = localDate.getMonthValue();
//					int currentYear = localDate.getYear();
//
//					YearMonth yearMonth = YearMonth.of(currentYear, currentMonth);
//
//					String startDate1= createLeaveRequest.getStartDate();
//					String lastDayOfMonth= String.valueOf(yearMonth.lengthOfMonth());
//
//					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//					LocalDate startDate = LocalDate.parse(startDate1, formatter);
//					LocalDate endDate = LocalDate.parse(lastDayOfMonth, formatter);
//
//					long daysInBetween= ChronoUnit.DAYS.between(startDate,endDate);
//					long difference = daysInBetween + 1;

					LocalDate date=LocalDate.now();
					DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("MMMM yyyy");
					String format = date.format(dateTimeFormatter);
					LeaveRecord leaveRecord1=leaveRecordRepository.findById(createLeaveRequest.getLeaveRecord().getLeaveId()).orElse(null);

//					if(difference>createLeaveRequest.getNoOfDays()) {
					if (leaveRecord1==null)
					{
						throw new RuntimeException("");
					}else
						if (createLeaveRequest.getNoOfDays() >= createLeaveRequest.getLeaveRecord().getTotalLeave()) {
//							LeaveRecord leaveRecord1 = new LeaveRecord();


							leaveRecord1.setLeaveId(leaveRecord1.getLeaveId());
							leaveRecord1.setEmployeeId(createLeaveRequest.getLeaveRecord().getEmployeeId());
							leaveRecord1.setEmployeeName(createLeaveRequest.getLeaveRecord().getEmployeeName());
							leaveRecord1.setLop(1 - createLeaveRequest.getLeaveRecord().getTotalLeave() + createLeaveRequest.getLeaveRecord().getLop());
							leaveRecord1.setTotalLeave(0);
							leaveRecord1.setLeaveLeft(0);
							leaveRecord1.setLeaveRecordOfMonth(format);
							leaveRecord1.setLeaveTaken(1 + createLeaveRequest.getLeaveRecord().getLeaveTaken());
							leaveRecordService.updateLeaveRecord(leaveRecord1,createLeaveRequest.getLeaveRecord().getLeaveId());
							createLeaveRequestDto.setLeaveRecord(leaveRecord1);
						} else {

							leaveRecord1.setEmployeeId(createLeaveRequest.getLeaveRecord().getEmployeeId());
							leaveRecord1.setEmployeeName(createLeaveRequest.getLeaveRecord().getEmployeeName());
							leaveRecord1.setLop(0);
							leaveRecord1.setTotalLeave(createLeaveRequest.getLeaveRecord().getTotalLeave() - createLeaveRequest.getNoOfDays());
							leaveRecord1.setLeaveLeft(createLeaveRequest.getLeaveRecord().getTotalLeave() - createLeaveRequest.getNoOfDays());
							leaveRecord1.setLeaveTaken(1);
							leaveRecordRepository.save(leaveRecord1);
							createLeaveRequestDto.setLeaveRecord(leaveRecord1);
						}
//					}
//					else
//					{
//						if (createLeaveRequest.getNoOfDays() >= createLeaveRequest.getLeaveRecord().getTotalLeave())
//						{
//
//							leaveRecord1.setEmployeeId(createLeaveRequest.getLeaveRecord().getEmployeeId());
//							leaveRecord1.setEmployeeName(createLeaveRequest.getLeaveRecord().getEmployeeName());
//							leaveRecord1.setLop(createLeaveRequest.getNoOfDays() - createLeaveRequest.getLeaveRecord().getTotalLeave() + createLeaveRequest.getLeaveRecord().getLop());
//							leaveRecord1.setTotalLeave(0);
//							leaveRecord1.setLeaveLeft(0);
//							leaveRecord1.setLeaveTaken(createLeaveRequest.getNoOfDays() + createLeaveRequest.getLeaveRecord().getLeaveTaken());
//							leaveRecordRepository.save(leaveRecord1);
//							createLeaveRequestDto.setLeaveRecord(leaveRecord1);
//						} else {
//
//							leaveRecord1.setEmployeeId(createLeaveRequest.getLeaveRecord().getEmployeeId());
//							leaveRecord1.setEmployeeName(createLeaveRequest.getLeaveRecord().getEmployeeName());
//							leaveRecord1.setLop(0);
//							leaveRecord1.setTotalLeave(createLeaveRequest.getLeaveRecord().getTotalLeave() - createLeaveRequest.getNoOfDays());
//							leaveRecord1.setLeaveLeft(createLeaveRequest.getLeaveRecord().getTotalLeave() - createLeaveRequest.getNoOfDays());
//							leaveRecord1.setLeaveTaken(createLeaveRequest.getNoOfDays());
//							leaveRecordRepository.save(leaveRecord1);
//							createLeaveRequestDto.setLeaveRecord(leaveRecord1);
//						}
//					}
				}catch (RuntimeException e) {
					e.printStackTrace();
				} catch (MessagingException e) {
					throw new RuntimeException(e);
				}
//				catch (MessagingException e) {
//					throw new RuntimeException(e);
//				}
			}
			else if (createLeaveRequestDto.getStatus().equals("2"))
			{
				try {
					MimeMessage message=mailSender.createMimeMessage();
					MimeMessageHelper helper=new MimeMessageHelper(message);
					helper.setFrom(fromEmail);
					helper.setTo(createLeaveRequest.getEmail());
					helper.setSubject("Response Against Leave Request");
					helper.setText("Request For Leave");

					mailSender.send(message);
					System.out.println(message);
					notification1.setMessage("Alas "+createLeaveRequest.getSelectEmployee()+" your leave has been Rejected");
					notification1.setStatus(true);
					notification1Service.saveNotification(notification1);
				}catch (RuntimeException e)
				{
					e.printStackTrace();
				} catch (MessagingException e) {
					throw new RuntimeException(e);
				}
			}
			else {
				return null;
			}
		}
		else {
			throw new RuntimeException("Create Leave Not Found for Id : "+id);
		}
		return createLeaveRequestDto;
	}

	public CreateLeaveRequest createLeaveRequestdtotoCreateLeaveRequest(CreateLeaveRequestDto createLeaveRequestDto) {
				return this.modelMapper.map(createLeaveRequestDto,CreateLeaveRequest.class);
	}

	public CreateLeaveRequestDto createLeaveRequesttoCreateLeaveRequestdto(CreateLeaveRequest createLeaveRequest)
	{
		return this.modelMapper.map(createLeaveRequest,CreateLeaveRequestDto.class);
	}

	public double countApprovedLeave(String employeeId, String month) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH);
		Date startDate, endDate;

		month = month.toUpperCase();
		startDate = dateFormat.parse(month + " " + Calendar.getInstance().get(Calendar.YEAR));
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		endDate = calendar.getTime();

		DateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String endDateString = outputDateFormat.format(endDate);
		String startdateString = outputDateFormat.format(startDate);

		String status1 = "1";
		List<CreateLeaveRequest> list = createLeaveRequestRepository.findByEmployeeIdAndStartDateAndEndDateAndStatus(employeeId,startdateString, endDateString,status1);
		return list.size();
	}

	public List<CreateLeaveRequest> ApprovedLeave(String employeeId, String month) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH);
		Date startDate, endDate;

		month = month.toUpperCase();
		startDate = dateFormat.parse(month + " " + Calendar.getInstance().get(Calendar.YEAR));
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		endDate = calendar.getTime();

		DateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String endDateString = outputDateFormat.format(endDate);
		String startdateString = outputDateFormat.format(startDate);

		String status1 = "1";
		List<CreateLeaveRequest> list = createLeaveRequestRepository.findByEmployeeIdAndStartDateAndEndDateAndStatus(employeeId,startdateString, endDateString,status1);
		return list;
	}

}
