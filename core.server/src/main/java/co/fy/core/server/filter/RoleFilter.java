package co.fy.core.server.filter;

import co.fy.core.server.OauthAccessToken.api.OauthAccessTokenApi;
import co.fy.core.server.OauthAccessToken.model.OauthAccessToken;
import co.fy.core.server.role.api.RoleServiceApi;
import org.apache.catalina.servlet4preview.http.HttpFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisKeyValueTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

/**
 * CREATED IN  2017-12-04 下午3:24
 *
 * @AUTHOR: 姜坤
 * 权限过滤器
 **/
@WebFilter(urlPatterns = "/*", filterName = "roleFilter")
public class RoleFilter extends HttpFilter {
    @Autowired
    private RoleServiceApi roleServiceApi;

    @Autowired
    private JedisPool jedis;
    @Autowired
    private RedisKeyValueTemplate redisKeyValueTemplate;

    Logger logger = LoggerFactory.getLogger(RoleFilter.class);
    @Autowired
    private OauthAccessTokenApi oauthAccessTokenApi;

    protected String extractTokenKey(String value) {
        if (value == null) {
            return null;
        } else {
            MessageDigest digest;
            try {
                digest = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException var5) {
                throw new IllegalStateException("MD5 algorithm not available.  Fatal (should be in the JDK).");
            }

            try {
                byte[] bytes = digest.digest(value.getBytes("UTF-8"));
                return String.format("%032x", new BigInteger(1, bytes));
            } catch (UnsupportedEncodingException var4) {
                throw new IllegalStateException("UTF-8 encoding not available.  Fatal (should be in the JDK).");
            }
        }
    }

