package co.fy.gateway.server.user.web;

import co.fy.gateway.server.user.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

/**
 * CREATED IN  2017-11-27 上午11:04
 *
 * @AUTHOR: 姜坤
 **/
public class d {
    public static void main(String[] args){
        //密码生成策略
        String pass = "96e79218965eb72c92a549dd5a330112";
        BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
        String hashPass = encode.encode(pass);
        String hashPass1 = encode.encode(pass);
        String hashPass3 = encode.encode(pass);
        String hashPass4 = encode.encode(pass);
        String hashPass5 = encode.encode(pass);
        String hashPass6 = encode.encode(pass);
        System.out.println("===="+hashPass.length());
        System.out.println("===="+hashPass1.length());
        System.out.println("===="+hashPass3.length());
        System.out.println("===="+hashPass4.length());
        System.out.println("===="+hashPass5.length());
        System.out.println("===="+hashPass6.length());
    }
}
