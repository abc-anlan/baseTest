package util;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

public class qiniuUtil {

    public static String  accessKey = "5Wl7a19u-hiH4LWDlK61VivjUj7M-z2_rh_GPCPp";
    public static String  secretKey = "n2BBeQRNV5Sa3Bu6VSh6lj-OJLLOoiXtDqdFHuSR";
    public static String  bucket = "20200929basedemo";

    public static void upload1(String filePath,String fileName){
        //构造一个指定的zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
        UploadManager uploadManager = new UploadManager(cfg);
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(filePath, fileName, upToken);
            //解析上传成功结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        } catch (QiniuException e) {
            Response response = e.response;
            try {
                System.err.println(response.bodyString());
            } catch (QiniuException qiniuException) {
                //ignore
            }
        }
    }

    public static void upload2(byte[] bytes,String fileName){
        //构造一个指定的zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
        UploadManager uploadManager = new UploadManager(cfg);
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key=fileName;
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(bytes, key, upToken);
            //解析上传成功结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException e) {
            Response response = e.response;
            try {
                System.err.println(response.bodyString());
            } catch (QiniuException qiniuException) {
                //ignore
            }
        }
    }

    public static void deleteFileFormQiNiu(String fileName){
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());

        String key = fileName;

        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucket, key);
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }
    }
}
