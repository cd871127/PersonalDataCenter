package anthony.cd.app.pdc.file.controller;

import anthony.cd.app.pdc.common.util.ServerResponse;
import anthony.cd.app.pdc.common.util.SystemConst;
import anthony.cd.app.pdc.file.action.FileAction;
import anthony.cd.app.pdc.file.dto.FileDTO;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static anthony.cd.app.pdc.common.util.SystemConst.RequestResult.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/file")
@CrossOrigin(origins = "http://localhost:3000", methods = {GET, POST, DELETE})
public class FileController {
    @Resource
    private FileAction fileAction;


    @RequestMapping(value = "{userName}/{fileId}", method = RequestMethod.GET)
    void downloadFiles(@PathVariable String userName, @PathVariable String fileId, HttpServletResponse response) throws UnsupportedEncodingException {
        FileDTO fileDTO = fileAction.downloadFile(userName, fileId);
        byte[] bytes = new byte[1];
        ServletOutputStream outputStream = null;
        FileInputStream fis = null;
        String fileName = fileDTO.getFileName() + fileDTO.getPostfix();
        fileName = new String(fileName.getBytes(SystemConst.CHARSET), "ISO8859-1");
        response.setContentType("application/force-download");
        response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
        try {
            outputStream = response.getOutputStream();
            fis = new FileInputStream(new File(fileDTO.getServerPath() + fileDTO.getFileName() + fileDTO.getPostfix()));
            while (-1 != fis.read(bytes)) {
                outputStream.write(bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null)
                    outputStream.close();
                if (fis != null)
                    fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping(value = "{userName}", method = POST)
    ServerResponse<List<FileDTO>> uploadFile(@PathVariable String userName, HttpServletRequest request) {
        List<MultipartFile> files = new ArrayList<>();
        MultiValueMap<String, MultipartFile> filesMap = ((MultipartHttpServletRequest) request).getMultiFileMap();
        files.addAll(filesMap.toSingleValueMap().values());
        if (files.isEmpty()) {
            return new ServerResponse<>(OPERATION_FAILED);
        }
        for (MultipartFile multipartFile : files) {
            System.out.println(multipartFile.getSize());
            if (multipartFile.getSize() > 10485760)
                return new ServerResponse<>(FILE_TOO_LARGE);
            if ("".equals(multipartFile.getOriginalFilename()))
                return new ServerResponse<>(EMPTY_FILE_NAME);
        }

        List<FileDTO> failedList = fileAction.uploadFile(files, userName);
        if (failedList.isEmpty())
            return new ServerResponse<>(SUCCESS);
        ServerResponse<List<FileDTO>> serverResponse = new ServerResponse<>(UPLOAD_FAILED);
        serverResponse.setData(failedList);

        return serverResponse;
    }


    @RequestMapping(value = "{userName}", method = GET)
    ServerResponse<List<FileDTO>> queryAllUserFiles(@PathVariable String userName) {
        ServerResponse<List<FileDTO>> serverResponse = new ServerResponse<>(SUCCESS);
        List<FileDTO> fileList = fileAction.queryAllUserFiles(userName);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        DecimalFormat df = new DecimalFormat("#0.00");
        for (FileDTO fileDTO : fileList) {
            fileDTO.setReadableCreatedDate(sdf.format(fileDTO.getCreatedDate()));
            fileDTO.setReadableFileSize(df.format((double) fileDTO.getFileSize() / (1024 )) + "KB");
        }
        serverResponse.setData(fileList);
        return serverResponse;
    }


    @RequestMapping(value = "{userName}/{fileId}", method = DELETE)
    ServerResponse deleteFile(@PathVariable String userName, @PathVariable String fileId) {
        if (fileAction.deleteFile(userName, fileId))
            return new ServerResponse(SUCCESS);
        else
            return new ServerResponse(OPERATION_FAILED);
    }
}
