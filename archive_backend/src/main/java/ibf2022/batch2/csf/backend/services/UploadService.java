package ibf2022.batch2.csf.backend.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ibf2022.batch2.csf.backend.repositories.ImageRepository;

@Service
public class UploadService {
    
    @Autowired
    private ImageRepository imgRepo;

    public void upload(MultipartFile zipFile) throws IOException {
        this.imgRepo.upload(zipFile);
    }
}
