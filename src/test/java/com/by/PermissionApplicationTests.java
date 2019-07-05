package com.by;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PermissionApplicationTests {

    @Test
    public void contextLoads() {
         //ByteSource credentialsSalt = ByteSource.Util.bytes("柯凡");
        String md5 = String.valueOf(new SimpleHash("MD5", "kf","柯凡" , 3));
        System.out.println(md5);
    }

}
