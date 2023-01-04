package com.ahom.hrms.serviceimpl;

import java.io.IOException;
import java.util.Optional;

import com.ahom.hrms.Repository.ImageDataRepo;
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

	@Override
    public String uploadImage(MultipartFile file) throws IOException {

        ImageData imageData = dataRepo.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(Imageutils.compressImage(file.getBytes())).build());
        if (imageData != null) {
            return "file uploaded successfully : " + file.getOriginalFilename();
        }
        return null;
    }

	@Override
    public byte[] downloadImage(String fileName){
        Optional<ImageData> dbImageData = dataRepo.findByName(fileName);
        byte[] images=Imageutils.decompressImage(dbImageData.get().getImageData());
        return images;
    }

}
