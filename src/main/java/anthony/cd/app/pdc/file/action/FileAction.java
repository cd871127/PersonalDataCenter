package anthony.cd.app.pdc.file.action;

import anthony.cd.app.pdc.common.util.SystemConst;
import anthony.cd.app.pdc.common.util.hash.MD5;
import anthony.cd.app.pdc.file.dto.FileDTO;
import anthony.cd.app.pdc.file.mapper.FileMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class FileAction {

    @Resource
    private FileMapper fileMapper;

    @Value("${pdc.user-file-dir}")
    private String fileDir;

    public List<FileDTO> uploadFile(List<MultipartFile> files, String userName) {
        List<FileDTO> successList = new ArrayList<>();
        List<FileDTO> failedList = new ArrayList<>();

        Set<String> fileIdSet = new HashSet<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (MultipartFile multipartFile : files) {
            FileDTO fileDTO = null;
            try {
                //生成fileID,查找fileID,判断是否需要transferTo
                String fileId = MD5.byteToMD5String(multipartFile.getBytes());
                if (fileIdSet.contains(fileId))
                    continue;
                fileIdSet.add(fileId);
                Map<String, String> paraMap = new HashMap<>();
                paraMap.put("fileId", fileId);
                fileDTO = fileMapper.findFile(paraMap);
                if (null == fileDTO) {
                    String path = SystemConst.FILE_PATH + "/" + sdf.format(new Date(System.currentTimeMillis())) + "/";
                    File dir = new File(path);

                    if (!dir.exists()) {
                        if (!dir.mkdir()) {
                            throw new IOException();
                        }
                    }
                    fileDTO = new FileDTO(multipartFile);
                    fileDTO.setFileId(fileId);
                    fileDTO.setServerPath(path);
                    multipartFile.transferTo(new File(path + multipartFile.getOriginalFilename()));
                }
                if (!userName.equals(fileDTO.getUserName())) {
                    fileDTO.setUserName(userName);
                    successList.add(fileDTO);
                }
            } catch (IOException e) {
                e.printStackTrace();
                if (fileDTO == null) {
                    fileDTO = new FileDTO(multipartFile);
                    failedList.add(fileDTO);
                }
            }
        }
        if (!successList.isEmpty())
            fileMapper.addFiles(successList);
        return failedList;
    }

    public FileDTO downloadFile(String userName, String fileId) {
        Map<String, String> paraMap = new HashMap<>();
        paraMap.put("userName", userName);
        paraMap.put("fileId", fileId);
        return fileMapper.findFile(paraMap);
    }

    public boolean deleteFile(String userName, String fileId) {
        Map<String, String> paraMap = new HashMap<>();
        paraMap.put("userName", userName);
        paraMap.put("fileId", fileId);
        // 查询fileDTO
        FileDTO fileDTO = fileMapper.findFile(paraMap);
        if (fileDTO == null)
            return false;
        // 删除用户下对应的数据
        if (!fileMapper.deleteFile(paraMap))
            return false;
        // 查询fileID是否还存在,如果存在,直接返回,
        paraMap.remove("userName");
        if (!fileMapper.findFileList(paraMap).isEmpty())
            return true;
        // 如果不存在,删除文件后返回
        File file = new File(fileDTO.getServerPath() + fileDTO.getFileName() + fileDTO.getPostfix());
        return file.exists() && file.isFile() && file.delete();
    }

    public List<FileDTO> queryAllUserFiles(String userName) {
        Map<String,String> paraMap=new HashMap<>();
        paraMap.put("userName",userName);
        return fileMapper.findFileList(paraMap);
    }
}
