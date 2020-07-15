package com.vmall.util;

import com.vmall.FastDFSFile;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
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
    static {
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
    public static String[] upload(FastDFSFile fastDFSFile) throws Exception {
        //附加参数
        NameValuePair[] meta_list = new NameValuePair[1];
        meta_list[0] = new NameValuePair("author", fastDFSFile.getAuthor());

        //获取Tracker Server
        TrackerServer trackerServer = getTrackerServer();

        //获取StorageClient
        StorageClient storageClient = getStorageClient(trackerServer);

        //通过storage client访问storage，实现文件上传，并且获取文件上传后的存储信息
        /***
         * upload[]
         *       upload[0]:文件上传所存储的Storage的组名字   ex：group1
         *       upload[1]:文件存储到Storage上的文件名字     ex: M00/02/14/imm.jpg
         */
        String[] uploads = storageClient.upload_file(fastDFSFile.getContent(), fastDFSFile.getExt(), meta_list);
        return uploads;
    }

    /***
     * 获取文件信息
     * @param groupName
     * @param remoteFileName
     * @return
     * @throws Exception
     */
    public static FileInfo getFile(String groupName, String remoteFileName) throws Exception {
        //获取Tracker Server
        TrackerServer trackerServer = getTrackerServer();

        //获取StorageClient
        StorageClient storageClient = getStorageClient(trackerServer);

        //获取文件信息
        return storageClient.get_file_info(groupName, remoteFileName);
    }

    /***
     * 文件下载
     * @param groupName
     * @param remoteFileName
     * @return
     * @throws IOException
     * @throws MyException
     */
    public static InputStream downloadFile(String groupName, String remoteFileName) throws IOException, MyException {
        //获取Tracker Server
        TrackerServer trackerServer = getTrackerServer();

        //获取StorageClient
        StorageClient storageClient = getStorageClient(trackerServer);

        //文件下载
        byte[] buffer = storageClient.download_file(groupName, remoteFileName);
        return new ByteArrayInputStream(buffer);
    }

    /***
     * 删除文件
     * @param groupName
     * @param remoteFileName
     * @throws IOException
     * @throws MyException
     */
    public static void deleteFile(String groupName, String remoteFileName) throws IOException, MyException {
        //获取Tracker Server
        TrackerServer trackerServer = getTrackerServer();

        //获取StorageClient
        StorageClient storageClient = getStorageClient(trackerServer);

        //删除文件
        storageClient.delete_file(groupName, remoteFileName );
    }

    /***
     * 获取storage信息
     * @return
     * @throws IOException
     */
    public static StorageServer getStorages() throws IOException {
        //创建tracker client 对象，通过对象访问tracker server
        TrackerClient trackerClient = new TrackerClient();
        //通过tracker client获取tracker server的链接对象
        TrackerServer trackerServer = trackerClient.getConnection();
        //获取Storage信息
        return trackerClient.getStoreStorage(trackerServer);
    }

    /***
     * 获取Storage的IP和端口信息
     * @param groupName
     * @param remoteFileName
     * @return
     * @throws IOException
     */
    public static ServerInfo[] getServerInfo(String groupName, String remoteFileName) throws IOException {
        //创建tracker client 对象，通过对象访问tracker server
        TrackerClient trackerClient = new TrackerClient();
        //通过tracker client获取tracker server的链接对象
        TrackerServer trackerServer = trackerClient.getConnection();

        return trackerClient.getFetchStorages(trackerServer, groupName, remoteFileName);
    }

    /***
     * 获取tracker url地址
     * @return
     * @throws IOException
     */
    public static String getTrackerUrl() throws IOException {
        //获取tracker server
        TrackerServer trackerServer = getTrackerServer();
        //Tracker IP,port
        String ip = trackerServer.getInetSocketAddress().getHostString();
        int tracker_http_port = ClientGlobal.getG_tracker_http_port();
        String url = "http://" + ip + ":" + tracker_http_port;
        return url;
    }

    /***
     * 获取tracker server
     * @return
     * @throws IOException
     */
    public static TrackerServer getTrackerServer() throws IOException {
        //创建tracker client 对象，通过对象访问tracker server
        TrackerClient trackerClient = new TrackerClient();
        //通过tracker client获取tracker server的链接对象
        TrackerServer trackerServer = trackerClient.getConnection();

        return trackerServer;
    }

    /***
     * 获取StorageCLient
     * @param trackerServer
     * @return
     */
    public static StorageClient getStorageClient(TrackerServer trackerServer){
        //通过tracker server的链接信息可以获取storage的链接信息，创建storage client对象存储storage的链接信息
        StorageClient storageClient = new StorageClient(trackerServer, null);
        return storageClient;
    }

    public static void main(String[] args) throws Exception {
        /*FileInfo fileInfo = getFile("group1", "M00/00/00/wKjThF8NgOOAVfEOAAD1L0WD6ls495.jpg");
        System.out.println(fileInfo.getSourceIpAddr());
        System.out.println(fileInfo.getFileSize());*/

        /*//文件下载
        InputStream inputStream = downloadFile("group1", "M00/00/00/wKjThF8NgOOAVfEOAAD1L0WD6ls495.jpg");
        //写入本地磁盘
        FileOutputStream outputStream = new FileOutputStream("D:/1.jpg");

        //定义一个缓冲区
        byte[] buffer = new byte[1024];
        while(inputStream.read(buffer) != -1){
            outputStream.write(buffer);
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();*/
        //deleteFile("group1", "M00/00/00/wKjThF8NgOOAVfEOAAD1L0WD6ls495.jpg");

        //获取storage信息
        /*StorageServer storageServer = getStorages();
        System.out.println(storageServer.getStorePathIndex());
        System.out.println(storageServer.getInetSocketAddress().getHostString());*/

        /*ServerInfo[] groups = getServerInfo("group1", "wKjThF1E95SAZkDVAAnAAJuzIB4821.jpg");
        for(ServerInfo group : groups){
            System.out.println(group.getIpAddr());
            System.out.println(group.getPort());
        }*/

        //获取tracker信息
        System.out.println(getTrackerUrl());
    }
}
