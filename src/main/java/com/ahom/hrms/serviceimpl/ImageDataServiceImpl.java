package com.ahom.hrms.serviceimpl;

import java.io.IOException;
import java.util.Optional;

import com.ahom.hrms.Repository.EmployeeRepository;
import com.ahom.hrms.Repository.ImageDataRepo;
import com.ahom.hrms.entities.Employee;
import com.ahom.hrms.entities.ImageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



import com.ahom.hrms.service.ImageDataService;
import com.ahom.hrms.util.Imageutils;


@Service
public class ImageDataServiceImpl implements ImageDataService {
	
	@Autowired
    ImageDataRepo dataRepo;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public String uploadImage(MultipartFile file,String id) throws IOException {
        Employee employee=employeeRepository.findById(id).orElse(null);
        if (employee!=null) {
            ImageData imageData = dataRepo.save(ImageData.builder()
                    .name(file.getOriginalFilename())
                    .type(file.getContentType())
                    .employeeId(employee.getId())
                    .imageData(Imageutils.compressImage(file.getBytes())).build());
            return "file uploaded successfully : " + file.getOriginalFilename();
        }else throw new RuntimeException("error");
    }

	@Override
    public byte[] downloadImage(String employeeId){
        Optional<ImageData> dbImageData = Optional.ofNullable(dataRepo.findByEmployeeId(employeeId));
        return Imageutils.decompressImage(dbImageData.get().getImageData());
    }

}
