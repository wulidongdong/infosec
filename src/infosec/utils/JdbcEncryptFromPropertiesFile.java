package infosec.utils;


import java.util.Properties;

import org.springframework.beans.BeansException;  
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;  
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;  

public class JdbcEncryptFromPropertiesFile extends  
      PropertyPlaceholderConfigurer {  

  @Override  
  protected void processProperties(  
          ConfigurableListableBeanFactory beanFactory, Properties props)  
          throws BeansException {  
      String password = props.getProperty("jdbc.password");
      String username = props.getProperty("jdbc.username");

      if (password != null) {//**解密过程**  
          // 解密jdbc.password属性值，重新明文赋值 
          String strDes = DESUtils.getDecryptString(password);// 密文解密成明文 
          props.setProperty("jdbc.password", strDes);//赋值  
      }  
      
      if (username != null) {//**解密过程**  
          // 解密jdbc.username属性值，重新明文赋值 
          String strDes = DESUtils.getDecryptString(username);// 密文解密成明文 
          props.setProperty("jdbc.username", strDes);//赋值  
      }
      super.processProperties(beanFactory, props);//调用父方法  

  }  

}  