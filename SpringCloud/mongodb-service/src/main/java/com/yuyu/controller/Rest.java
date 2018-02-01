package com.yuyu.controller;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import com.mongodb.gridfs.GridFSInputFile;
import com.yuyu.bo.UserInfo;
import com.yuyu.dao.UserMongoRepository;
import com.yuyu.utils.MD5CaculateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;

@RestController
public class Rest {

    private static final Logger logger = LoggerFactory.getLogger(Rest.class);
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private UserMongoRepository repository;

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public void postUserInfo(UserInfo user) {
        this.repository.save(user);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public void getUserInfo(Long id) {
        UserInfo user = this.repository.findUserById(id);

        System.out.println(user);
    }

    @RequestMapping(value = "/upload/{folder}", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadDoc(@PathVariable("folder") String folder, @RequestParam("file") MultipartFile mutiFile) {

        try {
            String filename = mutiFile.getOriginalFilename();
            test(mutiFile);
            InputStream inputStream = new ByteArrayInputStream(mutiFile.getBytes());
            inputStream.mark(0);
            String md5Id = MD5CaculateUtil.getHash(inputStream, "MD5");
            inputStream.reset();
            GridFS gridFS = new GridFS(this.mongoTemplate.getDb());


            DBObject query = new BasicDBObject("md5", md5Id);
            GridFSDBFile gridFSDBFile = gridFS.findOne(query);

            test(gridFSDBFile);

            if (gridFSDBFile != null) {
                logger.info(filename+" already exists in the database!");
                return;
            }
            GridFSInputFile gfFile = gridFS.createFile(inputStream);
            gfFile.setFilename(filename);
//            gfFile.setId(md5Id);
            gfFile.save();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * 验证文件类型
     * @param file
     * @throws IOException
     */
    private void test(MultipartFile file) throws IOException {
        System.out.println("获取文件MIME类型="+file.getContentType());
       System.out.println("获取表单中文件组件的名字="+file.getName());
        System.out.println("获取上传文件的原名="+file.getOriginalFilename());
        System.out.println("获取文件的字节大小，单位byte="+file.getSize());
        System.out.println("是否为空="+file.isEmpty());
//        file.transferTo(new File("e:/tmp/"+ file.getOriginalFilename()));
    }
    private void test(GridFSDBFile gridFSDBFile) throws IOException {
        if(gridFSDBFile==null)
            return;
        System.out.println("获取文件MIME类型="+gridFSDBFile.getContentType());
        System.out.println("getFilename="+gridFSDBFile.getFilename());
        System.out.println("getAliases="+gridFSDBFile.getAliases());
        System.out.println("getUploadDate，单位byte="+gridFSDBFile.getUploadDate());
    }
}
