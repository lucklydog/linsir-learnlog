# 参考文档    
[csdn博客](https://blog.csdn.net/wxl1314920/article/details/41892761)    
***    
# 问题描述  
expdp/impdp由于源数据版本高于目标数据库版本，遭遇ORA-39001、ORA-39000，ORA-39142，解决只需要expdp导出时加上目标数据的version，具体问题如下  
***     
![微信截图](https://github.com/lucklydog/linsir-learnlog/blob/master/oracle%E5%AD%A6%E4%B9%A0%E6%97%A5%E8%AE%B0/image/oracle1.png?raw=true)      
**报错信息**    
    连接到: Oracle Database 11g Enterprise Edition Release 11.1.0.7.0 - 64bit Production   
    With the Partitioning, OLAP, Data Mining and Real Application Testing options   
    ORA-39001: 参数值无效   
    ORA-39000: 转储文件说明错误   
    ORA-39142: 版本号 3.1 (在转储文件 "d:\OraData\scott_bx.dmp" 中) 不兼容              
* 查阅资料知道是因为源数据版本高于目标数据库版本，于是查阅了我的数据库的版本   
**查阅版本代码1**      
    D:\OraData>sqlplus -version     
    SQL*Plus: Release 11.1.0.7.0 - Production
***     
果然与源数据不一致，源数据的数据库版本为11.2.0.1.0      
**查阅代码2**    
        D:\OraData>sqlplus sys/oracle as sysdba      
        SQL> select * from v$version;
        BANNER   
        Oracle Database 11g Enterprise Edition Release 11.1.0.7.0 - 64bit Production   
        PL/SQL Release 11.1.0.7.0 - Production   
        CORE    11.1.0.7.0      Production  
        TNS for 64-bit Windows: Version 11.1.0.7.0 - Production   
        NLSRTL Version 11.1.0.7.0 - Production      
***  
以上两种查找方式不同，结果一致  
