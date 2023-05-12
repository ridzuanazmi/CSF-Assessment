package ibf2022.batch2.csf.backend.services;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ibf2022.batch2.csf.backend.models.Archive;
import ibf2022.batch2.csf.backend.repositories.ArchiveRepository;
import ibf2022.batch2.csf.backend.repositories.ImageRepository;

@Service
public class UploadService {

    @Autowired
    private ImageRepository imgRepo;

    @Autowired
    private ArchiveRepository archiveRepo;

    public Archive upload(MultipartFile zipFile, String title, String name, String comments) throws IOException {
        return this.imgRepo.upload(zipFile, title, name, comments);
    }

    public void recordBundle(Archive bundle) {
        this.archiveRepo.recordBundle(bundle);
    }

    public Optional<Archive> getBundleByBundleId(String bundleId) {
        try {
            return archiveRepo.getBundleByBundleId(bundleId);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
