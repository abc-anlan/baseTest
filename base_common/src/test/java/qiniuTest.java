import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.junit.Test;
import sun.misc.Unsafe;

public class qiniuTest {

    @Test
    public void test(){
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
//...其他参数参考类注释

        UploadManager uploadManager = new UploadManager(cfg);
//...生成上传凭证，然后准备上传
        String accessKey = "5Wl7a19u-hiH4LWDlK61VivjUj7M-z2_rh_GPCPp";
        String secretKey = "n2BBeQRNV5Sa3Bu6VSh6lj-OJLLOoiXtDqdFHuSR";
        String bucket = "20200929basedemo";
//如果是Windows情况下，格式是 D:\\qiniu\\test.png
        String localFilePath = "C:\\Users\\Administrator\\Desktop\\图片\\QQ图片20200924095618.jpg";
//默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;

        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);

        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }

    }


    @Test
    public void test_del(){
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
//...其他参数参考类注释

        String accessKey = "5Wl7a19u-hiH4LWDlK61VivjUj7M-z2_rh_GPCPp";
        String secretKey = "n2BBeQRNV5Sa3Bu6VSh6lj-OJLLOoiXtDqdFHuSR";
        String bucket = "20200929basedemo";
        String key = "FojP2diTT5t26g-HRr_EA8G7xuZK";

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

    @Test
    public void test3(){
        Integer a=1;
        Integer b=2;
        Long g=3L;
        System.out.println(a==b);
        System.out.println(g==(a+b));
    }

    @Test
    public void test4(){

        Unsafe unsafe1 = Unsafe.getUnsafe();

    }
}
