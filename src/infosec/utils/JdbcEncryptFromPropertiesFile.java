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

      if (password != null) {//**���ܹ���**  
          // ����jdbc.password����ֵ���������ĸ�ֵ 
          String strDes = DESUtils.getDecryptString(password);// ���Ľ��ܳ����� 
          props.setProperty("jdbc.password", strDes);//��ֵ  
      }  
      
      if (username != null) {//**���ܹ���**  
          // ����jdbc.username����ֵ���������ĸ�ֵ 
          String strDes = DESUtils.getDecryptString(username);// ���Ľ��ܳ����� 
          props.setProperty("jdbc.username", strDes);//��ֵ  
      }
      super.processProperties(beanFactory, props);//���ø�����  

  }  

}  