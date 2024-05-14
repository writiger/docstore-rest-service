package com.ds.file.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.ds.common.domain.R;
import com.ds.file.utils.FileUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author writiger
 * @description
 * @create_at 2024-05-13 15:52
 */
@Controller
@Api(tags = "文件上传接口")
@RestController
@RequestMapping("/file")
public class FileController {
    @Value("${pro.uploadPath}")
    private String UPLOAD_FOLDER;

    @ApiOperation("上传文档接口")
    @PostMapping(value = "/doc")
    public R<Void> uploadDoc(@RequestParam("file") MultipartFile file,String uuid){
        try {
            if (file.isEmpty()) {
                return R.error("file is empty");
            }
            // 获取文件的名称
            String originalFilename = file.getOriginalFilename();
            // 文件后缀 例如：.png
            String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            // 根路径，在 resources/uploads
            String basePath = ResourceUtils.getURL("classpath:").getPath()+UPLOAD_FOLDER;
            // 新的文件名，使用uuid生成文件名
            String fileName = uuid + fileSuffix;
            // 创建新的文件夹
            File fileExist = new File(basePath);
            // 文件夹不存在，则新建
            if (!fileExist.exists()) {
                fileExist.mkdirs();
            }
            // 获取文件对象
            File newFile = new File(basePath, fileName);
            // 完成文件的上传
            file.transferTo(newFile);
            // 返回绝对路径
            return R.ok();
        } catch (Exception e) {

            e.printStackTrace();
        }
        return R.error("上传失败");
    }

    @ApiOperation("下载文件接口")
    @PostMapping("/download")
    public void download(String uuid,String suffix,HttpServletResponse response) {
        try {
            String path = ResourceUtils.getURL("classpath:").getPath()+UPLOAD_FOLDER;
            // 获取文件名
            // path是指想要下载的文件的路径
            File file = new File(path+"/"+uuid+suffix);
            // 将文件写入输入流
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStream fis = new BufferedInputStream(fileInputStream);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();

            // 清空response
            response.reset();
            // 设置response的Header
            response.setCharacterEncoding("UTF-8");
            //Content-Disposition的作用：告知浏览器以何种方式显示响应返回的文件，用浏览器打开还是以附件的形式下载到本地保存
            //attachment表示以附件方式下载   inline表示在线打开   "Content-Disposition: inline; filename=文件名.mp3"
            // filename表示文件的默认名称，因为网络传输只支持URL编码的相关支付，因此需要将文件名URL编码后进行传输,前端收到后需要反编码才能获取到真正的名称
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(uuid+"."+suffix, "UTF-8"));
            // 告知浏览器文件的大小
            response.addHeader("Content-Length", "" + file.length());
            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            outputStream.write(buffer);
            outputStream.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
