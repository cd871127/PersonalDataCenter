package io.github.cd871127.pdc.file.mapper;

import io.github.cd871127.pdc.file.dto.FileDTO;
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
