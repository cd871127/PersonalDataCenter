package anthony.cd.app.pdc.file.mapper;

import anthony.cd.app.pdc.file.dto.FileDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface FileMapper {

    int addFiles(List<FileDTO> files);

    FileDTO findFile(Map<String, String> paraMap);

    List<FileDTO> findFileList(Map<String, String> paraMap);


    boolean deleteFile(Map<String, String> paraMap);
}