    /*@Autowired
      DataSource dataSource;
      JdbcTokenStore jdbcTokenStore=new JdbcTokenStore(dataSource);*/
    private HttpServletRequest request;
    private HttpServletResponse response;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("==================================================core.server权限过滤器 init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        this.request = (HttpServletRequest) servletRequest;
        this.response = ((HttpServletResponse) servletResponse);
        String api = request.getRequestURI();
        logger.info("==================================================根据请求header 获取 token数据");
        String token = request.getHeader("Authorization").replace(" ", "").split("Bearer")[1];
        String token_value=jedis.getResource().get(("auth:"+token));
        if(token_value==null){
            request.getRequestDispatcher("/error/402").forward(request, response);
        }else{
            String st=token_value.split("java.lang.Number")[1].split("2a")[1];
            if (roleServiceApi.inspect(st.substring(60,st.length()), api)) {
                filterChain.doFilter(request, response);
            } else {
                request.getRequestDispatcher("/error/401").forward(request, response);
            }
        }
       /* String s=d.split("java.lang.Number")[1];

        String[] st=s.split("2a");
        System.out.print(st[1].substring(58,st[1].length()).length());
        String s1=st[1].substring(60,st[1].length());*/
       /*
������  xp       t <$2a$10$DwKR2oIq/YE6bNAwuOszwOxv/wf/eAD6nDfjajzXFQ2eosE5RNhMCt test01
������  xp       t <$2a$10$8FPqg4tPwZscaPk8VTIe8O78KD7KzZuZMK0Eoz2Sw2hhara5eyBvat admin
������  xp       t <$2a$10$DwKR2oIq/YE6bNAwuOszwOxv/wf/eAD6nDfjajzXFQ2eosE5RNhMCt test01



        Optional<OauthAccessToken> optional = oauthAccessTokenApi.getOneOauthAccessToken(tokenId);

�� sr Aorg.springframework.security.oauth2.provider.OAuth2Authentication�@bR L
storedRequestt <Lorg/springframework/security/oauth2/provider/OAuth2Request;L userAuthenticationt 2Lorg/springframework/security/core/Authentication;xr Gorg.springframework.security.authentication.AbstractAuthenticationTokenӪ(~nGd Z
authenticatedL authoritiest Ljava/util/Collection;L detailst Ljava/lang/Object;xp sr &java.util.Collections$UnmodifiableList�%1�� L listt Ljava/util/List;xr ,java.util.Collections$UnmodifiableCollectionB ��^� L cq ~ xpsr java.util.ArrayListx����a� I sizexp    w    xq ~ psr :org.springframework.security.oauth2.provider.OAuth2Request        Z approvedL authoritiesq ~ L
extensionst Ljava/util/Map;L redirectUrit Ljava/lang/String;L refresht ;Lorg/springframework/security/oauth2/provider/TokenRequest;L resourceIdst Ljava/util/Set;L
responseTypesq ~ xr 8org.springframework.security.oauth2.provider.BaseRequest6(z>�qi� L clientIdq ~ L requestParametersq ~ L scopeq ~ xpt uTXPb6w3QQLQQj2RrCdsr %java.util.Collections$UnmodifiableMap��t�B L mq ~ xpsr java.util.HashMap���`� F
loadFactorI 	thresholdxp?@     w      t 	source_idt oauth2-resourcet
grant_typet passwordt 	client_idt uTXPb6w3QQLQQj2RrCdt scopet webt usernamet adminxsr %java.util.Collections$UnmodifiableSet��я��U  xq ~ 	sr java.util.LinkedHashSet�l�Z��*  xr java.util.HashSet�D�����4  xpw   ?@     q ~  xsq ~ &w   ?@     sr Borg.springframework.security.core.authority.SimpleGrantedAuthority      � L roleq ~ xpt clientxsq ~ ?@      w       xppsq ~ &w   ?@     t oauth2-resourcexsq ~ &w   ?@      xsr Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken      � L credentialsq ~ L 	principalq ~ xq ~ sq ~ sq ~     w    xq ~ 3sr java.util.LinkedHashMap4�N\l�� Z accessOrderxq ~ ?@     w      q ~ q ~ q ~ q ~  t
client_secrett c9yuCNT7Wgv53kXrh2rq ~ q ~ q ~ q ~ q ~ !q ~ "x psr $co.fy.gateway.server.user.model.User        Z enabledL idt Ljava/lang/Long;L passwordq ~ L usernameq ~ xpsr java.lang.Long;��̏#� J valuexr java.lang.Number������  xp       t <$2a$10$8FPqg4tPwZscaPk8VTIe8O78KD7KzZuZMK0Eoz2Sw2hhara5eyBvat admin

¬í sr Aorg.springframework.security.oauth2.provider.OAuth2Authentication½@bR L 
storedRequestt <Lorg/springframework/security/oauth2/provider/OAuth2Request;L userAuthenticationt 2Lorg/springframework/security/core/Authentication;xr Gorg.springframework.security.authentication.AbstractAuthenticationTokenÓª(~nGd Z 
authenticatedL authoritiest Ljava/util/Collection;L detailst Ljava/lang/Object;xp sr &java.util.Collections$UnmodifiableListü%1µì L listt Ljava/util/List;xr ,java.util.Collections$UnmodifiableCollectionB Ë^÷ L cq ~ xpsr java.util.ArrayListxÒÇa I sizexp    w    xq ~ psr :org.springframework.security.oauth2.provider.OAuth2Request        Z approvedL authoritiesq ~ L 
extensionst Ljava/util/Map;L redirectUrit Ljava/lang/String;L refresht ;Lorg/springframework/security/oauth2/provider/TokenRequest;L resourceIdst Ljava/util/Set;L 
responseTypesq ~ xr 8org.springframework.security.oauth2.provider.BaseRequest6(z>£qi½ L clientIdq ~ L requestParametersq ~ L scopeq ~ xpt uTXPb6w3QQLQQj2RrCdsr %java.util.Collections$UnmodifiableMapñ¥¨þtõB L mq ~ xpsr java.util.HashMapÚÁÃ`Ñ F 
loadFactorI 	thresholdxp?@     w      t 	source_idt oauth2-resourcet 
grant_typet passwordt 	client_idt uTXPb6w3QQLQQj2RrCdt scopet webt usernamet adminxsr %java.util.Collections$UnmodifiableSetÑU  xq ~ 	sr java.util.LinkedHashSetØl×ZÝ*  xr java.util.HashSetºD¸·4  xpw   ?@     q ~  xsq ~ &w   ?@     sr Borg.springframework.security.core.authority.SimpleGrantedAuthority      ¤ L roleq ~ xpt clientxsq ~ ?@      w       xppsq ~ &w   ?@     t oauth2-resourcexsq ~ &w   ?@      xsr Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken      ¤ L credentialsq ~ L 	principalq ~ xq ~ sq ~ sq ~     w    xq ~ 3sr java.util.LinkedHashMap4ÀN\lÀû Z accessOrderxq ~ ?@     w      q ~ q ~ q ~ q ~  t 
client_secrett c9yuCNT7Wgv53kXrh2rq ~ q ~ q ~ q ~ q ~ !q ~ "x psr $co.fy.gateway.server.user.model.User        Z enabledL idt Ljava/lang/Long;L passwordq ~ L usernameq ~ xpsr java.lang.Long;äÌ#ß J valuexr java.lang.Number¬à  xp       t <$2a$10$8FPqg4tPwZscaPk8VTIe8O78KD7KzZuZMK0Eoz2Sw2hhara5eyBvat admin
         //数据库的token 的方式
       /* String tokenId = this.extractTokenKey(request.getHeader("Authorization").replace(" ", "").split("Bearer")[1]);
        logger.info("==================================================根据获得token 获得唯一用户");

        Optional<OauthAccessToken> optional = oauthAccessTokenApi.getOneOauthAccessToken(tokenId);
        if (optional.isPresent()) {
            //获得用户名
            ;
            if (roleServiceApi.inspect(optional.get().getUserName(), api)) {
                filterChain.doFilter(request, response);
            } else {
                request.getRequestDispatcher("/error/401").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("/error/402").forward(request, response);
        }*/
        //filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
    public static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
}
