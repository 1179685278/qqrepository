package com.by.config;

import com.by.realms.MyRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by gcq on 2019/7/4.
 */
@Configuration
public class MyShiroConfig {

    /*
   * <bean id="securityManager" class="org.apache.shiro.web.mgt.DefaultWebSecurityManager">
       <!-- cacheManager 加载缓存  -->
       <property name="cacheManager" ref="cacheManager"/>
       <!-- 使用自定义的 realm  -->
       <property name="realm" ref="myRealm"/>
   </bean>
   * */
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myRealm());

        return securityManager;
    }

    /*
    * <bean id="myRealm" class="com.by.realm.MyRealm">
        <!-- 设置加密 -->
        <property name="credentialsMatcher">

        </property>
    </bean>
    * */
    @Bean
    public MyRealm myRealm(){
        MyRealm myRealm = new MyRealm();
        myRealm.setCredentialsMatcher(hashedCredentialsMatcher());

        return myRealm;
    }

    /*
    * <!-- 加密规则使用 MD5-->
            <bean class="org.apache.shiro.authc.credential.HashedCredentialsMatcher">
                <property name="hashAlgorithmName" value="MD5"></property>
                <!-- 加密次数 -->
                <property name="hashIterations" value="3"></property>
            </bean>
    * */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(3);
        return hashedCredentialsMatcher;
    }



    @Bean
    public ShiroFilterFactoryBean shiroFilter(){
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(defaultWebSecurityManager());
        factoryBean.setLoginUrl("/login.jsp");
        factoryBean.setSuccessUrl("/test.jsp");
        factoryBean.setUnauthorizedUrl("/unauthorized.jsp");
        Map<String ,String> map = new LinkedHashMap<>();
        map.put("/login.jsp","anon");
        map.put("/user/login","anon");
        map.put("/loginadd.jsp","anon");
        map.put("/user/logout","logout");
        map.put("/**","authc");
        factoryBean.setFilterChainDefinitionMap(map);
        return factoryBean;
    }
}
