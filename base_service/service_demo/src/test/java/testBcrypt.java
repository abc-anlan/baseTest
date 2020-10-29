import org.springframework.security.crypto.bcrypt.BCrypt;

public class testBcrypt {
    public static void main(String[] args) {
        //测试bcrypt加密算法

        //获取盐
        String gensalt = BCrypt.gensalt();
        System.out.println("盐="+gensalt);

        //基于盐对密码进行加密
        String hashpw = BCrypt.hashpw("123456", gensalt);
        System.out.println("密文密码="+hashpw);

        //解析密文
        boolean checkpw = BCrypt.checkpw("123456", hashpw);
        System.out.println(checkpw);
    }
}
