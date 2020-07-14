package com.vmall.util;

import com.vmall.FastDFSFile;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
/*
 * @ClassName: FastDFSUtil
 * @Description: 实现FastDFS文件管理， 文件上传，删除，下载，文件信息获取，storage信息获取，tracker信息获取
 * @Author: Se7en
 * @Date: 2020/7/14 4:39
 * @Version: 1.0
 */

public class FastDFSUtil {

    /***
     * 加载Tracker链接信息
     */
    static{
        try {
            //查找classpath下文件路径
            String filename = new ClassPathResource("fdfs_client.conf").getPath();
            //加载tracker链接信息
            ClientGlobal.init(filename);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    /***
     * 文件上传
     * @param fastDFSFile
     * @return String[]
     */
    public static String[] upload(FastDFSFile fastDFSFile) throws Exception{
        //附加参数
        NameValuePair[] meta_list = new NameValuePair[1];
        meta_list[0] = new NameValuePair("author", fastDFSFile.getAuthor());

        //创建一个tracker访问的客户端对象tracker client
        TrackerClient trackerClient = new TrackerClient();

        //通过tracker client访问tracker server服务，获取链接信息
        TrackerServer trackerServer = trackerClient.getConnection();

        //通过tracker server的链接信息可以获取storage的链接信息，创建storage client对象存储storage的链接信息
        StorageClient storageClient = new StorageClient(trackerServer, null);

        //通过storage client访问storage，实现文件上传，并且获取文件上传后的存储信息
        /***
         * upload[]
         *       upload[0]:文件上传所存储的Storage的组名字   ex：group1
         *       upload[1]:文件存储到Storage上的文件名字     ex: M00/02/14/imm.jpg
         */
        String[] uploads = storageClient.upload_file(fastDFSFile.getContent(), fastDFSFile.getExt(), meta_list);
        return uploads;
    }
}
