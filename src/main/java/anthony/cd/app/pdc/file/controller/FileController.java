package anthony.cd.app.pdc.file.controller;

import anthony.cd.app.pdc.common.util.ServerResponse;
import anthony.cd.app.pdc.common.util.SystemConst;
import anthony.cd.app.pdc.file.action.FileAction;
import anthony.cd.app.pdc.file.dto.FileDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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
import java.util.List;

import static anthony.cd.app.pdc.common.util.SystemConst.RequestResult.*;

@RestController
@RequestMapping("/file")
public class FileController {
    @Resource
    private FileAction fileAction;

//    @RequestMapping(value = "{userName}", method = RequestMethod.GET)
//    ServerResponse downloadFile(@PathVariable String userName, @PathVariable String fileId) {
//        return null;
//    }

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

    @RequestMapping(value = "{userName}", method = RequestMethod.POST)
    ServerResponse<List<FileDTO>> uploadFile(@PathVariable String userName, HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        if (files.isEmpty()) {
            return new ServerResponse<>(OPERATION_FAILED);
        }
        for (MultipartFile multipartFile : files) {
            if (multipartFile.getSize() > 1048576)
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


//    @RequestMapping(value = "{userName}/{fileId}", method = RequestMethod.PUT)
//    ServerResponse updateFile(@PathVariable String userName, @PathVariable String fileId) {
//        return null;
//    }

    @RequestMapping(value = "{userName}/{fileId}", method = RequestMethod.DELETE)
    ServerResponse deleteFile(@PathVariable String userName, @PathVariable String fileId) {
        if (fileAction.deleteFile(userName, fileId))
            return new ServerResponse(SUCCESS);
        else
            return new ServerResponse(OPERATION_FAILED);
    }
}
